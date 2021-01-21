package com.zakary.mapper;

import com.zakary.dao.Doctor;

import java.util.List;

public interface DoctorMapper {
    int deleteByPrimaryKey(Integer doctorid);

    int insert(Doctor record);

    int insertSelective(Doctor record);

    Doctor selectByPrimaryKey(Integer doctorid);

    int updateByPrimaryKeySelective(Doctor record);

    int updateByPrimaryKey(Doctor record);

    Doctor selectById(Doctor record);

    List<Doctor> selectDoctorAll();
    Doctor findByDoctorId(Integer userId);
}