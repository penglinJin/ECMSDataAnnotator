package cjlu.skyline.ecms_data_annotator.api.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.Query;

import cjlu.skyline.ecms_data_annotator.api.dao.RoleDao;
import cjlu.skyline.ecms_data_annotator.api.entity.RoleEntity;
import cjlu.skyline.ecms_data_annotator.api.service.RoleService;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<RoleEntity> page = this.page(
                new Query<RoleEntity>().getPage(params),
                new QueryWrapper<RoleEntity>()
        );

        return new PageUtils(page);
    }

}