package com.zakary.dao;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class PatientDao {
    private Integer patient_id;

    private String cert_code;

    private String patient_name;

    private String patient_gender;

    private Integer patient_age;

    private String patient_tel;
    private Integer type;

    private String doctor_cert_code;
    private String has_sickbed;
    @JSONField(format = "yyyy-MM-dd")
    private Date create_date;

    public String getDoctor_cert_code() {
        return doctor_cert_code;
    }

    public void setDoctor_cert_code(String doctor_cert_code) {
        this.doctor_cert_code = doctor_cert_code;
    }

    public String getHas_sickbed() {
        return has_sickbed;
    }

    public void setHas_sickbed(String has_sickbed) {
        this.has_sickbed = has_sickbed;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_gender() {
        return patient_gender;
    }

    public void setPatient_gender(String patient_gender) {
        this.patient_gender = patient_gender;
    }

    public Integer getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(Integer patient_age) {
        this.patient_age = patient_age;
    }

    public String getPatient_tel() {
        return patient_tel;
    }

    public void setPatient_tel(String patient_tel) {
        this.patient_tel = patient_tel;
    }

    public String getCert_code() {
        return cert_code;
    }

    public void setCert_code(String cert_code) {
        this.cert_code = cert_code;
    }

    @Override
    public String toString() {
        return "PatientDao{" +
                "patient_id=" + patient_id +
                ", cert_code='" + cert_code + '\'' +
                ", patient_name='" + patient_name + '\'' +
                ", patient_gender='" + patient_gender + '\'' +
                ", patient_age=" + patient_age +
                ", patient_tel=" + patient_tel +
                ", type='" + type + '\'' +
                ", doctor_cert_code='" + doctor_cert_code + '\'' +
                ", has_sickbed='" + has_sickbed + '\'' +
                ", create_date=" + create_date +
                '}';
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getType(Integer type){
        return type;
    }
}