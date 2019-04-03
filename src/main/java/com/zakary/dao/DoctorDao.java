package com.zakary.dao;

import java.util.Date;
import java.util.Objects;

public class DoctorDao {
    private Integer doctor_id;

    private String doctor_name;

    private String doctor_department;

    private String doctor_position;

    private String doctor_gender;

    private Integer type;

    private String doctor_tel;

    private String password;

    private String page;
    private String cert_code;
    private String active;
    private Date create_date;

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

    public String getCert_code() {
        return cert_code;
    }

    public void setCert_code(String cert_code) {
        this.cert_code = cert_code;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_department() {
        return doctor_department;
    }

    public void setDoctor_department(String doctor_department) {
        this.doctor_department = doctor_department;
    }

    public String getDoctor_position() {
        return doctor_position;
    }

    public void setDoctor_position(String doctor_position) {
        this.doctor_position = doctor_position;
    }

    public String getDoctor_gender() {
        return doctor_gender;
    }

    public void setDoctor_gender(String doctor_gender) {
        this.doctor_gender = doctor_gender;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDoctor_tel() {
        return doctor_tel;
    }

    public void setDoctor_tel(String doctor_tel) {
        this.doctor_tel = doctor_tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorDao doctorDao = (DoctorDao) o;
        return Objects.equals(password, doctorDao.password) &&
                Objects.equals(cert_code, doctorDao.cert_code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, cert_code);
    }
}