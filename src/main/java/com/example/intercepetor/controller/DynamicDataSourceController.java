package com.example.intercepetor.controller;

import com.example.intercepetor.datasource.datasourceAnotion.DynamicSwitchDataSource;
import com.example.intercepetor.dto.ResponseDTO;
import com.example.intercepetor.entity.User;
import com.example.intercepetor.mapper.UserMapper;
import com.example.intercepetor.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 18:24 2019/11/29
 */
@RestController
@RequestMapping("/datasource")
@Slf4j
public class DynamicDataSourceController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/master", method = RequestMethod.GET)
    @DynamicSwitchDataSource(dataSource = "master")
    public ResponseDTO selectAllMasterUser(){
        List<User> result = userMapper.selectAll();
        return new ResponseDTO(result);
    }

    @RequestMapping(value = "/master", method = RequestMethod.POST)
    @DynamicSwitchDataSource(dataSource = "master")
    public ResponseDTO insertMasterUser(@RequestBody User user){
        userService.insertUser(user);
        return new ResponseDTO();
    }

    @RequestMapping(value = "/severnt", method = RequestMethod.GET)
    @DynamicSwitchDataSource(dataSource = "severnt")
    public ResponseDTO selectAllSeverntUser(){
        List<User> result = userMapper.selectAll();
        return new ResponseDTO(result);
    }

    @RequestMapping(value = "/severnt", method = RequestMethod.POST)
    @DynamicSwitchDataSource(dataSource = "severnt")
    public ResponseDTO insertSeverntrUser(User user){
        userMapper.insert(user);
        return new ResponseDTO();
    }

    @RequestMapping(value = "/third", method = RequestMethod.GET)
    @DynamicSwitchDataSource(dataSource = "third")
    public ResponseDTO selectAllThirdUser(){
        List<User> result = userMapper.selectAll();
        return new ResponseDTO(result);
    }

    @RequestMapping(value = "/third", method = RequestMethod.POST)
    @DynamicSwitchDataSource(dataSource = "third")
    public ResponseDTO insertThirdUser(User user){
        userMapper.insert(user);
        return new ResponseDTO();
    }

    @RequestMapping(value = "/no", method = RequestMethod.GET)
    public ResponseDTO selectAllUser(){
        List<User> result = userMapper.selectAll();
        return new ResponseDTO(result);
    }

}
