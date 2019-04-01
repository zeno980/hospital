package com.zakary.mapper;

import com.zakary.dao.Doctor;
import com.zakary.dao.Page;

import java.util.List;

public interface DoctorMapper {
    int deleteByPrimaryKey(Integer doctorid);

    int insert(Doctor record);

    int insertSelective(Doctor record);

    Doctor selectByPrimaryKey(Integer doctorid);

    int updateByPrimaryKeySelective(Doctor record);

    int updateByPrimaryKey(Doctor record);
    int getDoctorsCounts();
    Doctor selectById(Doctor record);

    List<Doctor> selectDoctorAll(Page page);
}