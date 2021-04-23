package cjlu.skyline.ecms_data_annotator.api.service.impl;

import cjlu.skyline.ecms_data_annotator.api.entity.DocEntity;
import cjlu.skyline.ecms_data_annotator.api.entity.LabelInfoEntity;
import cjlu.skyline.ecms_data_annotator.api.entity.SysUserEntity;
import cjlu.skyline.ecms_data_annotator.api.service.DocService;
import cjlu.skyline.ecms_data_annotator.api.service.LabelInfoService;
import cjlu.skyline.ecms_data_annotator.api.service.SysUserService;
import cjlu.skyline.ecms_data_annotator.api.utils.ApiUtils;
import cjlu.skyline.ecms_data_annotator.api.vo.ApproveVo;
import cjlu.skyline.ecms_data_annotator.api.vo.StaticsVo;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import cjlu.skyline.ecms_data_annotator.api.dao.AnnotatorRecordDao;
import cjlu.skyline.ecms_data_annotator.api.entity.AnnotatorRecordEntity;
import cjlu.skyline.ecms_data_annotator.api.service.AnnotatorRecordService;
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
    public List<StaticsVo> getStatics() {
        List<StaticsVo> staticsVos = new ArrayList<>();
        List<AnnotatorRecordEntity> list = annotatorRecordService.list();
        Map<Long, List<AnnotatorRecordEntity>> collect = list.stream().collect(Collectors.groupingBy(AnnotatorRecordEntity::getUserId));
        collect.forEach((k,v)->{
            QueryWrapper<SysUserEntity> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("user_id",k);
            SysUserEntity one = sysUserService.getOne(queryWrapper);
            int i=0;
//            System.out.println("userId:"+k+" value:"+v);
            for (AnnotatorRecordEntity recordEntity : v) {
                if (recordEntity.getAnnotatorTypeCode() == 0)
                    i++;
            }
            StaticsVo staticsVo=new StaticsVo();
            staticsVo.setName(one.getUsername());
            staticsVo.setValue(i);
            staticsVos.add(staticsVo);
        });
        return staticsVos;
    }
}