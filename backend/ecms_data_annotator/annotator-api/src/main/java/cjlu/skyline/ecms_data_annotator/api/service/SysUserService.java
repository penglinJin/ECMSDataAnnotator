package cjlu.skyline.ecms_data_annotator.api.service;

import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import cjlu.skyline.ecms_data_annotator.api.entity.SysUserEntity;

import java.util.Map;

/**
 * 系统用户
 *
 * @author jinpenglin
 * @email nsp4289@autuni.ac.nz
 * @date 2021-03-09 23:46:56
 */
public interface SysUserService extends IService<SysUserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

