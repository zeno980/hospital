package com.zakary.dao;

public class SickbedDao {
    private Integer sickbed_id;
    private Integer sickroom_id;
    private String sickbed_state;
    private String patient_cert_code;


    public Integer getSickbed_id() {
        return sickbed_id;
    }

    public void setSickbed_id(Integer sickbed_id) {
        this.sickbed_id = sickbed_id;
    }



    public String getSickbed_state() {
        return sickbed_state;
    }

    public void setSickbed_state(String sickbed_state) {
        this.sickbed_state = sickbed_state;
    }


    public Integer getSickroom_id() {
        return sickroom_id;
    }

    public void setSickroom_id(Integer sickroom_id) {
        this.sickroom_id = sickroom_id;
    }

    public String getPatient_cert_code() {
        return patient_cert_code;
    }

    public void setPatient_cert_code(String patient_cert_code) {
        this.patient_cert_code = patient_cert_code;
    }
}
