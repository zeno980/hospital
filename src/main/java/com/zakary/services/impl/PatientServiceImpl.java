package com.zakary.services.impl;

import com.zakary.dao.PageDao;
import com.zakary.dao.PatientDao;
import com.zakary.dao.SickbedDao;
import com.zakary.dao.TreatmentDao;
import com.zakary.dao.utils.DoctorPatients;
import com.zakary.dao.utils.PatientSickbed;
import com.zakary.exp.BusinessException;
import com.zakary.mapper.DoctorMapper;
import com.zakary.mapper.PatientMapper;
import com.zakary.services.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private PatientService patientService;

    @Override
    public int getPatientsCounts(PageDao pageDao) {
        return patientMapper.getAllPatientsCounts(pageDao);
    }

    @Override
    public List<DoctorPatients> getAllPatientByDoctorCert(PageDao pageDao) {
        if("1".equals(pageDao.getDoctor_cert_code()))
            throw new BusinessException("id不存在");
        //int doctor_id=Integer.parseInt(doctor_cert_code);
        logger.info("医生ID: "+pageDao.getDoctor_cert_code());
        return patientMapper.selectAllPatientsByDoctorCert(pageDao);
    }

    @Override
    public void insertPatient(TreatmentDao treatmentDao) {//此处需要在前端自动将此医生的赋值
        if(/*(treatmentDao.getDoctor_id()==null)*/
           /* (treatmentDao.getPatient_id()==null)*/
            (treatmentDao.getDoctor_cert_code()==null)
            ||(treatmentDao.getPatient_cert_code()==null)
            ||(treatmentDao.getTreatment_name()==null)
            ||(treatmentDao.getTreatment_time()==null)
            ||(treatmentDao.getTreatment_fee()==null))
            throw new BusinessException("必要参数为空");
        else if(patientMapper.getCountByCert(treatmentDao.getPatient_cert_code())==0)
            throw new BusinessException("此患者不存在");
        else if(patientMapper.getCountByCertInTreatment(treatmentDao.getPatient_cert_code())!=0)
            throw new BusinessException("此患者已存在在治疗名单中");
        else
            patientMapper.insertPatientTreatmnet(treatmentDao);
    }

    @Override
    public String isEmpty(int sickroom_id, int sickbed_id) {
        System.out.println("room:"+sickroom_id+"  bed:"+sickbed_id);
        return patientMapper.isEmptyBySickbed(sickroom_id,sickbed_id);
    }

    @Override
    public void arrangeSickbed(SickbedDao sickbedDao){
        if((sickbedDao.getPatient_cert_code()==null)
                ||(sickbedDao.getSickroom_id()==null)
                ||(sickbedDao.getSickbed_id()==null))
            throw new BusinessException("必要参数为空");
        else {
            System.out.println("room:"+sickbedDao.getSickroom_id()+"  bed:"+sickbedDao.getSickbed_id());
            if (!patientService.isEmpty(sickbedDao.getSickroom_id(), sickbedDao.getSickbed_id()).equals("empty")) {
                //System.out.println(patientService.isEmpty(sickbedDao.getSickroom_id(), sickbedDao.getSickbed_id()));
                throw new BusinessException("该病床已分配");
            }
            else {
                if (patientService.isInSickbed(sickbedDao.getPatient_cert_code()) != 0)
                    throw new BusinessException("该病人已存在");
                else {
                    sickbedDao.setSickbed_state("full");
                    patientMapper.updateSickbed(sickbedDao);
                }
            }
        }
//       patientMapper.insertSickbed(sickbedDao);
    }

    @Override
    public int isInSickbed(String patient_cert_code){
        return patientMapper.selectPatientInSickbedByCert(patient_cert_code);
    }

    @Override
    public List<PatientSickbed> getPatientsSickbedInfo(PageDao pageDao) {
        return patientMapper.selectPatientsSickbedInfo(pageDao);
    }
}
