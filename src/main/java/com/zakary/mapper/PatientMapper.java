package com.zakary.mapper;

import com.zakary.dao.PatientDao;

public interface PatientMapper {
    int deleteByPrimaryKey(Integer patientid);

    int insert(PatientDao record);

    int insertSelective(PatientDao record);

    PatientDao selectByPrimaryKey(Integer patientid);

    int updateByPrimaryKeySelective(PatientDao record);

    int updateByPrimaryKey(PatientDao record);
}