package com.zakary.dao.utils;

import java.util.Date;

public class DoctorPatients {
    //private Integer patient_id;
    private String patient_cert_code;
    private String patient_name;
    private String patient_gender;
    private Integer patient_age;
    private String treatment_name;
    private Date treatment_time;
    private Double treatment_fee;

    /*public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }*/

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


    public String getPatient_cert_code() {
        return patient_cert_code;
    }

    public void setPatient_cert_code(String patient_cert_code) {
        this.patient_cert_code = patient_cert_code;
    }
}
