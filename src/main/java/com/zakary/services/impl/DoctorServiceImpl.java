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
    public void login(DoctorDao doctorDao) {
        if(doctorDao.getCert_code()==null||doctorDao.getPassword()==null)
            throw new BusinessException("必要参数为空");
        DoctorDao result = doctorMapper.selectDoctorByCode(doctorDao);
        if(result==null)
            throw new BusinessException("用户不存在");
        if("doctor".equals(doctorDao.getPage())&& result.getType()!=0){
            logger.info("用户:"+doctorDao.getCert_code());
            throw new BusinessException("没有权限访问此页面");
        }
        if(result.getActive().equals("N")){
            throw new BusinessException("账号未审核通过");
        }
        if(!doctorDao.equals(result)){
            throw new BusinessException("账号或密码错误");
        }
    }
    public List<DoctorDao> getDoctorAll(PageDao pageDao){
        return doctorMapper.selectAllDoctors(pageDao);
    }
    public void insertDoctor(DoctorDao doctorDao){
        if(doctorDao.getDoctor_name()==null
                ||doctorDao.getDoctor_department()==null
                ||doctorDao.getDoctor_position()==null
                ||doctorDao.getDoctor_gender()==null
                ||doctorDao.getDoctor_tel()==null
                ||doctorDao.getPassword()==null
                ||doctorDao.getCert_code()==null) {
            throw new BusinessException("必要参数为空");
        }
        DoctorDao realUser = doctorMapper.selectDoctorByCode(doctorDao);
        if(realUser!=null){
            throw new BusinessException("用户已存在");
        }
        doctorMapper.insertDoctor(doctorDao);
    }

    @Override
    public void updateInformation(DoctorDao doctorDao) {

        if(doctorDao.getDoctor_name()==null
                ||doctorDao.getDoctor_department()==null
                ||doctorDao.getDoctor_position()==null
                ||doctorDao.getDoctor_gender()==null
                ||doctorDao.getDoctor_tel()==null
                ||doctorDao.getDoctor_id()==null) {
            throw new BusinessException("必要参数为空");
        }
        DoctorDao result = doctorMapper.selectDoctorById(doctorDao); //根据id查询
        if(!result.getCert_code().equals(doctorDao.getCert_code())){ //尝试修改身份证号码
            logger.info("修改身份证号码(旧号码:"+result.getCert_code()+">>>>>新号码:"+doctorDao.getCert_code()+")");
            DoctorDao doctor_code = doctorMapper.selectDoctorByCode(doctorDao);//根据新身份证号码查询
            if(doctor_code!=null){
                throw new BusinessException("此证件号已存在");
            }
        }
        doctorMapper.updateDoctorById(doctorDao);
    }

    @Override
    public void deleteDoctor(DoctorDao doctorDao) {
        if(doctorDao.getDoctor_id() ==null)
            throw new BusinessException("必要参数为空");
        if(doctorDao.getDoctor_id()==1)
            throw new BusinessException("此用户无法删除");
        doctorMapper.deleteDoctorById(doctorDao);
    }

    @Override
    public int getDoctorsCounts() {
        return doctorMapper.getAllDoctorsCounts();
    }
}

