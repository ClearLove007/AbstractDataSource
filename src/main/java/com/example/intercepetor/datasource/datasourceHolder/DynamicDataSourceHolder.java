package com.example.intercepetor.datasource.datasourceHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 15:25 2019/11/29
 */
@Slf4j
public class DynamicDataSourceHolder {

    private static final ThreadLocal<String> HOLDER = new ThreadLocal<String>();

    public static void putDataKey(String key){
        log.info("设置数据源:{}",key);
        HOLDER.set(key);
    }

    public static String getDateKey(){
        return HOLDER.get();
    }

    public static void clearData(){
        log.info("清除数据源:{}",getDateKey());
        HOLDER.remove();
    }
}
