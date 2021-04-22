package cjlu.skyline.ecms_data_annotator.api.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author 金鹏霖
 * @version 1.0
 * @description
 * @date 2021/4/12
 */
@Data
public class AnnotationDto {
    private Long labelId;
    private String labelName;
    private Long userId;
    private Date createdAt;
}
