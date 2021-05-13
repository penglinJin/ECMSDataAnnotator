package cjlu.skyline.ecms_data_annotator.api.controller;

import cjlu.skyline.ecms_data_annotator.common.utils.PageUtils;
import cjlu.skyline.ecms_data_annotator.common.utils.R;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LabelInfoControllerTest {


    @Autowired
    LabelInfoController labelInfoController;

    @Test
    public void getOldLabels() {
        long docId = 169942510584900148L;
        R oldLabels = labelInfoController.getOldLabels(docId);
        System.out.println(oldLabels.get("oldLabels"));
        Assert.assertEquals(1,((List)oldLabels.get("oldLabels")).size());
    }

    @Test
    public void list() {
        Map<String, Object> params=new HashMap<>();
        params.put("page","1");
        params.put("limit","10");
        params.put("content","");

        R list = labelInfoController.list(params);
        PageUtils page = (PageUtils) list.get("page");
        List<?> list1 = page.getList();
        list1.forEach(System.out::println);
        Assert.assertNotNull(list1);
    }

    @Test
    public void info() {
        long labelID = 11L;
        R info = labelInfoController.info(labelID);
        Assert.assertNotNull(info.get("labelInfo"));
        System.out.println(info.get("labelInfo"));

    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void deleteBatch() {
    }
}