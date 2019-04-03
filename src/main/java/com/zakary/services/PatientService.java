package com.zakary.services;

import com.zakary.dao.PageDao;
import com.zakary.dao.PatientDao;
import com.zakary.dao.TreatmentDao;
import com.zakary.dao.utils.DoctorPatients;

import java.util.List;

public interface PatientService {
    List<DoctorPatients> getAllPatientByDoctorId(PageDao pageDao,String id);
    int getPatientsCounts();
    void insertPatient(TreatmentDao treatmentDao);
}
