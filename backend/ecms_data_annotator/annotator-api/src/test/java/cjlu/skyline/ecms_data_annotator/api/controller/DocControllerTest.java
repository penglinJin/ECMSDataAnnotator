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

//    @Test
//    public void approve(){
//        Long recordId=Long.parseLong("129");
//        Long userId=Long.parseLong("2");
//        R approve = docController.approve(recordId, userId);
//        Assert.assertEquals(0,approve.get("code"));
//
//    }
//
//    @Test
//    public void reject(){
//        Long recordId=Long.parseLong("130");
//        Long userId=Long.parseLong("2");
//        R approve = docController.reject(recordId, userId);
//        Assert.assertEquals(0,approve.get("code"));
//    }

    @Test
    public void approvalList() {
        Map<String, Object> params=new HashMap<>();
        params.put("page","1");
        params.put("limit","10");
        params.put("content","");
        R r = docController.approvalList(params);
        PageUtils page = (PageUtils) r.get("page");
        List<?> list = page.getList();
        list.forEach(System.out::println);
        Assert.assertNotNull(list);
    }

    @Test
    public void preList() {
        Map<String, Object> params=new HashMap<>();
        params.put("page","1");
        params.put("limit","10");
        params.put("content","");
        R r = docController.preList(params);
        PageUtils page = (PageUtils) r.get("page");
        List<?> list = page.getList();
        list.forEach(System.out::println);
        Assert.assertNotNull(list);
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
        Assert.assertNotNull(list1);
    }

}