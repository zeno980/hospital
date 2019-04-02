package com.zakary.dao;

import java.util.Date;

public class Hlist {
    private Integer hlistid;
    private Integer doctorid;
    private Integer patientid;
    private Integer sickroomid;
    private Integer sickbedid;
    private Date inhtime;
    private Date outhtime;
    private String treatmentname;
    private double hlistfee;

    public Integer getHlistid() {
        return hlistid;
    }

    public void setHlistid(Integer hlistid) {
        this.hlistid = hlistid;
    }

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

    public Integer getSickroomid() {
        return sickroomid;
    }

    public void setSickroomid(Integer sickroomid) {
        this.sickroomid = sickroomid;
    }

    public Integer getSickbedid() {
        return sickbedid;
    }

    public void setSickbedid(Integer sickbedid) {
        this.sickbedid = sickbedid;
    }

    public Date getInhtime() {
        return inhtime;
    }

    public void setInhtime(Date inhtime) {
        this.inhtime = inhtime;
    }

    public Date getOuthtime() {
        return outhtime;
    }

    public void setOuthtime(Date outhtime) {
        this.outhtime = outhtime;
    }

    public String getTreatmentname() {
        return treatmentname;
    }

    public void setTreatmentname(String treatmentname) {
        this.treatmentname = treatmentname;
    }

    public double getHlistfee() {
        return hlistfee;
    }

    public void setHlistfee(double hlistfee) {
        this.hlistfee = hlistfee;
    }
}
