package cjlu.skyline.ecms_data_annotator.api.service.impl;

import cjlu.skyline.ecms_data_annotator.api.dao.DocStateDao;
import cjlu.skyline.ecms_data_annotator.api.entity.AnnotatorRecordEntity;
import cjlu.skyline.ecms_data_annotator.api.entity.DocStateEntity;
import cjlu.skyline.ecms_data_annotator.api.service.AnnotatorRecordService;
import cjlu.skyline.ecms_data_annotator.api.service.DocStateService;
import cjlu.skyline.ecms_data_annotator.api.utils.ApiUtils;
import cjlu.skyline.ecms_data_annotator.api.vo.DocVo;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.Query;
import cjlu.skyline.ecms_data_annotator.common.utils.R;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import cjlu.skyline.ecms_data_annotator.api.dao.DocDao;
import cjlu.skyline.ecms_data_annotator.api.entity.DocEntity;
import cjlu.skyline.ecms_data_annotator.api.service.DocService;


@Service("docService")
public class DocServiceImpl extends ServiceImpl<DocDao, DocEntity> implements DocService {

    @Autowired
    DocDao docDao;

    @Autowired
    DocStateDao docStateDao;

    @Autowired
    DocStateService docStateService;

    @Autowired
    AnnotatorRecordService annotatorRecordService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DocEntity> page = this.page(
                new Query<DocEntity>().getPage(params),
                new QueryWrapper<DocEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public R deleteBatch(Long[] docIds) {
        Arrays.stream(docIds).forEach(id->{
            QueryWrapper<DocEntity> docQueryWrapper=new QueryWrapper<>();
            docQueryWrapper.eq("doc_id",id);
            docDao.delete(docQueryWrapper);

            QueryWrapper<DocStateEntity> docStateQueryWrapper=new QueryWrapper<>();
            docStateQueryWrapper.eq("doc_id",id);
            docStateDao.delete(docStateQueryWrapper);


        });
        return R.ok();
    }

    @Override
    public PageUtils queryPrePage(Map<String, Object> params) {

        List<Long> statList=new ArrayList<>();
        statList.add(Long.parseLong("0"));
        statList.add(Long.parseLong("2"));
        List<DocStateEntity> docStatList = docStateService.list(new QueryWrapper<DocStateEntity>().in("doc_stat",statList));

        List<Long> docIdList = new ArrayList<>();
        docStatList.forEach(item->{
            docIdList.add(item.getDocId());
        });

        IPage<DocEntity> page = this.page(
                new Query<DocEntity>().getPage(params),
                new QueryWrapper<DocEntity>().in("doc_id",docIdList)
        );

        IPage<DocVo> convert = page.convert(DocEntity -> ApiUtils.copyProperties(DocEntity, DocVo.class));

        List<DocVo> docVos=new ArrayList<>();
        convert.getRecords().forEach(i->{
            Long docId = i.getDocId();
            QueryWrapper<DocStateEntity> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("doc_id",docId);
            int docStatus=docStateService.getOne(queryWrapper).getDocStat();
            if (docId==0){
                i.setDocState("unannotated");

            }else if (docId==2){
                i.setDocState("approved");
            }
            docVos.add(i);
        });

        convert.setRecords(docVos);



        return new PageUtils(convert);
    }

    @Override
    public PageUtils queryApprovalPage(Map<String, Object> params) {

        List<DocStateEntity> docStatList = docStateService.list(new QueryWrapper<DocStateEntity>().eq("doc_stat", 1));

        List<Long> docIdList = new ArrayList<>();
        docStatList.forEach(item->{
            docIdList.add(item.getDocId());
        });

        IPage<DocEntity> page = this.page(
                new Query<DocEntity>().getPage(params),
                new QueryWrapper<DocEntity>().in("doc_id",docIdList)
        );

        IPage<DocVo> convert = page.convert(DocEntity -> ApiUtils.copyProperties(DocEntity, DocVo.class));

        List<DocVo> docVos=new ArrayList<>();
        convert.getRecords().forEach(i->{
            i.setDocState("unapproved");
            docVos.add(i);
        });

        convert.setRecords(docVos);
        return new PageUtils(convert);
    }

    @Override
    public R approve(Long annotateRecordId,Long userId) {

        AnnotatorRecordEntity record = annotatorRecordService
                .getOne(new QueryWrapper<AnnotatorRecordEntity>()
                        .eq("record_id", annotateRecordId));

        //approve record
        AnnotatorRecordEntity annotatorRecordEntity=new AnnotatorRecordEntity();
        Long docId=record.getDocId();
        annotatorRecordEntity.setDocId(docId);
        annotatorRecordEntity.setUserId(userId);
        annotatorRecordEntity.setAnnotatorTypeCode(2);
        annotatorRecordEntity.setTargetRecord(annotateRecordId);
        annotatorRecordEntity.setStatus(1);
        annotatorRecordService.save(annotatorRecordEntity);

        record.setStatus(1);
        annotatorRecordService.update(record,new UpdateWrapper<AnnotatorRecordEntity>().eq("record_id",annotateRecordId));

        DocStateEntity docStateEntity=docStateService.getOne(new QueryWrapper<DocStateEntity>().eq("doc_id",docId));
        docStateEntity.setDocStat(2);
        UpdateWrapper<DocStateEntity> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("doc_id",docId);
        docStateService.update(docStateEntity,updateWrapper);

        return R.ok();
    }

    @Override
    public R reject(Long annotateRecordId, Long userId) {
        AnnotatorRecordEntity record = annotatorRecordService
                .getOne(new QueryWrapper<AnnotatorRecordEntity>()
                        .eq("record_id", annotateRecordId));

        AnnotatorRecordEntity annotatorRecordEntity=new AnnotatorRecordEntity();

        Long docId=record.getDocId();
        annotatorRecordEntity.setDocId(docId);
        annotatorRecordEntity.setUserId(userId);
        annotatorRecordEntity.setAnnotatorTypeCode(1);
        annotatorRecordEntity.setStatus(1);
        annotatorRecordEntity.setTargetRecord(annotateRecordId);
        annotatorRecordService.save(annotatorRecordEntity);

        record.setStatus(1);
        annotatorRecordService.update(record,new UpdateWrapper<AnnotatorRecordEntity>().eq("record_id",annotateRecordId));


        DocStateEntity docStateEntity=docStateService.getOne(new QueryWrapper<DocStateEntity>().eq("doc_id",docId));
        docStateEntity.setDocStat(0);
        UpdateWrapper<DocStateEntity> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("doc_id",docId);
        docStateService.update(docStateEntity,updateWrapper);


        return R.ok();
    }

}