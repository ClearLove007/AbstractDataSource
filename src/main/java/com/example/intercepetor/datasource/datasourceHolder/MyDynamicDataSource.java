package com.example.intercepetor.datasource.datasourceHolder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 15:30 2019/11/29
 */
@Slf4j
public class MyDynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDateKey();
    }
}
