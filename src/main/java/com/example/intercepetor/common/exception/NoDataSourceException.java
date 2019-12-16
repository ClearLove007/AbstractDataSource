package com.example.intercepetor.common.exception;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 11:14 2019/12/16
 */
public class NoDataSourceException extends RuntimeException {
    private static final long serialVersionUID = 2L;

    private Long code;
    private String msg;

    public NoDataSourceException(){
        super();
    }

    public NoDataSourceException(Long code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
