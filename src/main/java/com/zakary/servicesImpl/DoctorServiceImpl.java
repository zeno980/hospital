package com.zakary.servicesImpl;


import com.zakary.dao.Doctor;
import com.zakary.mapper.DoctorMapper;
import com.zakary.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorMapper dao;

    @Override
    public Doctor getDoctor(Doctor user) {
        // TODO Auto-generated method stub
        //调用dao层查询结果
        return dao.selectById(user);
    }
}

