package cjlu.skyline.ecms_data_annotator.api.service.impl;

import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import cjlu.skyline.ecms_data_annotator.api.dao.AnnotatorRecordDao;
import cjlu.skyline.ecms_data_annotator.api.entity.AnnotatorRecordEntity;
import cjlu.skyline.ecms_data_annotator.api.service.AnnotatorRecordService;


@Service("annotatorRecordService")
public class AnnotatorRecordServiceImpl extends ServiceImpl<AnnotatorRecordDao, AnnotatorRecordEntity> implements AnnotatorRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AnnotatorRecordEntity> page = this.page(
                new Query<AnnotatorRecordEntity>().getPage(params),
                new QueryWrapper<AnnotatorRecordEntity>()
        );

        return new PageUtils(page);
    }

}