package com.example.intercepetor.controller;

import com.example.intercepetor.dto.RequestDTO;
import com.example.intercepetor.dto.ResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 12:11 2019/12/24
 */
@RestController
@RequestMapping("/shiro")
public class ShiroController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseDTO login(@RequestBody RequestDTO request){

    }
}
