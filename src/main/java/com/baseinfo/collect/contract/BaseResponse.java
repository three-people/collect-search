package com.baseinfo.collect.contract;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 通用的响应类
 */
public class BaseResponse {

    private int code;

    private String msg;

    private Map<String,Object> data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
