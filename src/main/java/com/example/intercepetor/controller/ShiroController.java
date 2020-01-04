package com.example.intercepetor.controller;

import com.example.intercepetor.dto.RequestDTO;
import com.example.intercepetor.dto.ResponseDTO;
import com.example.intercepetor.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 14:57 2020/1/4
 */
@RestController
@RequestMapping("/shiroController")
@Slf4j
public class ShiroController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseDTO login(@RequestBody RequestDTO requestDTO){
        String username = requestDTO.getString("username");
        String password = requestDTO.getString("password");
        int status = login(username, password, true);
        if (status == 0){
            return new ResponseDTO("status", "login success");
        }
        return new ResponseDTO("status", "login fail");
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    @RequiresAuthentication
    @RequiresPermissions("access:acc")
    public ResponseDTO select(){
        return new ResponseDTO(userMapper.selectAll());
    }

    @RequestMapping(value = "/selected", method = RequestMethod.GET)
    @RequiresUser
    public ResponseDTO selected(){
        return new ResponseDTO(userMapper.selectAll());
    }

    private int login(String loginName, String plainPassword, boolean isRememberMe) {
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(plainPassword)) {
            return -1;
        }
        Subject subject = SecurityUtils.getSubject();
//        subject.getSession().setTimeout(3600000L);
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, plainPassword, isRememberMe);
        try{
            subject.login(token);
        }catch (UnknownAccountException e){
            log.error("未知账户名:{}", e);
            return -2;
        }catch (LockedAccountException e){
            log.error("账户已登陆:{}", e);
            return -3;
        }catch (IncorrectCredentialsException e){
            log.error("账户名密码错误:{}", e);
            return -4;
        }
        return 0;
    }

}
