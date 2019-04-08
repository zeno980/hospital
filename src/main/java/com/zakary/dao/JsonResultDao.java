package com.zakary.dao;

import com.alibaba.fastjson.JSONObject;

/**
 * 默认成功只需要调用构造方法传入data即可
 * 前端表格渲染接口要求返回必须参数code(成功状态 0，失败 1),count(数据总数，用于分页渲染)，msg(成功或失败信息)
 * 不是前端表格接口不需要返回count
 */
public class JsonResultDao {
    private int code;
    private String msg;
    private int count;
    private Object data;

    public JsonResultDao(){};

    public JsonResultDao(Object data){
        this.data = JSONObject.toJSON(data);
        this.code = 0;
    };
    public JsonResultDao(Object data,int code,String msg){
        this.data = JSONObject.toJSON(data);
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
        this.data = JSONObject.toJSON(data);
    }

}
