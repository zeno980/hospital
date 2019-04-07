package com.zakary.dao.utils;

public class PatientSickbed {

    private String patient_name;
    private String patient_cert_code;
    private String doctor_name;
    private int sickroom;
    private int sickbed;
    private double sickromm_fee;

    public int getSickroom() {
        return sickroom;
    }

    public void setSickroom(int sickroom) {
        this.sickroom = sickroom;
    }

    public int getSickbed() {
        return sickbed;
    }

    public void setSickbed(int sickbed) {
        this.sickbed = sickbed;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public double getSickromm_fee() {
        return sickromm_fee;
    }

    public void setSickromm_fee(double sickromm_fee) {
        this.sickromm_fee = sickromm_fee;
    }


    public String getPatient_cert_code() {
        return patient_cert_code;
    }

    public void setPatient_cert_code(String patient_cert_code) {
        this.patient_cert_code = patient_cert_code;
    }
}
