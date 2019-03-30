package com.zakary.servicesImpl;


import com.zakary.dao.Patient;
import com.zakary.mapper.PatientMapper;
import com.zakary.services.RootManagerPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RootManagerPatientServiceImpl implements RootManagerPatientService {
    @Autowired
    private PatientMapper dao;

    public List<Patient> getPatientAll(){
        return dao.selectPatientAll();
    }
    public int insertPatient(Patient patient){
        return dao.insert(patient);
    }

    @Override
    public int updateInformation(Patient patient) {
        return dao.updateByPrimaryKey(patient);
    }

    @Override
    public int deletePatient(Patient patient) {
        return dao.deleteByPrimaryKey(patient.getPatientid());
    }
}
