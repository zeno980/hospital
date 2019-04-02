package com.zakary.dao;

import java.util.Date;

public class Treatment {
    private Integer doctorid;
    private Integer patientid;
    private String treatmentname;
    private Date treatmenttime;
    private Double treatmentfee;


    public Integer getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(Integer doctorid) {
        this.doctorid = doctorid;
    }

    public Integer getPatientid() {
        return patientid;
    }

    public void setPatientid(Integer patientid) {
        this.patientid = patientid;
    }

    public String getTreatmentname() {
        return treatmentname;
    }

    public void setTreatmentname(String treatmentname) {
        this.treatmentname = treatmentname;
    }

    public Date getTreatmenttime() {
        return treatmenttime;
    }

    public void setTreatmenttime(Date treatmenttime) {
        this.treatmenttime = treatmenttime;
    }

    public Double getTreatmentfee() {
        return treatmentfee;
    }

    public void setTreatmentfee(Double treatmentfee) {
        this.treatmentfee = treatmentfee;
    }
}
