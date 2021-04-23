package cjlu.skyline.ecms_data_annotator.api;


import cjlu.skyline.ecms_data_annotator.api.dto.AnnotationDto;
import cjlu.skyline.ecms_data_annotator.api.entity.AnnotatorRecordEntity;
import cjlu.skyline.ecms_data_annotator.api.entity.SysUserEntity;
import cjlu.skyline.ecms_data_annotator.api.service.AnnotatorRecordService;
import cjlu.skyline.ecms_data_annotator.api.service.SrcDocService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnotatorApiApplicationTests {
    public AnnotatorApiApplicationTests(){}


    @Autowired
    SrcDocService srcDocService;
//    @Autowired
//    DocService docService;

//    @Autowired
//    AnnotatorRecordService annotatorRecordService;
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

//        QueryWrapper<AnnotatorRecordEntity> queryWrapper=new QueryWrapper<>();
//        List<AnnotatorRecordEntity> list = annotatorRecordService.list();
//        List<AnnotatorRecordEntity> es=new ArrayList<>();
//        list.forEach(item->{
//            String newLabels = item.getNewLabels();
//            if (!StringUtils.isEmpty(newLabels)){
//                String[] split = newLabels.split(",");
//                List<String> collect = Arrays.stream(split).filter(e -> e.equals("11")).collect(Collectors.toList());
//                if (collect.size()>0){
//                    es.add(item);
//                }
//            }
//        });
//
//        List<AnnotatorRecordEntity> collect = es.stream().sorted(Comparator.comparing(AnnotatorRecordEntity::getCreateTime).reversed()).collect(Collectors.toList());
//        collect.forEach(System.out::println);

        System.out.println(tmpLocation);
        ResponseEntity<FileSystemResource> fileSystemResourceResponseEntity = srcDocService.downloadFile(tmpLocation);
        System.out.println(fileSystemResourceResponseEntity);

    }
}
