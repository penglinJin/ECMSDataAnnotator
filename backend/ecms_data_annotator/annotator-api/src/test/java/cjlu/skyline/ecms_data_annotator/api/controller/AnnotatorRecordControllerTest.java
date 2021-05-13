package cjlu.skyline.ecms_data_annotator.api.controller;

import cjlu.skyline.ecms_data_annotator.api.service.LabelInfoService;
import cjlu.skyline.ecms_data_annotator.api.service.SysUserService;
import cjlu.skyline.ecms_data_annotator.api.vo.StaticsVo;
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
public class AnnotatorRecordControllerTest {

    @Autowired
    AnnotatorRecordController annotatorRecordController;

    @Autowired
    LabelInfoService labelInfoService;

    @Test
    public void approvalList() {
        Map<String, Object> params=new HashMap<>();
        params.put("page","1");
        params.put("limit","10");
        params.put("content","");
        R r = annotatorRecordController.approvalList(params);
        PageUtils page = (PageUtils) r.get("page");
        List<?> list = page.getList();
        list.forEach(System.out::println);
        Assert.assertNotNull(list);

    }

    @Test
    public void completeSituation() {
        R r = annotatorRecordController.completeSituation();
        List<StaticsVo> completeSituation = (List<StaticsVo>) r.get("completeSituation");
        completeSituation.forEach(System.out::println);
        Assert.assertEquals(3,completeSituation.size());
    }

    @Test
    public void annotationNum() {
        R r = annotatorRecordController.annotationNum();
        List<StaticsVo> numStatics = (List<StaticsVo>) r.get("numStatics");
        numStatics.forEach(System.out::println);
        Assert.assertNotNull(numStatics.size());
    }

    @Test
    public void labelStatics() {
        R r = annotatorRecordController.labelStatics();
        List<StaticsVo> labelStatics = (List<StaticsVo>) r.get("labelStatics");
        labelStatics.forEach(System.out::println);
        Assert.assertEquals(labelInfoService.list().size(),labelStatics.size());
    }
}