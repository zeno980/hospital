package com.zakary.dao;

public class JSONEntity {
    private Object data;
    private String code;
    private  boolean success;
    private String msg;
    public  JSONEntity(){}
    public  JSONEntity(Object data){
        this.data=data;
        this.success=true;
    }
    public  JSONEntity(Object data,boolean success,String msg){
        this.data = data;
        this.success=success;
        this.msg=msg;
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
