package cjlu.skyline.ecms_data_annotator.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * 
 * @author jinpenglin
 * @email nsp4289@autuni.ac.nz
 * @date 2021-01-11 15:35:15
 */
@Data
@TableName("annotator_record")
public class AnnotatorRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long recordId;
	/**
	 * 
	 */
	private Long labelId;
	/**
	 * 
	 */
	private Long userId;
	/**
	 * 
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long docId;
	/**
	 * 0更新1删除
	 */
	private Integer annotatorTypeCode;
	/**
	 * 
	 */
	private Integer startOffset;
	/**
	 * 
	 */
	private Integer endOffset;
	/**
	 * 
	 */
	private BigDecimal prob;

	/**
	 *
	 */
	private String oldLabels;

	/**
	 *
	 */
	private String newLabels;

	/**
	 *
	 */
	private Long targetRecord;

	/**
	 * 0未处理1已处理
	 */
	private Integer status;

	private Date createTime;
}
