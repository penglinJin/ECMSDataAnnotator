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
@TableName("doc_label")
public class DocLabelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 文件注释关联
	 */
	@TableId
	private Long docLabelId;
	/**
	 * 
	 */
	private Long docId;
	/**
	 * 
	 */
	private Long labelId;
	/**
	 * start index of the annotate area
	 */
	private Long startOffset;
	/**
	 * end index of the annotate area
	 */
	private Long endOffset;

}
