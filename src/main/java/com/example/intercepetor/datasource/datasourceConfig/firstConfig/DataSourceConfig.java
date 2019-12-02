//package com.example.intercepetor.datasource.datasourceConfig.firstConfig;
//
//import com.example.intercepetor.datasource.datasourceHolder.MyDynamicDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Author: XueWeiDong
// * @Description:
// * @Date: 17:14 2019/11/29
// */
//@Configuration
//@MapperScan(value = "com.example.intercepetor.mapper")
//public class DataSourceConfig {
//
//    @Bean(name = "master")
//    @ConfigurationProperties("spring.datasource.master")
//    public DataSource getMasterDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "severnt")
//    @ConfigurationProperties("spring.datasource.severnt")
//    public DataSource getSeverntDataSource(){
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean("myDynamicDataSource")
//    public MyDynamicDataSource dataSource(@Qualifier("master") DataSource master, @Qualifier("severnt") DataSource severnt){
//        Map<Object, Object> targetDataSource = new HashMap<Object, Object>();
//        targetDataSource.put("master", master);
//        targetDataSource.put("severnt", severnt);
//        MyDynamicDataSource dynamicDataSource = new MyDynamicDataSource();
//        dynamicDataSource.setTargetDataSources(targetDataSource);
//        dynamicDataSource.setDefaultTargetDataSource(master);
//        return dynamicDataSource;
//    }
//
//    @Bean(name = "sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("myDynamicDataSource") DataSource dynamicDataSource)throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dynamicDataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
//        return bean.getObject();
//    }
//}
