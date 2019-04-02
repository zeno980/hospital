package com.zakary.dao;

public class SickbedDao {
    private Integer sickbed_id;
    private Integer sickrooom_id;
    private String sickbed_state;


    public Integer getSickbed_id() {
        return sickbed_id;
    }

    public void setSickbed_id(Integer sickbed_id) {
        this.sickbed_id = sickbed_id;
    }

    public Integer getSickrooom_id() {
        return sickrooom_id;
    }

    public void setSickrooom_id(Integer sickrooom_id) {
        this.sickrooom_id = sickrooom_id;
    }

    public String getSickbed_state() {
        return sickbed_state;
    }

    public void setSickbed_state(String sickbed_state) {
        this.sickbed_state = sickbed_state;
    }
}
