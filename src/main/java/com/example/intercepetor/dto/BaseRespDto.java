package com.example.intercepetor.dto;

import java.io.Serializable;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 10:40 2019/11/29
 */
public class BaseRespDto implements Serializable {
    private static final long serialVersionUID = 5453520762479712152L;
    private String resultCode;
    private String errorCode;
    private String errorDesc;

    public BaseRespDto() {
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return this.errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
