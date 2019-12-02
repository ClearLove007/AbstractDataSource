package com.example.intercepetor.dto;

import java.util.*;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 10:39 2019/11/29
 */
public class ResponseDTO extends BaseRespDto {
    private static final long serialVersionUID = -6144000382695493830L;
    private String dataType = "0";
    private Map<String, Object> responseBody = new HashMap();
    private List<?> responseList = new ArrayList();

    public ResponseDTO() {
    }

    public ResponseDTO(Map<String, Object> map) {
        this.setResultCode("0");
        this.setResponseBody(map);
    }

    public ResponseDTO(List list) {
        this.setResultCode("0");
        this.setResponseBody(list);
    }

    public ResponseDTO set(String key, Object value) {
        this.responseBody.put(key, value);
        return this;
    }

    public String getString(String key) {
        return (String)this.get(key, String.class);
    }

    public Long getLong(String key) {
        return (Long)this.get(key, Long.class);
    }

    
    public Integer getInteger(String key) {
        return (Integer)this.get(key, Integer.class);
    }

    public <T> T get(String key, Class<T> c) {
        return c.cast(this.responseBody.get(key));
    }

    public ResponseDTO put(Map<String, Object> data) {
        if (data == null) {
            return this;
        } else {
            Iterator var2 = data.entrySet().iterator();

            while(var2.hasNext()) {
                Map.Entry<String, Object> e = (Map.Entry)var2.next();
                this.put((String)e.getKey(), e.getValue());
            }

            return this;
        }
    }

    public void putAll(Map<String, Object> data) {
        this.responseBody.putAll(data);
    }

    public void put(String key, Object value) {
        if ("resultCode".equals(key)) {
            this.setResultCode(value.toString());
        } else if ("errorCode".equals(key)) {
            this.setErrorCode(value.toString());
        } else if ("errorDesc".equals(key)) {
            this.setErrorDesc(value.toString());
        } else {
            this.responseBody.put(key, value);
        }

    }

    public Map<String, Object> all() {
        Map<String, Object> m = this.baseValues();
        m.putAll(this.responseBody);
        return m;
    }

    private Map<String, Object> baseValues() {
        Map<String, Object> baseValues = new HashMap();
        putIfNotNull(baseValues, "errorCode", this.getErrorCode());
        putIfNotNull(baseValues, "errorDesc", this.getErrorDesc());
        putIfNotNull(baseValues, "resultCode", this.getResultCode());
        return baseValues;
    }

    private static void putIfNotNull(Map<String, Object> m, String key, Object value) {
        if (value != null) {
            m.put(key, value);
        }

    }

    public static ResponseDTO success() {
        ResponseDTO result = new ResponseDTO();
        result.setResultCode("0");
        return result;
    }

    public static ResponseDTO error(String errorCode, String errorDesc) {
        ResponseDTO result = new ResponseDTO();
        result.setResultCode("1");
        result.setErrorCode(errorCode);
        result.setErrorDesc(errorDesc);
        return result;
    }

    public boolean isOk() {
        return "0".equals(this.getResultCode());
    }

    public Object getResponseBody() {
        if ("0".equals(this.dataType)) {
            return this.responseBody;
        } else {
            return "1".equals(this.dataType) ? this.responseList : null;
        }
    }

    public ResponseDTO setResponseBody(Map responseBody) {
        this.dataType = "0";
        this.responseBody = responseBody;
        return this;
    }

    public ResponseDTO setResponseBody(List responseBody) {
        this.dataType = "1";
        this.responseList = responseBody;
        return this;
    }
}
