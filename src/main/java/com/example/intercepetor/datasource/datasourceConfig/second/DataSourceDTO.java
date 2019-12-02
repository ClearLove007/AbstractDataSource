package com.example.intercepetor.datasource.datasourceConfig.second;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 14:04 2019/12/2
 */
@ConfigurationProperties(prefix = "spring")
@Component
@Setter
@Getter
public class DataSourceDTO {
    private Map<String, Map<String, String>> datasource;
}
