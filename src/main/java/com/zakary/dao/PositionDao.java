package com.zakary.dao;

import java.util.Date;

public class PositionDao {
    private String position_name;
    private Integer position_id;
    private Integer department_id;
    private String department_name;
    private String active;
    private Date create_date;

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public Integer getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    @Override
    public String toString() {
        return "PositionDao{" +
                "position_name='" + position_name + '\'' +
                ", position_id=" + position_id +
                ", department_id=" + department_id +
                ", department_name='" + department_name + '\'' +
                ", active='" + active + '\'' +
                ", create_date=" + create_date +
                '}';
    }
}
