package com.example.intercepetor.service;

import com.example.intercepetor.common.exception.ServiceException;
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
        throw new ServiceException("添加数据出现异常");
    }
}
