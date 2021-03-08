package cjlu.skyline.ecms_data_annotator.api.vo;

import cjlu.skyline.ecms_data_annotator.api.entity.DocEntity;

import lombok.Data;


/**
 * @author 金鹏霖
 * @version 1.0
 * @description
 * @date 2021/3/8
 */

@Data
public class DocVo extends DocEntity {
    private static final long serialVersionUID = 1L;

    private String docState;


}
