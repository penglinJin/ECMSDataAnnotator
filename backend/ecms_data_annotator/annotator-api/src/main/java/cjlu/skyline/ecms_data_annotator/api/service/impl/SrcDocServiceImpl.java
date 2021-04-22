package cjlu.skyline.ecms_data_annotator.api.service.impl;

import cjlu.skyline.ecms_data_annotator.api.dao.*;
import cjlu.skyline.ecms_data_annotator.api.dto.AnnotationDto;
import cjlu.skyline.ecms_data_annotator.api.dto.JsonDto;
import cjlu.skyline.ecms_data_annotator.api.entity.*;
import cjlu.skyline.ecms_data_annotator.api.feign.ThirdPartyFeignService;
import cjlu.skyline.ecms_data_annotator.api.service.*;
import cjlu.skyline.ecms_data_annotator.api.utils.ApiUtils;
import cjlu.skyline.ecms_data_annotator.api.utils.NLPUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.R;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.Query;

import org.springframework.util.StringUtils;


@Service("srcDocService")
public class SrcDocServiceImpl extends ServiceImpl<SrcDocDao, SrcDocEntity> implements SrcDocService {

    @Autowired
    ThirdPartyFeignService thirdPartyFeignService;

    private static String JSON = "json";

    private static String TXT = "txt";

    private static String PNG = "png";

    private static String JPG = "jpg";

    private static String POSITIVE="positive";

    private static String NEGATIVE="negative";


    @Autowired
    DocLabelService docLabelService;

    @Autowired
    DocService docService;

    @Autowired
    AnnotatorRecordService annotatorRecordService;

    @Autowired
    DocStateService docStateService;

    @Autowired
    LabelInfoService labelInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SrcDocEntity> page = this.page(
                new Query<SrcDocEntity>().getPage(params),
                new QueryWrapper<SrcDocEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public R processDataset(String filePath, Long userId) {

        //insert srcDoc into database
        SrcDocEntity srcDocEntity = new SrcDocEntity();
        Long srcDocId = ApiUtils.getUniqId();
        srcDocEntity.setSrcDocId(srcDocId);
        srcDocEntity.setSrcDocPath(filePath);
        int i = filePath.indexOf("_");
        String fileName = filePath.substring(i + 1);
        srcDocEntity.setSrcDocName(fileName);

        int j = fileName.indexOf(".");
        String fileType = fileName.substring(j + 1);
        if (!StringUtils.isEmpty(fileType)) {
            if (fileType.equals(JSON) || fileType.equals(TXT)) {
                srcDocEntity.setDocType(0);
            } else if (fileType.equals(PNG) || fileType.equals(JPG)) {
                srcDocEntity.setDocType(1);
            }
        }
        srcDocEntity.setCreateUserId(userId);
        srcDocEntity.setCreateTime(new Date());
        this.save(srcDocEntity);

        LabelInfoEntity positiveEntity=labelInfoService.getOne(new QueryWrapper<LabelInfoEntity>().eq("label_content",POSITIVE));
        LabelInfoEntity negativeEntity=labelInfoService.getOne(new QueryWrapper<LabelInfoEntity>().eq("label_content",NEGATIVE));

        URL url = null;

        if (fileType.equals(TXT)) {
            try {
                url = new URL(filePath);

                URLConnection conn = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

                String current;
                while ((current = in.readLine()) != null) {
                    DocEntity docEntity = new DocEntity();
                    Long docId = ApiUtils.getUniqId();
                    docEntity.setDocId(docId);
                    docEntity.setSrcDocId(srcDocId);
                    docEntity.setDocType(0);
                    docEntity.setCreateUserId(userId);
                    docEntity.setCreateTime(new Date());
                    docEntity.setDocContent(current);


                    if(NLPUtils.getScore(current)>=2){
                        docEntity.setNlpLabel(POSITIVE);
                        DocLabelEntity docLabelEntity=new DocLabelEntity();
                        docLabelEntity.setDocId(docId);
                        docLabelEntity.setLabelId(positiveEntity.getLabelId());
                        docLabelService.save(docLabelEntity);
                    }else {
                        docEntity.setNlpLabel(NEGATIVE);
                        DocLabelEntity docLabelEntity=new DocLabelEntity();
                        docLabelEntity.setDocId(docId);
                        docLabelEntity.setLabelId(negativeEntity.getLabelId());
                        docLabelService.save(docLabelEntity);
                    }


                    docService.save(docEntity);

                    DocStateEntity docStateEntity = new DocStateEntity();
                    docStateEntity.setDocId(docId);
                    docStateEntity.setCreateTime(new Date());
                    docStateEntity.setUpdateTime(new Date());
                    docStateEntity.setDocStat(0);
                    docStateService.save(docStateEntity);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return R.ok("txt file process success");
        }

        if (fileType.equals(JSON)) {

            List<LabelInfoEntity> labels=labelInfoService.list();

            List<String> labelContentList = labels.stream().map(LabelInfoEntity::getLabelContent).collect(Collectors.toList());
            try {
                url = new URL(filePath);

                URLConnection conn = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

                String current;
                while ((current = in.readLine()) != null) {
                    JSONObject jsonObject=JSONObject.parseObject(current);
                    if (!jsonObject.containsKey("text")||!jsonObject.containsKey("labels")){
                        return R.error("JSON format error");
                    }
                    String text=jsonObject.getString("text");
                    List<String> labelsList =jsonObject.getJSONArray("labels").toJavaList(String.class);
                    JsonDto jsonDto=new JsonDto();
                    jsonDto.setText(text);
                    jsonDto.setLabels(labelsList);

                    List<String> reduce = labelsList.stream().filter(item -> !labelContentList.contains(item)).collect(Collectors.toList());
                    if (reduce.size()>0){
                        return R.error("Some labels are uncreated");
                    }



                    DocEntity docEntity = new DocEntity();
                    Long docId = ApiUtils.getUniqId();
                    docEntity.setDocId(docId);
                    docEntity.setSrcDocId(srcDocId);
                    docEntity.setDocType(0);
                    docEntity.setCreateUserId(userId);
                    docEntity.setCreateTime(new Date());
                    docEntity.setDocContent(text);

                    //set labels
                    List<LabelInfoEntity> intersection=labels.stream().filter(item->labelsList.contains(item.getLabelContent())).collect(Collectors.toList());
                    intersection.forEach(item->{
                        DocLabelEntity docLabelEntity=new DocLabelEntity();
                        docLabelEntity.setLabelId(item.getLabelId());
                        docLabelEntity.setDocId(docId);
                        docLabelService.save(docLabelEntity);
                    });

                    //if there are no labels in origin data

                    if(NLPUtils.getScore(text)>=2){
                        docEntity.setNlpLabel(POSITIVE);
                    }else {
                        docEntity.setNlpLabel(NEGATIVE);
                    }

                    docService.save(docEntity);

                    DocStateEntity docStateEntity = new DocStateEntity();
                    docStateEntity.setDocId(docId);
                    docStateEntity.setCreateTime(new Date());
                    docStateEntity.setUpdateTime(new Date());
                    docStateEntity.setDocStat(0);
                    docStateService.save(docStateEntity);


                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return R.ok("JSON file process success");
        }

        if (fileType.equals(JPG) || fileType.equals(PNG)) {


            DocEntity docEntity = new DocEntity();
            Long docId = ApiUtils.getUniqId();
            docEntity.setDocId(docId);
            docEntity.setSrcDocId(srcDocId);
            docEntity.setDocType(1);
            docEntity.setCreateUserId(userId);
            docEntity.setCreateTime(new Date());
            docEntity.setDocContent(filePath);

            //TODO need to be improved after implement NLP module
            docService.save(docEntity);

            DocStateEntity docStateEntity = new DocStateEntity();
            docStateEntity.setDocId(docId);
            docStateEntity.setCreateTime(new Date());
            docStateEntity.setUpdateTime(new Date());
            docStateEntity.setDocStat(0);
            docStateService.save(docStateEntity);

            return R.ok("pic file process success");
        }


        return R.error("not txt,json,png or jpg file");
    }

    @Override
    public R annotate(Long[] labelIds, Long userId, Long docId) {
        List<Long> news = Arrays.asList(labelIds);
        String newLabels = ApiUtils.transToString(news);
        List<Long> olds = labelInfoService.getOldLabels(docId);
        String oldLabels = ApiUtils.transToString(olds);

        AnnotatorRecordEntity annotationRecord = new AnnotatorRecordEntity();
        annotationRecord.setAnnotatorTypeCode(0);
        annotationRecord.setUserId(userId);
        annotationRecord.setDocId(docId);
        annotationRecord.setOldLabels(oldLabels);
        annotationRecord.setNewLabels(newLabels);
        annotationRecord.setStatus(0);
        annotationRecord.setCreateTime(new Date());
        annotatorRecordService.save(annotationRecord);

        //update doc state
        QueryWrapper<DocStateEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("doc_id", docId);
        DocStateEntity docState = docStateService.getOne(queryWrapper);
        //set status to wait for approval
        docState.setDocStat(1);
        docStateService.update(docState, new UpdateWrapper<DocStateEntity>().eq("doc_id", docId));
        return R.ok();
    }

    /**
     * form json
     * @author 金鹏霖
     * @date 2021/4/13
     * @param
     * @return org.springframework.http.ResponseEntity<org.springframework.core.io.FileSystemResource>
     */
    @Override
    public ResponseEntity<FileSystemResource> downloadFile() {
        //1.collect all docs
        List<DocEntity> docEntities = docService.list();
        for(int i=0;i<docEntities.size();i++){
            DocEntity docEntity=docEntities.get(i);
            //each doc form to a jsonObject as a line in final json file
            JSONObject object=new JSONObject();
            
            AnnotationDto annotationDto=new AnnotationDto();

        }
        return null;
    }

    public ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
    }


}