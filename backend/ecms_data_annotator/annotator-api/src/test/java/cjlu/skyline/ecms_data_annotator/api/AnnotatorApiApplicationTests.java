package cjlu.skyline.ecms_data_annotator.api;

import cjlu.skyline.ecms_data_annotator.api.service.DocService;
import cjlu.skyline.ecms_data_annotator.api.utils.ApiUtils;
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

    @Test
    public void testAn(){
        Long[] docIds=new Long[1];
        docIds[0]=Long.parseLong("697245309510097996");

        docService.deleteBatch(docIds);
    }
}
