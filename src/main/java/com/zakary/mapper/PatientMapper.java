package com.zakary.mapper;

import com.zakary.dao.PatientDao;
import com.zakary.dao.utils.DoctorPatients;

import java.util.List;

public interface PatientMapper {
    int deleteByPrimaryKey(Integer patientid);

    int insert(PatientDao record);

    int insertSelective(PatientDao record);

    PatientDao selectByPrimaryKey(Integer patientid);

    int updateByPrimaryKeySelective(PatientDao record);

    int updateByPrimaryKey(PatientDao record);

    List<DoctorPatients> selectAllPatientByDoctorId(int id);

    int getAllPatientsCounts();

    int getCountById(int patient_id);

    int getCountByIdInTreatment(int patient_id);

    void insertPatientTreatmnet();
}