package com.zakary.servicesImpl;

import com.zakary.dao.Patient;
import com.zakary.mapper.PatientMapper;
import com.zakary.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientMapper dao;

    @Override
    public Patient getPatient(Patient user) {
        // TODO Auto-generated method stub
        //调用dao层查询结果
        return dao.selectById(user);
    }
}
