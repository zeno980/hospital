package com.zakary.services.impl;


import com.zakary.dao.DoctorDao;
import com.zakary.dao.PageDao;
import com.zakary.exp.BusinessException;
import com.zakary.mapper.DoctorMapper;
import com.zakary.services.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DoctorServiceImpl implements DoctorService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DoctorMapper doctorMapper;
    @Override
    public boolean login(DoctorDao doctorDao) {
        if(doctorDao.getDoctor_id()==null||doctorDao.getPassword()==null)
            throw new BusinessException("必要参数为空");
        DoctorDao result = doctorMapper.selectDoctorById(doctorDao);
        if("doctor".equals(doctorDao.getPage())&& result.getType()!=0){
            logger.info("用户id:"+doctorDao.getDoctor_id());
            throw new BusinessException("没有权限访问此页面");
        }
        if(doctorDao.equals(result)){
            return true;
        }else{
            throw new BusinessException("账号或密码错误");
        }
    }
    public List<DoctorDao> getDoctorAll(PageDao pageDao){
        return doctorMapper.selectAllDoctors(pageDao);
    }
    public boolean insertDoctor(DoctorDao doctorDao){
        if((doctorDao.getDoctor_id()==null)
                ||(doctorDao.getDoctor_name()==null)
                ||(doctorDao.getDoctor_department()==null)
                ||(doctorDao.getDoctor_position()==null)
                ||(doctorDao.getDoctor_gender()==null)
                ||(doctorDao.getDoctor_tel()==null)
                ||(doctorDao.getPassword()==null)) {
            throw new BusinessException("必要参数为空");
        }
        DoctorDao realUser = doctorMapper.selectDoctorById(doctorDao);
        if(realUser!=null){
            throw new BusinessException("用户已存在");
        }
        return true;
    }

    @Override
    public int updateInformation(DoctorDao doctorDao) {

        if((doctorDao.getDoctor_name()==null)
                ||(doctorDao.getDoctor_department()==null)
                ||(doctorDao.getDoctor_position()==null)
                ||(doctorDao.getDoctor_gender()==null)
                ||(doctorDao.getDoctor_tel()==null)
                ||(doctorDao.getPassword()==null)) {
            throw new BusinessException("必要参数为空");
        }
        return doctorMapper.updateDoctorById(doctorDao);
    }

    @Override
    public int deleteDoctor(DoctorDao doctorDao) {
        if(doctorDao.getDoctor_id() ==null)
            throw new BusinessException("必要参数为空");
        if(doctorDao.getDoctor_id()==1)
            throw new BusinessException("此用户无法删除");
        return doctorMapper.deleteDoctorById(doctorDao);
    }

    @Override
    public int getDoctorsCounts() {
        return doctorMapper.getAllDoctorsCounts();
    }
}

