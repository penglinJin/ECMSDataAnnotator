package cjlu.skyline.ecms_data_annotator.api.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @author 金鹏霖
 * @version 1.0
 * @description
 * @date 2021/3/3
 */
public class ApiUtils {
    public static long getUniqId() {
        Random random=new Random();
        String nanoRandom = System.nanoTime() + "" + random.nextInt(99999);
        int hash = Math.abs(UUID.randomUUID().hashCode());
        int needAdd = 19 - String.valueOf(hash).length() + 1;
        return Long.valueOf(hash + "" + nanoRandom.substring(needAdd));
    }
}
