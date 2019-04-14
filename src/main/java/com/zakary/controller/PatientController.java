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

    //获取病房的总数
    //不需要json传数据
    @ResponseBody
    public JsonResultDao getSickroomCount(){
        JsonResultDao jsonResultDao=new JsonResultDao();
        jsonResultDao.setData(patientService.selectSickroomCount());
        return jsonResultDao;
    }
    //第一个功能，添加病人在treatment表里，病人必须在patient表里存在
    //json传patient_cert_code,treatment_time,treatment_name,treatment_fee
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

    //第二个功能，查看此医生治疗的所有患者的基本信息,搜索患者
    @RequestMapping("/doctor_patients")
    @ResponseBody
    //json中含有cert_code（医生身份证），patient_cert_code
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
        List<DoctorPatients> patients=patientService.getAllPatientByDoctorCert(pageDao);
        jsonResultDao.setCode(0);
        jsonResultDao.setData(patients);
        jsonResultDao.setMsg("success");
        jsonResultDao.setCount(patientService.getPatientsCounts(pageDao));
        return jsonResultDao;
    }

    //安排病房
    @RequestMapping("/ArrangeSickbed")
    @ResponseBody
    //json中含有patient_cert_code，sickroom，sickbed
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
    @RequestMapping("/GetAllSickbedInfo")
    @ResponseBody
    //json中：如果传入空值则查询所有信息，传入patient_cert_code不为空，则查询当前的病人病床信息
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

    //开处方
    @RequestMapping("/makePrescribtion")
    @ResponseBody
    //json中prescriptionDao获得patient——id，prescrptionAttributeDaos获得drug_name,drug_num
    public JsonResultDao makePrescribtion(HttpServletRequest request,
                                          @RequestBody List<PrescriptionAttributeDao> prescriptionAttributeDaos){
        //获得医生cert-code
        HttpSession session = request.getSession();
        String doctor_cert_code = (String)session.getAttribute("cert_code");
        logger.info(doctor_cert_code);
        patientService.makePrescribtion(prescriptionAttributeDaos,doctor_cert_code);
        return  new JsonResultDao();
    }

    @RequestMapping("/getPrescriptionAttribute")
    @ResponseBody
    //json传入patient_cert_code即可
    //json中存储PrescriptionAttribute的doctor_cet_codes属性和prescription_id属性
    //获得的map中含有所有信息，只需要提取drognum,drugname
    //前台计算总价，方法如下
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
    //获取某个病人的药品总价
    @RequestMapping("/getallDrugPrice")
    @ResponseBody
    //json传入patient_cert_code即可
    //json返回为一个double总价
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
        JsonResultDao jsonResultDao=new JsonResultDao();
        jsonResultDao.setData(alldrugprice);
        return jsonResultDao;
    }
    //生成病历单
    @RequestMapping("/setHlist")
    @ResponseBody
    //json传入patient_cert_code
    public JsonResultDao setHlist(HttpServletRequest request,@RequestBody HlistDao hlistDao){
        //JsonResultDao jsonResultDao=new JsonResultDao();
        HttpSession session=request.getSession();
        String doctor_cert_code=(String)session.getAttribute("cert_code");
        hlistDao.setDoctor_cert_code(doctor_cert_code);
        logger.info("doctor: "+doctor_cert_code+"  patient: "+hlistDao.getPatient_cert_code());
        patientService.setHlistByCert(hlistDao);
        return new JsonResultDao();
    }

    @RequestMapping("/getHlistInfo")
    @ResponseBody
    //查看某位病人的病历单，json中传入patient_cert_code
    public JsonResultDao getHlistInfo(@RequestBody HlistDao hlistDao){
        JsonResultDao jsonResultDao=new JsonResultDao();
        HlistDao hlistDao1=patientService.getHlistByCert(hlistDao.getPatient_cert_code());
        jsonResultDao.setData(hlistDao1);
        return jsonResultDao;
    }
    @RequestMapping("/getAllPatientsSickbedInfo")
    @ResponseBody
    //病房管理，获取所有病人的的病房信息
    //传递页面参数，查询的话，传入patient_cert_code
    public List<Map<String,Object>> getAllPatientsSickbedInfo(HttpServletRequest request,PageDao pageDao){
        int pageNum=Integer.parseInt(request.getParameter("page"));
        int limit=Integer.parseInt(request.getParameter("limit"));
        pageDao.setPage((pageNum-1)*limit);
        pageDao.setLimit(limit);
        return patientService.getAllPatientSickbed(pageDao);
    }

    @RequestMapping("/getPatientsNoSickbed")
    @ResponseBody
    //得到所有没有分配病房的病人信息
    public List<Map<String,Object>> getPatientsNoSickbed(HttpServletRequest request,PageDao pageDao){
        int pageNum=Integer.parseInt(request.getParameter("page"));
        int limit=Integer.parseInt(request.getParameter("limit"));
        pageDao.setPage((pageNum-1)*limit);
        pageDao.setLimit(limit);
        return patientService.getAllPatientNoSickbed(pageDao);
    }
}
