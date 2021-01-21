package com.zakary.dao;

public class Patient {
    private Integer patientid;

    private String patientname;

    private String patientgender;

    private Integer patientage;

    private Integer patienttel;

    private String type;

    public Integer getPatientid() {
        return patientid;
    }

    public void setPatientid(Integer patientid) {
        this.patientid = patientid;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname == null ? null : patientname.trim();
    }

    public String getPatientgender() {
        return patientgender;
    }

    public void setPatientgender(String patientgender) {
        this.patientgender = patientgender == null ? null : patientgender.trim();
    }

    public Integer getPatientage() {
        return patientage;
    }

    public void setPatientage(Integer patientage) {
        this.patientage = patientage;
    }

    public Integer getPatienttel() {
        return patienttel;
    }

    public void setPatienttel(Integer patienttel) {
        this.patienttel = patienttel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}