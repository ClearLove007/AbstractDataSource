package com.example.intercepetor.common;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 10:20 2019/11/29
 */
public class AuthException extends RuntimeException {
    private static final long serialVersionUID = 2L;
    private long code;
    private String msg;

    public AuthException(long code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString(){
        return this.msg;
    }
}
