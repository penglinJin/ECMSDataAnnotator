package cjlu.skyline.ecms_data_annotator.api.utils;

import org.springframework.beans.BeanUtils;

import java.util.Random;
import java.util.UUID;

/**
 * @author 金鹏霖
 * @version 1.0
 * @description
 * @date 2021/3/3
 */
public class ApiUtils {
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
