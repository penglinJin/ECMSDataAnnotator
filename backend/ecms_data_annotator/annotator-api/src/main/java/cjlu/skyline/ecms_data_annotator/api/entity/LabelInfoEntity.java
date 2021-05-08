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
 * @date 2021-01-11 15:35:15
 */
@Data
@TableName("label_info")
public class LabelInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long labelId;
	/**
	 * 
	 */
	private String labelContent;
	/**
	 * 
	 */
	private Long createUserId;
	/**
	 * 
	 */
	private String textColor;
	/**
	 * 
	 */
	private String prefixKey;
	/**
	 * 
	 */
	private String suffixKey;
	/**
	 * 
	 */
	private String backgroundColor;

}
