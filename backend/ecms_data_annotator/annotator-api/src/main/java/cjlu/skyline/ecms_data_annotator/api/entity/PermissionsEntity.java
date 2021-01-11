package cjlu.skyline.ecms_data_annotator.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author jinpenglin
 * @email nsp4289@autuni.ac.nz
 * @date 2021-01-11 15:35:16
 */
@Data
@TableName("permissions")
public class PermissionsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 权限
	 */
	@TableId
	private Long permissionsId;
	/**
	 * 
	 */
	private Long userId;
	/**
	 * 
	 */
	private Long projectId;
	/**
	 * 1为管理，2为注释，3为审批
	 */
	private Long roleId;

}
