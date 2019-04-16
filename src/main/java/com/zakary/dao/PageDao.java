package com.zakary.dao;

public class PageDao {  //分页查询需要
    private int page;
    private int limit;
    private Integer cert_code;
    private Integer patient_id;
    private String patient_cert_code;
    private Integer doctor_id;
    private String doctor_cert_code;
    private String active;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Integer getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Integer doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Integer getCert_code() {
        return cert_code;
    }

    public void setCert_code(Integer doctor_id) {
        this.cert_code = doctor_id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Integer getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_cert_code() {
        return patient_cert_code;
    }

    public void setPatient_cert_code(String patient_cert_code) {
        this.patient_cert_code = patient_cert_code;
    }

    public String getDoctor_cert_code() {
        return doctor_cert_code;
    }

    public void setDoctor_cert_code(String doctor_cert_code) {
        this.doctor_cert_code = doctor_cert_code;
    }

}
