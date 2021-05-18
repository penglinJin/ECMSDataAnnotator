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

    private String labels;

    private String docState;

    private String img;

    @Override
    public String toString() {
        return "DocEntity{" +
                "docId=" + getDocId() +
                ", docName='" + getDocName() + '\'' +
                ", srcDocId=" + getSrcDocId() +
                ", docContent='" + getDocContent() + '\'' +
                ", docType=" + getDocType() +
                ", createTime=" + getCreateTime() +
                ", createUserId=" + getCreateUserId() +
                ", nlpLabel='" + getNlpLabel() + '\'' +
                ", htmlContent='" + getHtmlContent() + '\'' +
                ", labels='" + labels + '\'' +
                ", docState='" + docState + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
