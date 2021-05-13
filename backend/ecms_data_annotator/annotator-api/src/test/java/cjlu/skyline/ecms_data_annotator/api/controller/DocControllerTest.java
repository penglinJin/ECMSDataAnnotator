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

/**
 * @author 金鹏霖
 * @version 1.0
 * @description
 * @date 2021/5/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DocControllerTest {

    @Autowired
    DocController docController;

    @Test
    public void reject() {
    }

    @Test
    public void rejectBatch() {
    }

    @Test
    public void approveBatch() {
    }

    @Test
    public void approve() {
    }

    @Test
    public void approvalList() {
    }

    @Test
    public void preList() {
    }

    @Test
    public void list() {
        Map<String, Object> params=new HashMap<>();
        params.put("page","1");
        params.put("limit","10");
        params.put("content","");
        R list = docController.list(params);
        PageUtils page = (PageUtils) list.get("page");
        List<?> list1 = page.getList();
        list1.forEach(System.out::println);
        Assert.assertNotNull(list1);
    }

    @Test
    public void info() {
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