package com.example.intercepetor.datasource.datasourceConfig.secondConfig;

import com.example.intercepetor.datasource.datasourceHolder.MyDynamicDataSource;
import org.apache.commons.collections4.MapUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private DataSourceDTO dataSourceDTO;

    @Bean("datasourceMap")
    public Map<Object, Object> targetDataSource(){
        Map<Object, Object> map = new HashMap<>();
        Map<String, Map<String, String>> dataProperties = dataSourceDTO.getDatasource();
        dataProperties.forEach((key, value)->{
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(MapUtils.getString(value, "driver-class-name"));
            dataSource.setUrl(MapUtils.getString(value, "url"));
            dataSource.setUsername(MapUtils.getString(value, "username"));
            dataSource.setPassword(MapUtils.getString(value, "password"));
            map.put(key, dataSource);
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

