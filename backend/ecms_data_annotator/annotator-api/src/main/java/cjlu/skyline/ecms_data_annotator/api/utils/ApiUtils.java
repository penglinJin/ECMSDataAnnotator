package cjlu.skyline.ecms_data_annotator.api.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author 金鹏霖
 * @version 1.0
 * @description
 * @date 2021/3/3
 */
public class ApiUtils {

    @Value("${tmp.location}")
    private static String tmpLocation;

    public static File getExportJson() throws IOException {
        File file=new File(tmpLocation);
        if (file.exists()){
            file.delete();
        }
        FileWriter fileWriter=new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.write("12345\n22233");
        printWriter.close();

        return file;
    }

    public static String transToString(List<Long> labelList){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < labelList.size(); i++) {
            String s=labelList.get(i).toString();
            stringBuilder.append(s);
            if (i<labelList.size()-1){
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    public static List<Long> transToLabelInfo(String s){
        String[] split = s.split(",");
        List<Long> labelIdList = new ArrayList<>();
        Arrays.asList(split).forEach(i->{
            labelIdList.add(Long.valueOf(i));
        });
        return labelIdList;
    }

    public static Long getUniqId() {
        Random random=new Random();
        String nanoRandom = System.nanoTime() + "" + random.nextInt(99999);
            int hash = Math.abs(UUID.randomUUID().hashCode());
            int needAdd = 19 - String.valueOf(hash).length() + 1;
            String id=hash + "" + nanoRandom.substring(needAdd);
            String finalId;
            if (id.length()>=19){
                finalId=id.substring(0,18);
            }else {
                finalId=id;
            }
            return Long.valueOf(finalId);
    }


    /**
     * convert Type
     * @author 金鹏霖
     * @date 2021/3/8
     * @param source
    targetClass
     * @return Target
     */
    public static final <Target>Target copyProperties(Object source,Class<Target> targetClass){
        try {
            if(source==null || targetClass==null){
                return null;
            }
            Target doInstance = targetClass.newInstance();
            BeanUtils.copyProperties(source, doInstance);
            return doInstance;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
