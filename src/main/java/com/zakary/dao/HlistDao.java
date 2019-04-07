package com.zakary.dao;

import java.util.Date;

public class HlistDao {
    private Integer hlist_id;
    private Integer doctor_id;
    private String doctor_cert_code;
    private Integer patient_id;
    private String patient_cert_code;
    private Integer sickroom_id;
    private Integer sickbed_id;
    private Date treatment_time;
    private String treatment_name;
    private double hlist_fee;


    public Integer getHlist_id() {
        return hlist_id;
    }

    public void setHlist_id(Integer hlist_id) {
        this.hlist_id = hlist_id;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public Integer getSickroom_id() {
        return sickroom_id;
    }

    public void setSickroom_id(Integer sickroom_id) {
        this.sickroom_id = sickroom_id;
    }

    public Integer getSickbed_id() {
        return sickbed_id;
    }

    public void setSickbed_id(Integer sickbed_id) {
        this.sickbed_id = sickbed_id;
    }

    public String getTreatment_name() {
        return treatment_name;
    }

    public void setTreatment_name(String treatment_name) {
        this.treatment_name = treatment_name;
    }

    public double getHlist_fee() {
        return hlist_fee;
    }

    public void setHlist_fee(double hlist_fee) {
        this.hlist_fee = hlist_fee;
    }

    public String getDoctor_cert_code() {
        return doctor_cert_code;
    }

    public void setDoctor_cert_code(String doctor_cert_code) {
        this.doctor_cert_code = doctor_cert_code;
    }

    public String getPatient_cert_code() {
        return patient_cert_code;
    }

    public void setPatient_cert_code(String patient_cert_code) {
        this.patient_cert_code = patient_cert_code;
    }

    public Date getTreatment_time() {
        return treatment_time;
    }

    public void setTreatment_time(Date treatment_time) {
        this.treatment_time = treatment_time;
    }
}
