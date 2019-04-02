package com.zakary.dao;

import java.util.Date;

public class TreatmentDao {
    private Integer doctor_id;
    private Integer patient_id;
    private String treatment_name;
    private Date treatment_time;
    private Double treatment_fee;


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

    public String getTreatment_name() {
        return treatment_name;
    }

    public void setTreatment_name(String treatment_name) {
        this.treatment_name = treatment_name;
    }

    public Date getTreatment_time() {
        return treatment_time;
    }

    public void setTreatment_time(Date treatment_time) {
        this.treatment_time = treatment_time;
    }

    public Double getTreatment_fee() {
        return treatment_fee;
    }

    public void setTreatment_fee(Double treatment_fee) {
        this.treatment_fee = treatment_fee;
    }
}
