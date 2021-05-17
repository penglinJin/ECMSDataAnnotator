package cjlu.skyline.ecms_data_annotator.api.utils;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.BeanUtils;

import java.util.Map;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.util.*;

/**
 * @author 金鹏霖
 * @version 1.0
 * @description
 * @date 2021/3/3
 */
public class ApiUtils {


    private static JacksonXmlModule module = new JacksonXmlModule();
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static XmlMapper xmlMapper = new XmlMapper(module);
    static{
        //去掉xml顶部内容<?xml version="1.0" encoding="UTF-8" ?>
        module.setDefaultUseWrapper(false);
    }

    /**
     * xml字符串转换成net.sf.json.JSONObject对象
     * @param xmlStr
     * @return
     */
    public static JSONObject xml2jsonObj(String xmlStr){
        JSONObject jsonObject = XML.toJSONObject(xmlStr);
        return jsonObject;
    }

    /**
     * xml字符串转换成json字符串
     * @param xmlStr
     * @return
     */
    public static String xml2jsonStr(String xmlStr){
        JSONObject jsonObject = XML.toJSONObject(xmlStr);
        return jsonObject.toString();
    }

    /**
     * json字符串转换成xml字符串
     * @param jsonStr
     * @return
     */
    public static String json2xml(String jsonStr){
        try {
            JsonNode root = objectMapper.readTree(jsonStr);
            String result = xmlMapper.writeValueAsString(root);
            return result.replaceAll("</?ObjectNode>","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * map数据转换成xml字符串
     * @param mapData
     * @return
     */
    public static String map2xml(Map<String,Object> mapData){
        Object repinfoJsonObj = JSONObject.wrap(mapData);
        String jsonStr=repinfoJsonObj.toString();
        String result=json2xml(jsonStr);
        return result;
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
