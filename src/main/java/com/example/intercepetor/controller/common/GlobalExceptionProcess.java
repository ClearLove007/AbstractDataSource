package com.example.intercepetor.controller.common;

import com.example.intercepetor.common.exception.AuthException;
import com.example.intercepetor.common.exception.ServiceException;
import com.example.intercepetor.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 11:03 2019/11/29
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionProcess {

    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public ResponseDTO serviceException(ServiceException e){
        log.error("服务异常:{}",e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errMsg", e.toString());
        return new ResponseDTO(map);
    }

    @ResponseBody
    @ExceptionHandler(value = AuthException.class)
    public ResponseDTO authException(AuthException e){
        log.error("服务异常:{}",e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errMsg", e.toString());
        return new ResponseDTO(map);
    }

    @ResponseBody
    @ExceptionHandler(value = SQLException.class)
    public ResponseDTO SQLException(SQLException e){
        log.error("sql语句执行异常:{}", e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errMsg", e.toString());
        return new ResponseDTO(map);
    }

    @ResponseBody
    @ExceptionHandler(value = AuthorizationException.class)
    public ResponseDTO authorizedException(AuthorizationException e){
        log.error("该用户无权限:{}", e);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("errMsg", e.toString());
        return new ResponseDTO(map);
    }
}
