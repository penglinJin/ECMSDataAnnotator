package cjlu.skyline.ecms_data_annotator.api.vo;

import cjlu.skyline.ecms_data_annotator.api.entity.AnnotatorRecordEntity;
import lombok.Data;


/**
 * @author 金鹏霖
 * @version 1.0
 * @description
 * @date 2021/3/9
 */

@Data
public class ApproveVo extends AnnotatorRecordEntity {

    private String createUserName;

    private String oldLabelNames;

    private String updatedLabelNames;

    private String docContent;

    private String htmlContent;

}
