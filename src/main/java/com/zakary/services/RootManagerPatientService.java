package com.zakary.services;

import com.zakary.dao.Patient;

import java.util.List;

public interface RootManagerPatientService {
    List<Patient> getPatientAll();
    int insertPatient(Patient patient);
    int updateInformation(Patient patient);
    int deletePatient(Patient patient);
}
