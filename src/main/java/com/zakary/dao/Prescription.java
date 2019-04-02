package com.zakary.dao;

public class Prescription {
    private Integer rescriptionid;
    private Integer doctorid;
    private Integer patientid;
    private Integer drugid;
    private Integer drugnum;

    public Integer getRescriptionid() {
        return rescriptionid;
    }

    public void setRescriptionid(Integer rescriptionid) {
        this.rescriptionid = rescriptionid;
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

    public Integer getDrugid() {
        return drugid;
    }

    public void setDrugid(Integer drugid) {
        this.drugid = drugid;
    }

    public Integer getDrugnum() {
        return drugnum;
    }

    public void setDrugnum(Integer drugnum) {
        this.drugnum = drugnum;
    }
}
