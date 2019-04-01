package com.zakary.servicesImpl;

import com.zakary.dao.Doctor;
import com.zakary.dao.JSONEntity;
import com.zakary.exp.BusinessException;
import com.zakary.mapper.DoctorMapper;
import com.zakary.services.RootManagerDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RootManagerDocotorServiceImpl implements RootManagerDoctorService {
    @Autowired
    private DoctorMapper dao;

    public List<Doctor> getDoctorAll(){
        return dao.selectDoctorAll();
    }
    public boolean insertDoctor(Doctor doctor){
        if((doctor.getDoctorid()==null)
                ||(doctor.getDoctorname()==null)
                ||(doctor.getDoctordepartment()==null)
                ||(doctor.getDoctorposition()==null)
                ||(doctor.getDoctorgender()==null)
                ||(doctor.getDoctortel()==null)
                ||(doctor.getPassword()==null)) {
            throw new BusinessException("必要参数为空");
        }
        Doctor realUser = dao.selectById(doctor);
        if(realUser!=null){
            throw new BusinessException("用户已存在");
        }
        return true;
    }

    @Override
    public int updateInformation(Doctor doctor) {

        if((doctor.getDoctorname()==null)
                ||(doctor.getDoctordepartment()==null)
                ||(doctor.getDoctorposition()==null)
                ||(doctor.getDoctorgender()==null)
                ||(doctor.getDoctortel()==null)
                ||(doctor.getPassword()==null)) {
            throw new BusinessException("必要参数为空");
        }
        return dao.updateByPrimaryKey(doctor);
    }

    @Override
    public int deleteDoctor(Doctor doctor) {
        if(doctor.getDoctorid() ==null)
            throw new BusinessException("必要参数为空");
        if(doctor.getDoctorid()==1)
            throw new BusinessException("此用户无法删除");
        return dao.deleteByPrimaryKey(doctor.getDoctorid());
    }
}
