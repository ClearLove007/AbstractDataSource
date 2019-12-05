package com.example.intercepetor.common;

import java.security.SecureRandom;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 10:17 2019/11/29
 */
public interface SystemConsts {

    interface Token{
        String TOKEN = "-1533*6jH%";
    }

    interface ErrCode{
        long AUTH_ERR_CODE = 9001L;
    }

    interface ErrMsg{
        String AUTH_ERR_MSG = "验证失败，请重新验证";
    }

    interface DataSourceConfig{
        String url = "url";
        String username = "username";
        String password = "password";
        String driver = "driver-class-name";
    }

    interface ShiroConfig{
        //AES加密cookie密钥
        byte[] KEY = SecureRandom.getSeed(16);

        int COOKIE_AGE = 60*60*24*30;
    }
}
