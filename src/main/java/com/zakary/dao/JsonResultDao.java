package com.zakary.dao;

public class JsonResultDao {
    private int code;
    private String msg;
    private int count;
    private Object data;

    public JsonResultDao(){};

    public JsonResultDao(Object data){
        this.data = data;
        this.code = 0;
    };
    public JsonResultDao(Object data,int code,String msg){
        this.data = data;
        this.code = code;
        this.msg = msg;
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
