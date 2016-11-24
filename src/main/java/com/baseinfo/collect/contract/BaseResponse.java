package com.baseinfo.collect.contract;

import java.util.List;
import java.util.Map;

/**
 * 通用的响应类
 */
public class BaseResponse {

    private int code;

    private String msg;

    private Map<String,Object> data;

    private List<Object> resList;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public List<Object> getResList() {
        return resList;
    }

    public void setResList(List<Object> resList) {
        this.resList = resList;
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
