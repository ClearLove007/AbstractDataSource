package com.example.intercepetor.datasource.datasourceConfig.secondConfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 14:04 2019/12/2
 */
@ConfigurationProperties(prefix = "spring")
@Setter
@Getter
public class DataSourceDTO {
    private Map<String, Map<String, String>> datasource;
}
