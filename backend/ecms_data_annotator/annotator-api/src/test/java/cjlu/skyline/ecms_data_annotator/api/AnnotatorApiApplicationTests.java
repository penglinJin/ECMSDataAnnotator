package cjlu.skyline.ecms_data_annotator.api;

import cjlu.skyline.ecms_data_annotator.api.entity.SysUserEntity;
import cjlu.skyline.ecms_data_annotator.api.service.AnnotatorRecordService;
import cjlu.skyline.ecms_data_annotator.api.service.DocService;
import cjlu.skyline.ecms_data_annotator.api.service.LabelInfoService;
import cjlu.skyline.ecms_data_annotator.api.service.SysUserService;
import cjlu.skyline.ecms_data_annotator.common.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnotatorApiApplicationTests {
    public AnnotatorApiApplicationTests(){}

//    @Autowired
//    DocService docService;
//
//    @Autowired
//    AnnotatorRecordService annotatorRecordService;
//
//    @Autowired
//    LabelInfoService labelInfoService;
//
//    @Autowired
//    SysUserService sysUserService;

    @Test
    public void testAn(){

//        Long id=Long.valueOf("1");
//        SysUserEntity user = sysUserService.getOne(new QueryWrapper<SysUserEntity>().eq("user_id", id));
//        System.out.println(user);
        //docService.queryPrePage();
//        QueryWrapper<AnnotatorRecordEntity> queryWrapper=new QueryWrapper();
//        Long id=Long.valueOf("160314514860059786");
//        queryWrapper.eq("doc_id",id).eq("label_id",7);
//        AnnotatorRecordEntity one = annotatorRecordService.getOne(queryWrapper);
//        System.out.println(one);
//        List<Long> longs = ApiUtils.transToLabelInfo(one.getNewLabels());
//        System.out.println(longs);
//        System.out.println(ApiUtils.transToString(longs));
//        Long id=Long.valueOf("160314514860059786");
//        List<Long> oldLabels = labelInfoService.getOldLabels(id);
//        System.out.println(oldLabels);
    }
}
