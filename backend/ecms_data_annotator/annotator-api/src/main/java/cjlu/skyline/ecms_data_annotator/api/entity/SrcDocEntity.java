package cjlu.skyline.ecms_data_annotator.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * 
 * @author jinpenglin
 * @email nsp4289@autuni.ac.nz
 * @date 2021-01-11 15:35:16
 */
@Data
@TableName("src_doc")
public class SrcDocEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 原文件id
	 */
	@TableId(value = "src_doc_id",type = IdType.INPUT)
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long srcDocId;
	/**
	 * 
	 */
	private String srcDocName;
	/**
	 * 原文件存储路径
	 */
	private String srcDocPath;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Long createUserId;
	/**
	 * 0文件1图片
	 */
	private Integer docType;

}
