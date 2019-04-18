package com.zakary.controller;

import com.zakary.dao.*;
import com.zakary.dao.utils.DoctorPatients;
import com.zakary.dao.utils.PatientSickbed;
import com.zakary.exp.BusinessException;
import com.zakary.services.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping("/patient")
    public String patient(){
        return "patient";
    }

    @RequestMapping("/getSickroomCount")

    /**
     *@description: 获取病房的总数
     *@param:
     *@return: 病房的总数
    */
    @ResponseBody
    public JsonResultDao getSickroomCount(){
        JsonResultDao jsonResultDao=new JsonResultDao();
        jsonResultDao.setData(patientService.selectSickroomCount());
        return jsonResultDao;
    }

    /**
     *@description: 添加病人在treatment表里，病人必须在patient表里存在
     *@param: patient_cert_code,treatment_time,treatment_name,treatment_fee
     *@return:
    */
    @RequestMapping("/doctor_addpatient")
    @ResponseBody
    public JsonResultDao addPatient(HttpServletRequest request,@RequestBody TreatmentDao treatmentDao){
        System.out.println(treatmentDao);
        HttpSession session = request.getSession();
        String cert_code = (String)session.getAttribute("cert_code");
        treatmentDao.setDoctor_cert_code(cert_code);
        patientService.insertPatient(treatmentDao);
        return new JsonResultDao("success");
    }

    /**
     *@description: 查看此医生治疗的所有患者的基本信息,搜索患者
     *@param: cert_code（医生身份证），patient_cert_code
     *@return: List<PatientDao>
    */
    @RequestMapping("/doctor_patients")
    @ResponseBody
    public JsonResultDao getPatients(HttpServletRequest request){
        int pageNum=Integer.parseInt(request.getParameter("page"));
        int limit=Integer.parseInt(request.getParameter("limit"));
        String patient_cert_code=request.getParameter("patient_cert_code");
        HttpSession session = request.getSession();
        PageDao pageDao = new PageDao();
        pageDao.setPage((pageNum-1)*limit);
        pageDao.setLimit(limit);
        String doctor_cert_code = (String)session.getAttribute("cert_code");
        JsonResultDao jsonResultDao=new JsonResultDao();
        if(doctor_cert_code!=null&&!doctor_cert_code.trim().equals("")){
            pageDao.setDoctor_cert_code(doctor_cert_code);
        }else{
            throw new BusinessException("获取id失败");
        }
        if(patient_cert_code!=null&&!patient_cert_code.trim().equals("")){
            pageDao.setPatient_cert_code(patient_cert_code);
        }
        List<PatientDao> patients=patientService.getAllPatientByDoctorCert(pageDao);
        jsonResultDao.setCode(0);
        jsonResultDao.setData(patients);
        jsonResultDao.setMsg("success");
        jsonResultDao.setCount(patientService.getPatientsCounts(pageDao));
        return jsonResultDao;
    }
    /**
     *@description: 安排病房
     *@param: patient_cert_code，sickroom，sickbed
     *@return:
    */
    @RequestMapping("/ArrangeSickbed")
    @ResponseBody
    public JsonResultDao arrangeSickbed(HttpServletRequest request,@RequestBody SickbedDao sickbedDao){
        //SickbedDao sickbedDao=new SickbedDao();
        //JsonResultDao jsonResultDao=new JsonResultDao();
        //String patient_cert_code=request.getParameter("patient_cert_code");
        //int cert_code=Integer.parseInt(request.getParameter("cert_code"))
        // int sickroom_id=Integer.parseInt(request.getParameter("sickroom"));
        //int sickbed_id=Integer.parseInt(request.getParameter("sickbed"));
        //sickbedDao.setPatient_id(patient_id);
        //sickbedDao.setPatient_cert_code(patient_cert_code);
        //sickbedDao.setSickroom_id(sickroom_id);
        //sickbedDao.setSickbed_id(sickbed_id);
        sickbedDao.setSickbed_state("full");

        patientService.arrangeSickbed(sickbedDao);

        return new JsonResultDao("success");
    }

    //病房管理 查询病房信息,搜索患者所属的病房
    /**
     *@description:  查询病房信息,搜索患者所属的病房
     *@param: atient_cert_code
     *@return: patientSickbeds
    */
    @RequestMapping("/GetAllSickbedInfo")
    @ResponseBody
    public JsonResultDao getAllSickbedInfo(HttpServletRequest request){
        int pageNum=Integer.parseInt(request.getParameter("page"));
        int limit=Integer.parseInt(request.getParameter("limit"));
        JsonResultDao jsonResultDao=new JsonResultDao();
        String patient_cert_code=request.getParameter("patient_cert_code");
        PageDao pageDao = new PageDao();
        pageDao.setPage((pageNum-1)*limit);
        pageDao.setLimit(limit);
        if(patient_cert_code!=null&&!patient_cert_code.trim().equals(""))//查询患者的病床信息
            pageDao.setPatient_cert_code(patient_cert_code);
        List<PatientSickbed> patientSickbeds=patientService.getPatientsSickbedInfo(pageDao);
        jsonResultDao.setCode(0);
        jsonResultDao.setMsg("success");
        jsonResultDao.setData(patientSickbeds);
        jsonResultDao.setCount(pageDao.getPatient_cert_code()==null?patientService.getPatientsCounts(pageDao):patientSickbeds.size());
        return jsonResultDao;
    }

    /**
     *@description: 开处方
     *@param: patient_id，prescrptionAttributeDaos获得drug_name,drug_num
     *@return:
    */
    @RequestMapping("/makePrescribtion")
    @ResponseBody
    public JsonResultDao makePrescribtion(HttpServletRequest request,
                                          @RequestBody List<PrescriptionAttributeDao> prescriptionAttributeDaos){
        //获得医生cert-code
        Logger logger=LoggerFactory.getLogger(getClass());
        logger.info(""+prescriptionAttributeDaos.get(0).getDrug_name());
        HttpSession session = request.getSession();
        String doctor_cert_code = (String)session.getAttribute("cert_code");
        logger.info(doctor_cert_code);
        patientService.makePrescribtion(prescriptionAttributeDaos,doctor_cert_code);
        return  new JsonResultDao();
    }
    /**
     *@description: 获得病房信息
     *@param: atient_cert_code
     *@return: PrescriptionAttribute的doctor_cet_codes属性和prescription_id属性
    */
    @RequestMapping("/getPrescriptionAttribute")
    @ResponseBody
    public List<Map<String,Object>> getPrescriptionAttribute(HttpServletRequest request, @RequestBody PrescriptionAttributeDao prescriptionAttributeDao){
        HttpSession session=request.getSession();
        String doctor_cert_code=(String)session.getAttribute("cert_code");
        prescriptionAttributeDao.setDoctor_cert_code(doctor_cert_code);
        List<Map<String,Object>> infos= patientService.getAllPrescriptionAttribute(prescriptionAttributeDao);
        //double alldrugprice=0;
        //for(int i=0;i<infos.size();i++) {
        //    Logger logger=Logger.getLogger("getAllPrescriptionAttribute");
        //    logger.info("DRUG :"+infos.get(i));
        //    double price=Double.parseDouble(infos.get(i).get("drug_price").toString());
        //    int num=Integer.parseInt(infos.get(i).get("drug_num").toString());
        //    alldrugprice+=price*num;
        //}
        //Logger logger=Logger.getLogger("drug_price");
        //logger.info("总价 ："+alldrugprice);
        return infos;
    }

    /**
     *@description: 获取某个病人的药品总价
     *@param: patient_cert_code
     *@return: allprice
    */
    @RequestMapping("/getallDrugPrice")
    @ResponseBody
    public JsonResultDao getallDrugPrice(HttpServletRequest request, @RequestBody PrescriptionAttributeDao prescriptionAttributeDao){
        HttpSession session=request.getSession();
        String doctor_cert_code=(String)session.getAttribute("cert_code");
        prescriptionAttributeDao.setDoctor_cert_code(doctor_cert_code);
        List<Map<String,Object>> infos= patientService.getAllPrescriptionAttribute(prescriptionAttributeDao);
        double alldrugprice=0;
        for(int i=0;i<infos.size();i++) {

            logger.info("DRUG :"+infos.get(i));
            double price=Double.parseDouble(infos.get(i).get("drug_price").toString());
            int num=Integer.parseInt(infos.get(i).get("drug_num").toString());
            alldrugprice+=price*num;
        }
        logger.info("总价 ："+alldrugprice);
        return new JsonResultDao(alldrugprice);
    }

    /**
     *@description: 生成病历单
     *@param: patient_cert_code
     *@return:
    */
    @RequestMapping("/setHlist")
    @ResponseBody
    public JsonResultDao setHlist(HttpServletRequest request,@RequestBody HlistDao hlistDao){
        //JsonResultDao jsonResultDao=new JsonResultDao();
        HttpSession session=request.getSession();
        String doctor_cert_code=(String)session.getAttribute("cert_code");
        hlistDao.setDoctor_cert_code(doctor_cert_code);
        logger.info("doctor: "+doctor_cert_code+"  patient: "+hlistDao.getPatient_cert_code());
        patientService.setHlistByCert(hlistDao);
        return new JsonResultDao();
    }

    /**
     *@description: 查看某位病人的病历单
     *@param: patient_cert_code
     *@return: HlistDao
    */
    @RequestMapping("/getHlistInfo")
    @ResponseBody
    public JsonResultDao getHlistInfo(@RequestBody HlistDao hlistDao){
        JsonResultDao jsonResultDao=new JsonResultDao();
        HlistDao hlistDao1=patientService.getHlistByCert(hlistDao.getPatient_cert_code());
        jsonResultDao.setData(hlistDao1);
        return jsonResultDao;
    }
    /**
     *@description: 病房管理，获取所有病人的的病房信息
     *@param: patient_cert_code
     *@return: List<Map<String,Object>> infos
    */
    @RequestMapping("/getAllPatientsSickbedInfo")
    @ResponseBody
    public JsonResultDao getAllPatientsSickbedInfo(HttpServletRequest request){
        PageDao pageDao=new PageDao();
        String doctor_cert_code = (String) request.getSession().getAttribute("cert_code");
        int pageNum=Integer.parseInt(request.getParameter("page"));
        int limit=Integer.parseInt(request.getParameter("limit"));
        pageDao.setPage((pageNum-1)*limit);
        pageDao.setLimit(limit);
        pageDao.setDoctor_cert_code(doctor_cert_code);
        JsonResultDao jsonResultDao=new JsonResultDao();
        String patient_cert_code=request.getParameter("patient_cert_code");
        if(patient_cert_code!=null&&!patient_cert_code.trim().equals(""))//查询患者的病床信息
            pageDao.setPatient_cert_code(patient_cert_code);
        List<Map<String,Object>> infos=patientService.getAllPatientSickbed(pageDao);
        jsonResultDao.setData(infos);
        jsonResultDao.setCode(0);
        jsonResultDao.setMsg("success");
        jsonResultDao.setCount(pageDao.getPatient_cert_code()==null?patientService.getPatientsCounts(pageDao):infos.size());
        return jsonResultDao;
    }

    /**
     *@description: 得到所有没有分配病房的病人信息
     *@param:
     *@return: List<Map<String,Object>>
    */
    @RequestMapping("/getPatientsNoSickbed")
    @ResponseBody
    public JsonResultDao getPatientsNoSickbed(HttpServletRequest request){
        PageDao pageDao=new PageDao();
        int pageNum=Integer.parseInt(request.getParameter("page"));
        int limit=Integer.parseInt(request.getParameter("limit"));
        String patient_cert_code=request.getParameter("patient_cert_code");
        if(patient_cert_code!=null&&!patient_cert_code.trim().equals(""))//查询患者的病床信息
            pageDao.setPatient_cert_code(patient_cert_code);
        pageDao.setPage((pageNum-1)*limit);
        pageDao.setLimit(limit);
        JsonResultDao jsonResultDao=new JsonResultDao();
        List<Map<String,Object>> infos=patientService.getAllPatientNoSickbed(pageDao);
        jsonResultDao.setData(infos);
        jsonResultDao.setCode(0);
        jsonResultDao.setMsg("success");
        jsonResultDao.setCount(infos.size());
        return jsonResultDao;
    }

    /**
     *@description: 往patient表中添加患者
     *@param: certcode name gender tel
     *@return:
    */
    @RequestMapping("/addPatientByInfo")
    @ResponseBody
    public JsonResultDao addPatientByInfo(HttpServletRequest request,@RequestBody PatientDao patientDao){
        HttpSession session=request.getSession();
        String doctor_cert_code=(String)session.getAttribute("cert_code");
        patientService.addPatient(doctor_cert_code,patientDao);
        return new JsonResultDao(0);
    }

    /**
     *@description: 得到一个病人的所有手术
     *@param: cert——code
     *@return: List<Map<String,Object>>
    */
    @RequestMapping("/getAllTreatmentByPatientCert")
    @ResponseBody
    public JsonResultDao getAllTreatmentByPatientCert(HttpServletRequest request){
        String patient_cert_code = (String) request.getParameter("patient_cert_code");
        TreatmentDao treatmentDao = new TreatmentDao();
        treatmentDao.setPatient_cert_code(patient_cert_code);
        List<Map<String,Object>> all=patientService.getAllTreatmentByPatientCertCode(treatmentDao);
        JsonResultDao jsonResultDao=new JsonResultDao();
        jsonResultDao.setData(all);
        jsonResultDao.setCount(all.size());
        jsonResultDao.setCode(0);
        jsonResultDao.setMsg("success");
        return jsonResultDao;
    }

    /**
     *@description: 添加病人信息
     *@param: cert——code
     *@return:
    */
    @RequestMapping("/alterPatientInfo")
    @ResponseBody
    public JsonResultDao alterPatientInfo(@RequestBody PatientDao patientDao){
        patientService.alterPatientInfoByCert(patientDao);
        return new JsonResultDao(0);
    }

    /**
     *@description: 得到末位病人的所有手术
     *@param: patient_cert_code
     *@return: patient_cert_code，patient_name,completed,not_complete,all_count
    */
    @RequestMapping("/getTreatmentCount")
    @ResponseBody
    public JsonResultDao getTreatmentCount(HttpServletRequest request){
        String doctor_cert_code = (String)request.getSession().getAttribute("cert_code");
        String patient_cert_code = request.getParameter("patient_cert_code");
        int pageNum=Integer.parseInt(request.getParameter("page"));
        int limit=Integer.parseInt(request.getParameter("limit"));
        PageDao pageDao = new PageDao();
        pageDao.setLimit(limit);
        pageDao.setPage((pageNum-1)*limit);
        pageDao.setDoctor_cert_code(doctor_cert_code);
        if(patient_cert_code!=null&&!patient_cert_code.trim().equals("")){
            pageDao.setPatient_cert_code(patient_cert_code);
        }
        List<Map<String,Object>> treatmentCount=patientService.getTreatmentCountByCert(pageDao);
        JsonResultDao jsonResultDao=new JsonResultDao();
        jsonResultDao.setData(treatmentCount);
        jsonResultDao.setCode(0);
        jsonResultDao.setCount(pageDao.getPatient_cert_code()==null?patientService.getPatientsCounts(pageDao):treatmentCount.size());
        jsonResultDao.setMsg("success");
        return jsonResultDao;
    }

    /**
     *@description: 得到某个病人的所有处方id，通过list.size获取数量
     *@param: PrescriptionDao.patient_cert_code
     *@return: List<PrescriptionDao>
     */
    @RequestMapping("/getAllPrescription")
    @ResponseBody
    public JsonResultDao getAllPrescription(@RequestBody PrescriptionDao prescriptionDao){
        List<PrescriptionDao> prescriptionDaos=patientService.getAllPrescriptionByCert(prescriptionDao);
        JsonResultDao jsonResultDao=new JsonResultDao();
        jsonResultDao.setData(prescriptionDaos);
        return jsonResultDao;
    }

    /**
     *@description: 通过处方id得到所有的药品内容
     *@param: prescriptionId
     *@return: list<Prescription_Attribute>
     */
    @RequestMapping("/getAllPrescriptionAttribute")
    @ResponseBody
    public JsonResultDao getAllPrescriptionAttribute(@RequestBody PrescriptionAttributeDao prescriptionAttributeDao){
        List<PrescriptionAttributeDao> prescriptionAttributeDaos=patientService.getAllPrescriptionAttributeByPrescriptionId(prescriptionAttributeDao);
        JsonResultDao jsonResultDao=new JsonResultDao();
        jsonResultDao.setData(prescriptionAttributeDaos);
        return jsonResultDao;
    }
}
