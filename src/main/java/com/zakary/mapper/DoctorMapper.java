package com.zakary.mapper;

import com.zakary.dao.DoctorDao;
import com.zakary.dao.PageDao;

import java.util.List;

public interface DoctorMapper {
    DoctorDao selectDoctorByCode(DoctorDao doctorDao);
    DoctorDao selectDoctorById(DoctorDao doctorDao);
    int deleteDoctorById(DoctorDao doctorDao);
    int insertDoctor(DoctorDao doctorDao);
    int updateDoctorById(DoctorDao doctorDao);
    List<DoctorDao> selectAllDoctors(PageDao pageDao);
    int getAllDoctorsCounts();
}