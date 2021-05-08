package cjlu.skyline.ecms_data_annotator.api.dto;

import lombok.Data;

import java.util.List;

/**
 * @author 金鹏霖
 * @version 1.0
 * @description
 * @date 2021/4/12
 */

@Data
public class JsonDto {
    private String text;
    private List<String> labels;
}
