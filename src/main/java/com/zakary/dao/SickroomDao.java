package com.zakary.dao;

public class SickroomDao {
    private Integer sickroom_id;
    private Double sickroom_fee;


    public Integer getSickroom_id() {
        return sickroom_id;
    }

    public void setSickroom_id(Integer sickroom_id) {
        this.sickroom_id = sickroom_id;
    }

    public Double getSickroom_fee() {
        return sickroom_fee;
    }

    public void setSickroom_fee(Double sickroom_fee) {
        this.sickroom_fee = sickroom_fee;
    }
}