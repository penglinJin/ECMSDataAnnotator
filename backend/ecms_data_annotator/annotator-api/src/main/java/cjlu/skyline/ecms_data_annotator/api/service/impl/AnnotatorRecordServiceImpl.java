package cjlu.skyline.ecms_data_annotator.api.service.impl;

import cjlu.skyline.ecms_data_annotator.api.entity.*;
import cjlu.skyline.ecms_data_annotator.api.service.*;
import cjlu.skyline.ecms_data_annotator.api.utils.ApiUtils;
import cjlu.skyline.ecms_data_annotator.api.vo.ApproveVo;
import cjlu.skyline.ecms_data_annotator.api.vo.StaticsVo;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import cjlu.skyline.ecms_data_annotator.api.dao.AnnotatorRecordDao;
import org.springframework.util.StringUtils;


@Service("annotatorRecordService")
public class AnnotatorRecordServiceImpl extends ServiceImpl<AnnotatorRecordDao, AnnotatorRecordEntity> implements AnnotatorRecordService {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    LabelInfoService labelInfoService;

    @Autowired
    DocService docService;

    @Autowired
    AnnotatorRecordService annotatorRecordService;

    @Autowired
    DocLabelService docLabelService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AnnotatorRecordEntity> page = this.page(
                new Query<AnnotatorRecordEntity>().getPage(params),
                new QueryWrapper<AnnotatorRecordEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryApprovalPage(Map<String, Object> params) {
        QueryWrapper<AnnotatorRecordEntity> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("status",0);
        List<AnnotatorRecordEntity> annotatorRecordEntities = this.list(queryWrapper);

        IPage<AnnotatorRecordEntity> page = this.page(
                new Query<AnnotatorRecordEntity>().getPage(params),
                queryWrapper
        );
        IPage<AnnotatorRecordEntity> convert = page.convert(AnnotatorRecordEntity -> ApiUtils.copyProperties(AnnotatorRecordEntity, ApproveVo.class));

        List<AnnotatorRecordEntity> approvalVos=new ArrayList<>();
        convert.getRecords().forEach(i->{
            Long userId=i.getUserId();
            SysUserEntity user = sysUserService.getOne(new QueryWrapper<SysUserEntity>().eq("user_id", userId));
            ApproveVo approveVo=new ApproveVo();
            BeanUtils.copyProperties(i,approveVo);
            approveVo.setCreateUserName(user.getUsername());
            DocEntity docEntity = docService.getOne(new QueryWrapper<DocEntity>().eq("doc_id", i.getDocId()));
            approveVo.setDocContent(docEntity.getDocContent());
            approveVo.setHtmlContent(docEntity.getHtmlContent());

            if (!StringUtils.isEmpty(i.getOldLabels())){
                StringBuilder sb=new StringBuilder();
                List<Long> oldLabelList = ApiUtils.transToLabelInfo(i.getOldLabels());
                oldLabelList.forEach(lbl->{
                    LabelInfoEntity one = labelInfoService.getOne(new QueryWrapper<LabelInfoEntity>().eq("label_id", lbl));
                    sb.append(one.getLabelContent());
                    sb.append(" ");
                });
                approveVo.setOldLabelNames(sb.toString());
            }
            if (!StringUtils.isEmpty(i.getNewLabels())){
                StringBuilder sb2=new StringBuilder();
                List<Long> newlabels = ApiUtils.transToLabelInfo(i.getNewLabels());

                newlabels.forEach(lbl->{
                    LabelInfoEntity one = labelInfoService.getOne(new QueryWrapper<LabelInfoEntity>().eq("label_id", lbl));
                    sb2.append(one.getLabelContent());
                    sb2.append(" ");
                });

                approveVo.setUpdatedLabelNames(sb2.toString());
            }

            approvalVos.add(approveVo);
        });

        convert.setRecords(approvalVos);
        return new PageUtils(convert);
    }

    @Override
    public AnnotatorRecordEntity getLabelApproveRecord(Long labelId) {
        List<AnnotatorRecordEntity> list = this.list();
        List<AnnotatorRecordEntity> es=new ArrayList<>();
        list.forEach(item->{
            String newLabels = item.getNewLabels();
            if (!StringUtils.isEmpty(newLabels)){
                String[] split = newLabels.split(",");
                List<String> collect = Arrays.stream(split).filter(e -> e.equals(String.valueOf(labelId))).collect(Collectors.toList());
                if (collect.size()>0){
                    es.add(item);
                }
            }
        });

        List<AnnotatorRecordEntity> collect = es.stream().sorted(Comparator.comparing(AnnotatorRecordEntity::getCreateTime).reversed()).collect(Collectors.toList());

        AnnotatorRecordEntity record = collect.get(0);
        return record;
    }

    @Override
    public List<StaticsVo> getTagsStatics() {
        List<StaticsVo> staticsVos = new ArrayList<>();
        List<LabelInfoEntity> list = labelInfoService.list();
        list.forEach(e->{
            Long labelId = e.getLabelId();
            List<DocLabelEntity> labels = docLabelService.list(new QueryWrapper<DocLabelEntity>().eq("label_id", labelId));
            StaticsVo staticsVo=new StaticsVo();
            staticsVo.setName(e.getLabelContent());
            staticsVo.setValue(labels.size());
            staticsVos.add(staticsVo);
        });

        return staticsVos;
    }


    @Override
    public List<StaticsVo> getStatics() {
        List<StaticsVo> staticsVos = new ArrayList<>();
        List<AnnotatorRecordEntity> list = annotatorRecordService.list();
        Map<Long, List<AnnotatorRecordEntity>> collect = list.stream().collect(Collectors.groupingBy(AnnotatorRecordEntity::getUserId));
        collect.forEach((k,v)->{
            QueryWrapper<SysUserEntity> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("user_id",k);
            SysUserEntity one = sysUserService.getOne(queryWrapper);
            int i=0;
            for (AnnotatorRecordEntity recordEntity : v) {
                if (recordEntity.getAnnotatorTypeCode() == 0){
                    i++;
                }
            }
            StaticsVo staticsVo=new StaticsVo();
            staticsVo.setName(one.getUsername());
            staticsVo.setValue(i);
            staticsVos.add(staticsVo);
        });
        return staticsVos;
    }

    @Override
    public List<StaticsVo> getCompleteSituation() {
        List<StaticsVo> staticsVos=new ArrayList<>();
        List<AnnotatorRecordEntity> annotatorRecords = annotatorRecordService.list();
        List<DocEntity> docs = docService.list();

        LabelInfoEntity positive = labelInfoService.getOneByName("positive");
        LabelInfoEntity negative = labelInfoService.getOneByName("negative");


        AtomicReference<Integer> countNlp= new AtomicReference<>(0);
        AtomicReference<Integer> countManual= new AtomicReference<>(0);
        AtomicReference<Integer> countUndo= new AtomicReference<>(0);

        docs.stream().forEach(e->{
            List<Long> oldLabels = labelInfoService.getOldLabels(e.getDocId());
            if (!StringUtils.isEmpty(e.getNlpLabel())){
                if (oldLabels.size()>0){
                    if (oldLabels.size()==1){
                        Long labelId = oldLabels.get(0);
                        String labelName="";
                        if (labelId.equals(positive.getLabelId())){
                            labelName=positive.getLabelContent();
                        }else if (labelId.equals(negative.getLabelId())){
                            labelName=negative.getLabelContent();
                        }
                        String nlpLabel = e.getNlpLabel();
                        //如果doc只有一个与nlp处理结果相同的注解则+1,否则人工+1
                        if (labelName.equals(nlpLabel)){
                            countNlp.getAndSet(countNlp.get() + 1);
                        }else {
                            countManual.getAndSet(countManual.get()+1);
                        }
                    } else if (oldLabels.size()>1){
                        countManual.getAndSet(countManual.get()+1);
                    }
                }else if (oldLabels.size()==0){
                    countUndo.getAndSet(countUndo.get() + 1);
                }
            }else {
                if (oldLabels.size()>0){
                    countManual.getAndSet(countManual.get()+1);
                }else {
                    countUndo.getAndSet(countUndo.get() + 1);
                }
            }
        });


        //1.设置nlp处理结果
        StaticsVo nlpStatics=new StaticsVo();
        nlpStatics.setName("nlp");
        nlpStatics.setValue(countNlp.get());
        staticsVos.add(nlpStatics);

        //2.设置已经手工处理结果
        StaticsVo manualStatics=new StaticsVo();
        manualStatics.setName("manual");
        manualStatics.setValue(countManual.get());
        staticsVos.add(manualStatics);


        //3.设置未处理结果
        StaticsVo undoStatics=new StaticsVo();
        undoStatics.setName("undo");
        undoStatics.setValue(countUndo.get());
        staticsVos.add(undoStatics);

        return staticsVos;
    }
}