package com.zakary.servicesImpl;


import com.zakary.dao.Doctor;
import com.zakary.exp.BusinessException;
import com.zakary.mapper.DoctorMapper;
import com.zakary.services.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DoctorServiceImpl implements DoctorService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DoctorMapper dao;

    @Override
    public boolean getDoctor(Doctor user) {
        if(user.getDoctorid()==null||user.getPassword()==null)
            throw new BusinessException("必要参数为空");
        Doctor doctor = dao.selectById(user);
        if("doctor".equals(user.getPage())&&doctor.getType()!=0){
            logger.info("用户id:"+user.getDoctorid());
            throw new BusinessException("没有权限访问此页面");
        }
        if(user.equals(doctor)){
            return true;
        }else{
            throw new BusinessException("账号或密码错误");
        }
    }

}

