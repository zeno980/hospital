package com.zakary.mapper;

import com.zakary.dao.Patient;

public interface PatientMapper {
    int deleteByPrimaryKey(Integer patientid);

    int insert(Patient record);

    int insertSelective(Patient record);

    Patient selectByPrimaryKey(Integer patientid);

    int updateByPrimaryKeySelective(Patient record);

    int updateByPrimaryKey(Patient record);
}