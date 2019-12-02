package com.example.intercepetor.intercepter;

import com.example.intercepetor.common.exception.AuthException;
import com.example.intercepetor.common.SystemConsts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 9:42 2019/11/29
 */
@Slf4j
public class HandlerIntercepter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");

        if (!StringUtils.isEmpty(token)){
            if (token.equals(SystemConsts.Token.TOKEN)){
                return true;
            } else {
                throw new AuthException(SystemConsts.ErrCode.AUTH_ERR_CODE, SystemConsts.ErrMsg.AUTH_ERR_MSG);
            }
        } else {
            throw new AuthException(SystemConsts.ErrCode.AUTH_ERR_CODE, SystemConsts.ErrMsg.AUTH_ERR_MSG);
        }
    }

    private Map<String, String> getRequestMap(Map reqParams){
        Map<String, String> params = new HashMap<String, String>();
        for (Iterator iter = reqParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) reqParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }
}
