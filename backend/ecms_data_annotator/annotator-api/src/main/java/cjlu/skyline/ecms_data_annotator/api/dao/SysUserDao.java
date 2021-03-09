package cjlu.skyline.ecms_data_annotator.api.dao;

import cjlu.skyline.ecms_data_annotator.api.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户
 * 
 * @author jinpenglin
 * @email nsp4289@autuni.ac.nz
 * @date 2021-03-09 23:46:56
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {
	
}
