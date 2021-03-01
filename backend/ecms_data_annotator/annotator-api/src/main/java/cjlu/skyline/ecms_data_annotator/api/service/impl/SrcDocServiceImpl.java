package cjlu.skyline.ecms_data_annotator.api.service.impl;

import cjlu.skyline.ecms_data_annotator.common.utils.R;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.Query;

import cjlu.skyline.ecms_data_annotator.api.dao.SrcDocDao;
import cjlu.skyline.ecms_data_annotator.api.entity.SrcDocEntity;
import cjlu.skyline.ecms_data_annotator.api.service.SrcDocService;


@Service("srcDocService")
public class SrcDocServiceImpl extends ServiceImpl<SrcDocDao, SrcDocEntity> implements SrcDocService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SrcDocEntity> page = this.page(
                new Query<SrcDocEntity>().getPage(params),
                new QueryWrapper<SrcDocEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public R processDataset(File file) {
        SrcDocEntity srcDoc=new SrcDocEntity();
        srcDoc.setCreateTime(new Date());
        return null;
    }

}