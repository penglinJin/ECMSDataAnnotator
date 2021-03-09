package cjlu.skyline.ecms_data_annotator.api;

import cjlu.skyline.ecms_data_annotator.api.entity.AnnotatorRecordEntity;
import cjlu.skyline.ecms_data_annotator.api.entity.DocEntity;
import cjlu.skyline.ecms_data_annotator.api.service.AnnotatorRecordService;
import cjlu.skyline.ecms_data_annotator.api.service.DocService;
import cjlu.skyline.ecms_data_annotator.api.service.LabelInfoService;
import cjlu.skyline.ecms_data_annotator.api.utils.ApiUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnotatorApiApplicationTests {
    public AnnotatorApiApplicationTests(){}

    @Autowired
    DocService docService;

    @Autowired
    AnnotatorRecordService annotatorRecordService;

    @Test
    public void testAn(){
        //docService.queryPrePage();
//        QueryWrapper<AnnotatorRecordEntity> queryWrapper=new QueryWrapper();
//        Long id=Long.valueOf("160314514860059786");
//        queryWrapper.eq("doc_id",id).eq("label_id",7);
//        AnnotatorRecordEntity one = annotatorRecordService.getOne(queryWrapper);
//        System.out.println(one);
//        System.out.println(ApiUtils.transToLabelInfo(one.getNewLabels()));
    }
}
