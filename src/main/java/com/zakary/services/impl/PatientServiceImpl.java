package com.zakary.services.impl;

import com.zakary.dao.*;
import com.zakary.dao.utils.DoctorPatients;
import com.zakary.dao.utils.PatientSickbed;
import com.zakary.exp.BusinessException;
import com.zakary.mapper.DoctorMapper;
import com.zakary.mapper.PatientMapper;
import com.zakary.services.PatientService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    //private Logger logger = LoggerFactory.getLogger(getClass());
    Logger logger = Logger.getLogger("");
    @Autowired
    private PatientMapper patientMapper;

    @Autowired
    private DoctorMapper doctotMapper;

    public int getPatientsCounts(PageDao pageDao) {
        return patientMapper.getAllPatientsCounts(pageDao);
    }

    public List<DoctorPatients> getAllPatientByDoctorCert(PageDao pageDao) {
        if("1".equals(pageDao.getDoctor_cert_code()))
            throw new BusinessException("id不存在");
        //int doctor_id=Integer.parseInt(doctor_cert_code);
        //logger.info("医生ID: "+pageDao.getDoctor_cert_code());
        return patientMapper.selectAllPatientsByDoctorCert(pageDao);
    }

    public void insertPatient(TreatmentDao treatmentDao) {//此处需要在前端自动将此医生的赋值
        if(/*(treatmentDao.getDoctor_id()==null)*/
           /* (treatmentDao.getPatient_id()==null)*/
            (treatmentDao.getDoctor_cert_code()==null)
            ||("".equals(treatmentDao.getDoctor_cert_code()))
            ||(treatmentDao.getPatient_cert_code()==null)
            ||("".equals(treatmentDao.getPatient_cert_code()))
            ||(treatmentDao.getTreatment_name()==null)
            ||("".equals(treatmentDao.getTreatment_name()))
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

    private String isEmpty(int sickroom_id, int sickbed_id) {
        System.out.println("room:"+sickroom_id+"  bed:"+sickbed_id);
        return patientMapper.isEmptyBySickbed(sickroom_id,sickbed_id);
    }

    public void arrangeSickbed(SickbedDao sickbedDao){
        if((sickbedDao.getPatient_cert_code()==null)
                ||("".equals(sickbedDao.getPatient_cert_code()))
                ||(sickbedDao.getSickroom_id()==null)
                ||(sickbedDao.getSickbed_id()==null))
            throw new BusinessException("必要参数为空");
        else {
            System.out.println("room:"+sickbedDao.getSickroom_id()+"  bed:"+sickbedDao.getSickbed_id());
            if (!isEmpty(sickbedDao.getSickroom_id(), sickbedDao.getSickbed_id()).equals("empty")) {
                //System.out.println(patientService.isEmpty(sickbedDao.getSickroom_id(), sickbedDao.getSickbed_id()));
                throw new BusinessException("该病床已分配");
            }
            else {
                if (isInSickbed(sickbedDao.getPatient_cert_code()) != 0)
                    throw new BusinessException("该病人已存在");
                else {
                    sickbedDao.setSickbed_state("full");
                    patientMapper.updateSickbed(sickbedDao);
                }
            }
        }
//       patientMapper.insertSickbed(sickbedDao);
    }

    private int isInSickbed(String patient_cert_code){
        return patientMapper.selectPatientInSickbedByCert(patient_cert_code);
    }

    public List<PatientSickbed> getPatientsSickbedInfo(PageDao pageDao) {
        return patientMapper.selectPatientsSickbedInfo(pageDao);
    }

    @Override
    public int selectSickroomCount(){
        return patientMapper.selectCountInSickroom();
    }

    @Override
    public int insertPrescription(PrescriptionDao prescriptionDao){
        if(prescriptionDao.getDoctor_cert_code()==null
            ||prescriptionDao.getPatient_cert_code()==null
            ||"".equals(prescriptionDao.getDoctor_cert_code())
            ||"".equals(prescriptionDao.getPatient_cert_code())){
            throw new BusinessException("必要参数为空");
        }
        else {
            return patientMapper.insertPrescriptionDao(prescriptionDao);
        }
    }
    @Override
    public int getPrescriptionId(PrescriptionDao prescriptionDao){
        return patientMapper.selectPrescriptionId(prescriptionDao);
    }
    @Override
    public void insertPrescriptionAttribute(PrescriptionAttributeDao prescriptionAttributeDao){
        if(prescriptionAttributeDao.getDrug_name()==null
                ||"".equals(prescriptionAttributeDao.getDrug_name())
                ||prescriptionAttributeDao.getDrug_num()==0) {
            throw new BusinessException("必要参数为空");
        }
        else if(prescriptionAttributeDao.getDrug_num()<0){
            throw new BusinessException("输入值非法");
        }
        else {
            System.out.println(prescriptionAttributeDao.getDrug_num());
            patientMapper.insertPrescriptionAttributeDao(prescriptionAttributeDao);
        }
    }

    private int getPrescriptionIdByCert(String doctor_cert_code,String patient_cert_code){
        return patientMapper.selectPrescriptionIdByCert(doctor_cert_code,patient_cert_code);
    }
    @Override
    public List<Map<String,Object>> getAllPrescriptionAttribute(PrescriptionAttributeDao prescriptionAttributeDao){
        List<Map<String, Object>> info;
        if(prescriptionAttributeDao.getDoctor_cert_code()==null
            ||"".equals(prescriptionAttributeDao.getDoctor_cert_code())
            ||prescriptionAttributeDao.getPatient_cert_code()==null
            ||"".equals(prescriptionAttributeDao.getPatient_cert_code())){
            throw new BusinessException("必要参数为空");
        }
        else {
            int perscription_id = getPrescriptionIdByCert(prescriptionAttributeDao.getDoctor_cert_code(), prescriptionAttributeDao.getPatient_cert_code());
            if (perscription_id == 0) {
                throw new BusinessException("此病人暂无处方");
            }
            else{
                prescriptionAttributeDao.setPrescription_id(perscription_id);
                info = patientMapper.selectAllPrescriptionAttribute(prescriptionAttributeDao);
            }
        }
        return info;
    }

    @Override
    public void setHlistByCert(HlistDao hlistDao){
        if(hlistDao.getDoctor_cert_code()==null
            ||hlistDao.getPatient_cert_code()==null
            ||"".equals(hlistDao.getDoctor_cert_code())
            ||"".equals(hlistDao.getPatient_cert_code())){
            throw new BusinessException("必要参数为空");
        }
        else if(patientMapper.hlistCountByCert(hlistDao.getPatient_cert_code())!=0){
            throw new BusinessException("此病人病历单已经生成");
        }
        else {
            //获取医生姓名
            DoctorDao doctorDao=new DoctorDao();
            doctorDao.setCert_code(hlistDao.getDoctor_cert_code());
            hlistDao.setDoctor_name(doctotMapper.selectDoctorByCode(doctorDao).getDoctor_name());
            logger.info("doctor_name:"+hlistDao.getDoctor_cert_code());
            //获取患者姓名
            hlistDao.setPatient_name(patientMapper.selectPatientByCert(hlistDao.getPatient_cert_code()).getPatient_name());
            //获取病房,病床
            hlistDao.setSickroom_id(patientMapper.selectSickbedbyCert(hlistDao.getPatient_cert_code()).getSickroom_id());
            hlistDao.setSickbed_id(patientMapper.selectSickbedbyCert(hlistDao.getPatient_cert_code()).getSickbed_id());
            //获取手术名称和时间,费用
            hlistDao.setTreatment_time(patientMapper.selectTreatmentByCert(hlistDao.getPatient_cert_code()).getTreatment_time());
            hlistDao.setTreatment_name(patientMapper.selectTreatmentByCert(hlistDao.getPatient_cert_code()).getTreatment_name());
            hlistDao.setTreatment_fee(patientMapper.selectTreatmentByCert(hlistDao.getPatient_cert_code()).getTreatment_fee());
            //获取药品费用
            PrescriptionAttributeDao prescriptionAttributeDao=new PrescriptionAttributeDao();
            prescriptionAttributeDao.setDoctor_cert_code(hlistDao.getDoctor_cert_code());
            prescriptionAttributeDao.setPatient_cert_code(hlistDao.getPatient_cert_code());
            List<Map<String,Object>> infos=getAllPrescriptionAttribute(prescriptionAttributeDao);
            double alldrugprice=0;
            for(int i=0;i<infos.size();i++) {
                //Logger logger=Logger.getLogger("getAllPrescriptionAttribute");
                //logger.info("DRUG :"+infos.get(i));
                double price=Double.parseDouble(infos.get(i).get("drug_price").toString());
                int num=Integer.parseInt(infos.get(i).get("drug_num").toString());
                alldrugprice+=price*num;
            }
            hlistDao.setDrug_all_price(alldrugprice);
            //获取处方单
            hlistDao.setPrescription_id(patientMapper.selectPrescriptionIdByCert(hlistDao.getDoctor_cert_code(),hlistDao.getPatient_cert_code()));
            //获取sickrommfee
            hlistDao.setSickroom_fee(patientMapper.selectSickrommFee(patientMapper.selectSickbedbyCert(hlistDao.getPatient_cert_code()).getSickroom_id()));
            //总费用：
            hlistDao.setHlist_fee(hlistDao.getSickroom_fee()+hlistDao.getDrug_all_price()+hlistDao.getTreatment_fee());
            logger.info(hlistDao.toString());
            patientMapper.insertHlist(hlistDao);
        }
    }

    @Override
    public HlistDao getHlistByCert(String patient_cert_code){
        if(patient_cert_code==null
        ||"".equals(patient_cert_code))
            throw new BusinessException("必要参数为空");
        else
            return patientMapper.selectHlistInfoByCert(patient_cert_code);
    }

    @Override
    public void makePrescribtion(List<PrescriptionAttributeDao> prescriptionAttributeDaos,String doctor_cert_code) {
        PrescriptionDao prescriptionDao=new PrescriptionDao();
        PrescriptionAttributeDao prescriptionAttributeDao = prescriptionAttributeDaos.get(0);
        prescriptionDao.setDoctor_cert_code(doctor_cert_code);
        prescriptionDao.setPatient_cert_code(prescriptionAttributeDao.getPatient_cert_code());
        //插入病历单在prescriptiondao表中
        insertPrescription(prescriptionDao);
        int prescription_id=getPrescriptionId(prescriptionDao);
        //插入prescriptionAttribute
        //Iterator<PrescriptionAttributeDao> iter = prescriptionAttributeDaos.iterator();
        //while(iter.hasNext()){
        //    patientService.insertPrescriptionAttribute(iter.next());
        //}
        for( int i = 0 ; i <  prescriptionAttributeDaos.size() ; i++) {
            System.out.println( prescriptionAttributeDaos.get(i));
            prescriptionAttributeDao.setDoctor_cert_code(doctor_cert_code);
            prescriptionAttributeDaos.get(i).setPrescription_id(prescription_id);
            insertPrescriptionAttribute(prescriptionAttributeDaos.get(i));
        }
    }
}
