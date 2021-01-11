package cjlu.skyline.ecms_data_annotator.api.service.impl;

import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import cjlu.skyline.ecms_data_annotator.api.dao.DocLabelDao;
import cjlu.skyline.ecms_data_annotator.api.entity.DocLabelEntity;
import cjlu.skyline.ecms_data_annotator.api.service.DocLabelService;


@Service("docLabelService")
public class DocLabelServiceImpl extends ServiceImpl<DocLabelDao, DocLabelEntity> implements DocLabelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DocLabelEntity> page = this.page(
                new Query<DocLabelEntity>().getPage(params),
                new QueryWrapper<DocLabelEntity>()
        );

        return new PageUtils(page);
    }

}