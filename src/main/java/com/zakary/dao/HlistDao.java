package com.zakary.dao;

import java.util.Date;

public class HlistDao {
    private Integer hlist_id;
    private String doctor_name;
    private String doctor_cert_code;
    private String patient_name;
    private String patient_cert_code;
    private Integer sickroom_id;
    private Integer sickbed_id;
    private Date treatment_time;
    private String treatment_name;
    private Integer prescription_id;
    private double drug_all_price;
    private double sickroom_fee;
    private double treatment_fee;
    private double hlist_fee;

    @Override
    public String toString() {
        return " hlist_id: "+getHlist_id()
                +" doctor_name: "+getDoctor_name()
                +" doctor_cert_code: "+getDoctor_cert_code()
                +" patient_name: "+getPatient_name()
                +" patient_cert_code: "+getPatient_cert_code()
                +" sickroom_id: "+getSickroom_id()
                +" sickbed_id: "+getSickbed_id()
                +" treatment_time: "+getTreatment_time()
                +" treatment_name: "+getTreatment_name()
                +" prescription_id: "+getPrescription_id()
                +" drug_all_price: "+getDrug_all_price()
                +" sickroom_fee: "+getSickroom_fee()
                +" treatment_fee: "+getTreatment_fee()
                +" hlist_fee: "+getHlist_fee();
    }

    public Integer getHlist_id() {
        return hlist_id;
    }

    public void setHlist_id(Integer hlist_id) {
        this.hlist_id = hlist_id;
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

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public Integer getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(Integer prescription_id) {
        this.prescription_id = prescription_id;
    }

    public double getDrug_all_price() {
        return drug_all_price;
    }

    public void setDrug_all_price(double drug_all_price) {
        this.drug_all_price = drug_all_price;
    }

    public double getSickroom_fee() {
        return sickroom_fee;
    }

    public void setSickroom_fee(double sickroom_fee) {
        this.sickroom_fee = sickroom_fee;
    }

    public String getDoctor_cert_code() {
        return doctor_cert_code;
    }

    public void setDoctor_cert_code(String doctor_cert_code) {
        this.doctor_cert_code = doctor_cert_code;
    }

    public double getTreatment_fee() {
        return treatment_fee;
    }

    public void setTreatment_fee(double treatment_fee) {
        this.treatment_fee = treatment_fee;
    }
}
