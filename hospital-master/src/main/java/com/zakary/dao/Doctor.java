package com.zakary.dao;

import java.util.Objects;

public class Doctor {
    private Integer doctorid;

    private String doctorname;

    private String doctordepartment;

    private String doctorposition;

    private String doctorgender;

    private Integer type;

    private String doctortel;

    private String password;

    public Integer getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(Integer doctorid) {
        this.doctorid = doctorid;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname == null ? null : doctorname.trim();
    }

    public String getDoctordepartment() {
        return doctordepartment;
    }

    public void setDoctordepartment(String doctordepartment) {
        this.doctordepartment = doctordepartment == null ? null : doctordepartment.trim();
    }

    public String getDoctorposition() {
        return doctorposition;
    }

    public void setDoctorposition(String doctorposition) {
        this.doctorposition = doctorposition == null ? null : doctorposition.trim();
    }

    public String getDoctorgender() {
        return doctorgender;
    }

    public void setDoctorgender(String doctorgender) {
        this.doctorgender = doctorgender == null ? null : doctorgender.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDoctortel() {
        return doctortel;
    }

    public void setDoctortel(String doctortel) {
        this.doctortel = doctortel == null ? null : doctortel.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorid=" + doctorid +
                ", doctorname='" + doctorname + '\'' +
                ", doctordepartment='" + doctordepartment + '\'' +
                ", doctorposition='" + doctorposition + '\'' +
                ", doctorgender='" + doctorgender + '\'' +
                ", type=" + type +
                ", doctortel='" + doctortel + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(doctorid, doctor.doctorid) &&
                Objects.equals(password, doctor.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorid, password);
    }
}