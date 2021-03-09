package cjlu.skyline.ecms_data_annotator.api.service.impl;

import cjlu.skyline.ecms_data_annotator.api.dao.*;
import cjlu.skyline.ecms_data_annotator.api.entity.*;
import cjlu.skyline.ecms_data_annotator.api.feign.ThirdPartyFeignService;
import cjlu.skyline.ecms_data_annotator.api.service.DocStateService;
import cjlu.skyline.ecms_data_annotator.api.service.LabelInfoService;
import cjlu.skyline.ecms_data_annotator.api.utils.ApiUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.R;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.Query;

import cjlu.skyline.ecms_data_annotator.api.service.SrcDocService;
import org.springframework.util.StringUtils;


@Service("srcDocService")
public class SrcDocServiceImpl extends ServiceImpl<SrcDocDao, SrcDocEntity> implements SrcDocService {

    @Autowired
    ThirdPartyFeignService thirdPartyFeignService;

    private static String JSON="json";

    private static String TXT="txt";

    private static String PNG="png";

    private static String JPG="jpg";


    @Value("${stock.dir}")
    private String stockDir;

    @Autowired
    SrcDocDao srcDocDao;

    @Autowired
    DocDao docDao;

    @Autowired
    AnnotatorRecordDao annotatorRecordDao;

    @Autowired
    DocLabelDao docLabelDao;

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
    public R processDataset(String filePath,Long userId) {

        //insert srcDoc into database
        SrcDocEntity srcDocEntity=new SrcDocEntity();
        Long srcDocId=ApiUtils.getUniqId();
        srcDocEntity.setSrcDocId(srcDocId);
        srcDocEntity.setSrcDocPath(filePath);
        int i=filePath.indexOf("_");
        String fileName=filePath.substring(i+1);
        srcDocEntity.setSrcDocName(fileName);

        int j=fileName.indexOf(".");
        String fileType=fileName.substring(j+1);
        if (!StringUtils.isEmpty(fileType)){
            if (fileType.equals(JSON)||fileType.equals(TXT)){
                srcDocEntity.setDocType(0);
            }else if (fileType.equals(PNG)||fileType.equals(JPG)){
                srcDocEntity.setDocType(1);
            }
        }
        srcDocEntity.setCreateUserId(userId);
        srcDocEntity.setCreateTime(new Date());
        srcDocDao.insert(srcDocEntity);


        URL url = null;

        if (fileType.equals(TXT)){
            try {
                url = new URL(filePath);

                URLConnection conn = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));

                String current;
                while ((current = in.readLine()) != null) {
                    DocEntity docEntity=new DocEntity();
                    Long docId=ApiUtils.getUniqId();
                    docEntity.setDocId(docId);
                    docEntity.setSrcDocId(srcDocId);
                    docEntity.setDocType(0);
                    docEntity.setCreateUserId(userId);
                    docEntity.setCreateTime(new Date());
                    docEntity.setDocContent(current);
                    docDao.insert(docEntity);

                    DocStateEntity docStateEntity=new DocStateEntity();
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

        if (fileType.equals(JSON)){

        }



        return R.ok();
    }

    @Override
    public R annotate(Long[] labelIds, Long userId, Long docId) {
        List<Long> news = Arrays.asList(labelIds);
        String newLabels=ApiUtils.transToString(news);
        List<Long> olds=labelInfoService.getOldLabels(docId);
        String oldLabels=ApiUtils.transToString(olds);

        AnnotatorRecordEntity annotationRecord=new AnnotatorRecordEntity();
        annotationRecord.setAnnotatorTypeCode(0);
        annotationRecord.setUserId(userId);
        annotationRecord.setDocId(docId);
        annotationRecord.setOldLabels(oldLabels);
        annotationRecord.setNewLabels(newLabels);

        //update doc state
        QueryWrapper<DocStateEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("doc_id",docId);
        DocStateEntity docState=docStateService.getOne(queryWrapper);
        //set status to wait for approval
        docState.setDocStat(1);
        docStateService.update(docState,new UpdateWrapper<DocStateEntity>().eq("doc_id",docId));
        return R.ok();
    }


}