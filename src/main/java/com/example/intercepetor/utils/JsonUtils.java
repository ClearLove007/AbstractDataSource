package com.example.intercepetor.utils;

import com.example.intercepetor.common.ServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 10:45 2019/11/29
 */
public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static  <T> T jsonToObject(String json, Class<T> clazz){
        T result = null;
        try {
            result = mapper.readValue(json, clazz);
        } catch (JsonProcessingException e){
            throw new ServiceException("json转换object失败,json："+json + " object:"+clazz.getName());
        }
        return result;
    }

    public static String objectToJson(Object value){

        String result = null;
        try {
            result = mapper.writeValueAsString(value);
        } catch (JsonProcessingException e){
            throw new ServiceException("object转换json失败");
        }
        return result;
    }
}
