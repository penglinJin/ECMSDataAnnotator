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
@TableName("doc")
public class DocEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long docId;
	/**
	 * 
	 */
	private String docName;
	/**
	 * 原文件id

	 */
	private Long srcDocId;
	/**
	 * 
	 */
	private String docContent;
	/**
	 * 0文本1图片
	 */
	private Integer docType;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Long createUserId;

}
