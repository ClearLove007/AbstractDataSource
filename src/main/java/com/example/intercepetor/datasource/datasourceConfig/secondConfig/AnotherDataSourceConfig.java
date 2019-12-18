package com.example.intercepetor.datasource.datasourceConfig.secondConfig;

import com.example.intercepetor.common.SystemConsts;
import com.example.intercepetor.datasource.datasourceAnotion.HandlerDataSourceAop;
import com.example.intercepetor.datasource.datasourceHolder.MyDynamicDataSource;
import org.apache.commons.collections4.MapUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;



/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 14:02 2019/12/2
 */
@Configuration
@MapperScan(value = "com.example.intercepetor.mapper")
@EnableConfigurationProperties(DataSourceDTO.class)
public class AnotherDataSourceConfig {


    @Bean("datasourceMap")
    public Map<Object, Object> targetDataSource(DataSourceDTO dataSourceDTO){
        Map<Object, Object> map = new HashMap<>();
        Map<String, Map<String, String>> dataProperties = dataSourceDTO.getDatasource();
        dataProperties.forEach((key, value)->{
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(MapUtils.getString(value, SystemConsts.DataSourceConfig.driver));
            dataSource.setUrl(MapUtils.getString(value, SystemConsts.DataSourceConfig.url));
            dataSource.setUsername(MapUtils.getString(value, SystemConsts.DataSourceConfig.username));
            dataSource.setPassword(MapUtils.getString(value, SystemConsts.DataSourceConfig.password));
            map.put(key, dataSource);
            //统计数据源
            HandlerDataSourceAop.setDataSource(key);
        });
        return map;
    }

    @Bean("myDynamicDataSource")
    public MyDynamicDataSource dataSource(@Qualifier("datasourceMap") Map<Object, Object> targetDataSource){
        MyDynamicDataSource dynamicDataSource = new MyDynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSource);
        dynamicDataSource.setDefaultTargetDataSource(targetDataSource.get("master"));
        return dynamicDataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("myDynamicDataSource") DataSource dynamicDataSource)throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        return bean.getObject();
    }
}

