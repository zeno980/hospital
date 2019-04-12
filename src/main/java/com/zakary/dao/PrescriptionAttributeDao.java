package com.zakary.dao;

public class PrescriptionAttributeDao {
    private int prescription_id;
    private String doctor_cert_code;
    private String patient_cert_code;
    private String drug_name;
    private int drug_num;

    public int getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(int prescription_id) {
        this.prescription_id = prescription_id;
    }

    public int getDrug_num() {
        return drug_num;
    }

    public void setDrug_num(int drug_num) {
        this.drug_num = drug_num;
    }

    public String getDoctor_cert_code() {
        return doctor_cert_code;
    }

    public void setDoctor_cert_code(String doctor_cert_code) {
        this.doctor_cert_code = doctor_cert_code;
    }

    public String getPatient_cert_code() {
        return patient_cert_code;
    }

    public void setPatient_cert_code(String patient_cert_code) {
        this.patient_cert_code = patient_cert_code;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }
}
