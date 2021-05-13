package cjlu.skyline.ecms_data_annotator.api.controller;

import cjlu.skyline.ecms_data_annotator.common.utils.R;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author 金鹏霖
 * @version 1.0
 * @description
 * @date 2021/5/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SrcDocControllerTest {

    @Autowired
    SrcDocController srcDocController;

//    @Test
//    public void downloadFile() {
//        ResponseEntity<FileSystemResource> responseEntity = srcDocController.downloadFile();
//        String path = responseEntity.getBody().getFile().getAbsolutePath();
//        System.out.println(path);
//        Assert.assertEquals("E:\\tmp\\export.json",path);
//    }

//    @Test
//    public void annotate() {
//        Long[] labelIds=new Long[1];
//        labelIds[0]=Long.parseLong("12");
//
//        Long userId1 = Long.valueOf("2");
//
//        Long docId=Long.valueOf("228861521824418");
//
//        String htmlContent="";
//
//        R annotate = srcDocController.annotate(labelIds, userId1, docId, htmlContent);
//
//        Assert.assertEquals(annotate.get("code"),0);
//    }

//    @Test
//    public void processDataset() {
//        String filePath="https://ecms-annotator.oss-cn-hangzhou.aliyuncs.com/2021-05-12/xxx_test11.txt";
//        Long userId=Long.parseLong("2");
//        R r = srcDocController.processDataset(filePath, userId);
//        String msg="txt file process success";
//        Assert.assertEquals(msg,r.get("msg"));
//    }
}