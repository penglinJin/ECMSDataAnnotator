package cjlu.skyline.ecms_data_annotator.api.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.Query;

import cjlu.skyline.ecms_data_annotator.api.dao.DocStateDao;
import cjlu.skyline.ecms_data_annotator.api.entity.DocStateEntity;
import cjlu.skyline.ecms_data_annotator.api.service.DocStateService;


@Service("docStateService")
public class DocStateServiceImpl extends ServiceImpl<DocStateDao, DocStateEntity> implements DocStateService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DocStateEntity> page = this.page(
                new Query<DocStateEntity>().getPage(params),
                new QueryWrapper<DocStateEntity>()
        );

        return new PageUtils(page);
    }

}