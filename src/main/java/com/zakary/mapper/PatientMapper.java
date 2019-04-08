package com.zakary.mapper;

import com.zakary.dao.PageDao;
import com.zakary.dao.PatientDao;
import com.zakary.dao.SickbedDao;
import com.zakary.dao.TreatmentDao;
import com.zakary.dao.utils.DoctorPatients;
import com.zakary.dao.utils.PatientSickbed;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PatientMapper {
    int deleteByPrimaryKey(Integer patientid);

    int insert(PatientDao record);

    int insertSelective(PatientDao record);

    PatientDao selectByPrimaryKey(Integer patientid);

    int updateByPrimaryKeySelective(PatientDao record);

    int updateByPrimaryKey(PatientDao record);

    List<DoctorPatients> selectAllPatientsByDoctorCert(PageDao pageDao);

    int getAllPatientsCounts();

    int getCountByCert(String cert_code);

    int getCountByCertInTreatment(String patient_cert_code);

    void insertPatientTreatmnet(TreatmentDao treatmentDao);

    String isEmptyBySickbed(@Param("sickroom_id") int sickroom_id, @Param("sickbed_id") int sickbed_id);

    void updateSickbed(SickbedDao sickbedDao);

    int selectPatientInSickbedByCert(String patient_cert_code);

    List<PatientSickbed> selectPatientsSickbedInfo(PageDao pageDao);

    int selectCountInSickroom();
}