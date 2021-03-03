package cjlu.skyline.ecms_data_annotator.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnotatorApiApplicationTests {
    public AnnotatorApiApplicationTests(){}

    @Test
    public void testAn(){
        URL url = null;

        try {
            url = new URL("https://ecms-annotator.oss-cn-hangzhou.aliyuncs.com/2021-03-03/933317aa-101a-428c-8bc6-dd2ad96c614d_huajianji-8-juan.json");   //想要读取的url地址

            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String urlString = "";
            String current;
            while ((current = in.readLine()) != null) {
                urlString += current;
            }

//            //写到本地
//            File fp = new File("D:/test.java");
//            OutputStream os = new FileOutputStream(fp);
//            os.write(urlString.getBytes());
//            os.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
