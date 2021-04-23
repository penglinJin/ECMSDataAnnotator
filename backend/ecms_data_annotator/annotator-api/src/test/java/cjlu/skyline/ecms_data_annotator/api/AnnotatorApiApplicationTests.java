package cjlu.skyline.ecms_data_annotator.api;


import cjlu.skyline.ecms_data_annotator.api.dto.AnnotationDto;
import cjlu.skyline.ecms_data_annotator.api.entity.AnnotatorRecordEntity;
import cjlu.skyline.ecms_data_annotator.api.service.AnnotatorRecordService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnotatorApiApplicationTests {
    public AnnotatorApiApplicationTests(){}

//    @Autowired
//    DocService docService;
//
    @Autowired
    AnnotatorRecordService annotatorRecordService;
//
//    @Autowired
//    LabelInfoService labelInfoService;
//
//    @Autowired
//    SysUserService sysUserService;
@Value("${tmp.location}")
private String tmpLocation;

    @Test
    public void testAn() throws IOException {

        List<AnnotatorRecordEntity> list = annotatorRecordService.list();
        Map<Long, List<AnnotatorRecordEntity>> collect = list.stream().collect(Collectors.groupingBy(AnnotatorRecordEntity::getUserId));
        collect.forEach((k,v)->{
            int i=0;
            System.out.println("userId:"+k+" value:"+v);
            for (AnnotatorRecordEntity recordEntity : v) {
                if (recordEntity.getAnnotatorTypeCode() == 0)
                    i++;
            }
            System.out.println(k+" "+i);
        });


//        for (AnnotatorRecordEntity annotatorRecordEntity : list) {
//            if (annotatorRecordEntity.getAnnotatorTypeCode() == 0){
//                Long userId = annotatorRecordEntity.getUserId();
//                i++;
//                System.out.println(userId+ ":"+i);
//
//            }
//
//        }
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

//        String str="{\"text\": \"Terrible customer service.\", \"labels\": [\"negative\"]} ";
//        JSONObject jsonObject = JSONObject.parseObject(str);
//        String text=jsonObject.getString("text");
//        List<String> labelsList =jsonObject.getJSONArray("labels").toJavaList(String.class);
//        System.out.println(text);
//        System.out.println(labelsList);

//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("text","12345");
//        JSONArray jsonArray = new JSONArray();
//        AnnotationDto annotationDto = new AnnotationDto();
//        annotationDto.setCreatedAt(new Date());
//        annotationDto.setLabelName("positive");
//        annotationDto.setLabelId(Long.parseLong("1"));
//        jsonArray.add(annotationDto);
//        jsonObject.put("annotations",jsonArray);
//        System.out.println(jsonObject);



//            File file=new File(tmpLocation);
//            if (file.exists()){
//                file.delete();
//            }
//            FileWriter fileWriter=new FileWriter(file);
//            PrintWriter printWriter = new PrintWriter(fileWriter);
//            printWriter.write("12345\n2223332");
//            printWriter.close();


        //file.delete();


    }
}
