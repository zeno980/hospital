package com.zakary.dao;

import java.util.Date;

public class HlistDao {
    private Integer hlist_id;
    private Integer doctor_id;
    private Integer patient_id;
    private Integer sickroom_id;
    private Integer sickbed_id;
    private Date inh_time;
    private Date outh_time;
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

    public Date getInh_time() {
        return inh_time;
    }

    public void setInh_time(Date inh_time) {
        this.inh_time = inh_time;
    }

    public Date getOuth_time() {
        return outh_time;
    }

    public void setOuth_time(Date outh_time) {
        this.outh_time = outh_time;
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
}
