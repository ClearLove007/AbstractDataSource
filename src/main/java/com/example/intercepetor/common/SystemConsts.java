package com.example.intercepetor.common;

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
}
