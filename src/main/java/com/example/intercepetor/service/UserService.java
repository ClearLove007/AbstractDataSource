package com.example.intercepetor.service;

import com.example.intercepetor.datasource.datasourceHolder.DynamicDataSourceHolder;
import com.example.intercepetor.entity.User;
import com.example.intercepetor.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 15:56 2019/12/5
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public boolean insertUser(User user){
        userMapper.insert(user);
        return true;
    }

    @Transactional
    public boolean insertSwitch(User user){
        DynamicDataSourceHolder.putDataKey("master");
        userMapper.insert(user);
        DynamicDataSourceHolder.clearData();

        DynamicDataSourceHolder.putDataKey("severnt");
        userMapper.insert(user);
        DynamicDataSourceHolder.clearData();

        DynamicDataSourceHolder.putDataKey("third");
        userMapper.insert(user);
        DynamicDataSourceHolder.clearData();

        return true;
    }
}
