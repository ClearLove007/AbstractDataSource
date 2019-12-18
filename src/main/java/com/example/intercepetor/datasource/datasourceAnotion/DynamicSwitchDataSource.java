package com.example.intercepetor.datasource.datasourceAnotion;

import java.lang.annotation.*;

/**
 * @Author: XueWeiDong
 * @Description: 创建拦截设置数据源的注解
 * @Date: 16:40 2019/11/29
 * {@link com.example.intercepetor.datasource.datasourceAnotion.HandlerDataSourceAop}
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicSwitchDataSource {
    String dataSource() default "";
}
