package com.zakary.services;

import com.zakary.dao.PageDao;
import com.zakary.dao.PatientDao;
import com.zakary.dao.SickbedDao;
import com.zakary.dao.TreatmentDao;
import com.zakary.dao.utils.DoctorPatients;
import com.zakary.dao.utils.PatientSickbed;

import java.util.List;

public interface PatientService {
    List<DoctorPatients> getAllPatientByDoctorCert(PageDao pageDao,String doctor_cert_code);
    int getPatientsCounts();
    void insertPatient(TreatmentDao treatmentDao);
    String isEmpty(int sickroom_id,int sickbed_id);
    void arrangeSickbed(SickbedDao sickbedDao);
    int isInSickbed(String patient_cert_code);
    List<PatientSickbed> getPatientsSickbedInfo(PageDao pageDao);
    int selectSickroomCount();
}
