package com.example.intercepetor.dto;

import org.springframework.util.ObjectUtils;

import java.util.HashMap;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 9:32 2019/11/29
 */
public class RequestDTO extends HashMap {

    public String getString(String key){
        Object obj = this.get(key);
        if (ObjectUtils.isEmpty(obj)){
            return null;
        }
        if (obj instanceof String){
            return (String)obj;
        } else {
            return obj.toString();
        }
    }
}
