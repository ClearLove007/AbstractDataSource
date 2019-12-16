package com.example.intercepetor.datasource.datasourceAnotion;

import com.example.intercepetor.datasource.datasourceHolder.DynamicDataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @Author: XueWeiDong
 * @Description: 使用aop拦截使用@DynamicSwitchDataSource
 * @Date: 16:43 2019/11/29
 */
@Aspect
@Slf4j
@Component
@Order(1)
public class HandlerDataSourceAop {

    public static final HashMap DATA_SOURCE_SUM = new HashMap();

    @Pointcut("@within(com.example.intercepetor.datasource.datasourceAnotion.DynamicSwitchDataSource)||@annotation(com.example.intercepetor.datasource.datasourceAnotion.DynamicSwitchDataSource)")
    public void handlerDataSourceAop(){}

    @Before("handlerDataSourceAop()")
    public void doBfore(JoinPoint joinPoint){
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        //获取方法上的注解
        DynamicSwitchDataSource annotation = method.getAnnotation(DynamicSwitchDataSource.class);
        if (null == annotation){
            return;
        }
        //获取注解上的数据源的值的信息
        String dataSourceKey = annotation.dataSource();
        //检验数据源是否存在
        if (!StringUtils.isEmpty(dataSourceKey) && DATA_SOURCE_SUM.containsKey(dataSourceKey)){
            DynamicDataSourceHolder.putDataKey(dataSourceKey);
        } else {
            log.info("没有指定的数据源, 默认走主数据源");
            return;
        }
        log.info("成功切换数据源，当前数据源:{}",DynamicDataSourceHolder.getDateKey());
    }

    @After("handlerDataSourceAop()")
    public void doAfter(){
        DynamicDataSourceHolder.clearData();
    }
}
