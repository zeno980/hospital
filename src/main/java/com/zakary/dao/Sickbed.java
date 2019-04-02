package com.zakary.dao;

public class Sickbed {
    private Integer sickbedid;
    private Integer sickrooomid;
    private String type;


    public Integer getSickbedid() {
        return sickbedid;
    }

    public void setSickbedid(Integer sickbedid) {
        this.sickbedid = sickbedid;
    }

    public Integer getSickrooomid() {
        return sickrooomid;
    }

    public void setSickrooomid(Integer sickrooomid) {
        this.sickrooomid = sickrooomid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
