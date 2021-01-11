package cjlu.skyline.ecms_data_annotator.api.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.Query;

import cjlu.skyline.ecms_data_annotator.api.dao.PermissionsDao;
import cjlu.skyline.ecms_data_annotator.api.entity.PermissionsEntity;
import cjlu.skyline.ecms_data_annotator.api.service.PermissionsService;


@Service("permissionsService")
public class PermissionsServiceImpl extends ServiceImpl<PermissionsDao, PermissionsEntity> implements PermissionsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PermissionsEntity> page = this.page(
                new Query<PermissionsEntity>().getPage(params),
                new QueryWrapper<PermissionsEntity>()
        );

        return new PageUtils(page);
    }

}