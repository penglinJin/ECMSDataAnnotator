package cjlu.skyline.ecms_data_annotator.api.service.impl;

import cjlu.skyline.ecms_data_annotator.api.dao.AnnotatorRecordDao;
import cjlu.skyline.ecms_data_annotator.api.dao.DocLabelDao;
import cjlu.skyline.ecms_data_annotator.api.entity.*;
import cjlu.skyline.ecms_data_annotator.api.service.DocLabelService;
import cjlu.skyline.ecms_data_annotator.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.Query;

import cjlu.skyline.ecms_data_annotator.api.dao.LabelInfoDao;
import cjlu.skyline.ecms_data_annotator.api.service.LabelInfoService;


@Service("labelInfoService")
public class LabelInfoServiceImpl extends ServiceImpl<LabelInfoDao, LabelInfoEntity> implements LabelInfoService {

    @Autowired
    LabelInfoDao labelInfoDao;

    @Autowired
    DocLabelDao docLabelDao;

    @Autowired
    AnnotatorRecordDao annotatorRecordDao;

    @Autowired
    DocLabelService docLabelService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LabelInfoEntity> page = this.page(
                new Query<LabelInfoEntity>().getPage(params),
                new QueryWrapper<LabelInfoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public R deleteBatch(Long[] labelIds) {
        Arrays.stream(labelIds).forEach(id->{
            QueryWrapper<LabelInfoEntity> labelQueryWrapper=new QueryWrapper<>();
            labelQueryWrapper.eq("label_id",id);
            labelInfoDao.delete(labelQueryWrapper);

            QueryWrapper<DocLabelEntity> docLabelQueryWrapper=new QueryWrapper<>();
            docLabelQueryWrapper.eq("label_id",id);
            docLabelDao.delete(docLabelQueryWrapper);

            QueryWrapper<AnnotatorRecordEntity> annotateQueryWrapper=new QueryWrapper<>();
            annotateQueryWrapper.eq("label_id",id);
            annotatorRecordDao.delete(annotateQueryWrapper);

        });
        return R.ok();
    }

    @Override
    public List<Long> getOldLabels(Long docId) {
        List<DocLabelEntity> docLabelList = docLabelService.list(new QueryWrapper<DocLabelEntity>().eq("doc_id", docId));
        List<Long> oldLabelList = new ArrayList<>();
        docLabelList.forEach(i->{
            oldLabelList.add(i.getLabelId());
        });
        return oldLabelList;
    }

    @Override
    public LabelInfoEntity getOneByName(String name) {
        return this.getOne(new QueryWrapper<LabelInfoEntity>().eq("label_content",name));
    }

}