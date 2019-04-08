package com.zakary.controller;


import com.zakary.dao.*;
import com.zakary.dao.utils.DoctorPatients;
import com.zakary.dao.utils.PatientSickbed;
import com.zakary.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @RequestMapping("/patient")
    public String patient(){
        return "patient";
    }

    @RequestMapping("/getSickroomCount")
    //获取病房的总数
    public JsonResultDao getSickroomCount(){
        JsonResultDao jsonResultDao=new JsonResultDao();
        jsonResultDao.setCode(patientService.selectSickroomCount());
        return jsonResultDao;
    }
    //第一个功能，添加病人在treatment表里，病人必须在patient表里存在
    //json传patient_cert_code,treatment_time,treatment_name,treatment_fee
    @PostMapping("/doctor_addpatient")
    @ResponseBody
    public JsonResultDao addPatient(HttpServletRequest request,@RequestBody TreatmentDao treatmentDao){
        HttpSession session = request.getSession();
        String cert_code = (String)session.getAttribute("cert_code");
        treatmentDao.setDoctor_cert_code(cert_code);
        patientService.insertPatient(treatmentDao);
        JsonResultDao jsonResultDao=new JsonResultDao();
        jsonResultDao.setCode(0);
        jsonResultDao.setMsg("success");
        return jsonResultDao;
    }

    //第二个功能，查看此医生治疗的所有患者的基本信息,搜索患者
    @RequestMapping("/doctor_patients")
    @ResponseBody
    //json中含有cert_code（医生身份证），patient_cert_code
    public JsonResultDao getPatients(HttpServletRequest request){
        int pageNum=Integer.parseInt(request.getParameter("page"));
        int limit=Integer.parseInt(request.getParameter("limit"));
        JsonResultDao jsonResultDao=new JsonResultDao();
        HttpSession session = request.getSession();
        String cert_code = (String)session.getAttribute("cert_code");
        //String doctor_cert_code=cert_code;
        String patient_cert_code=request.getParameter("patient_cert_code");
        PageDao pageDao = new PageDao();
        pageDao.setPage((pageNum-1)*limit);
        pageDao.setLimit(limit);
        if(patient_cert_code!=null&&!patient_cert_code.trim().equals(""))
            pageDao.setPatient_cert_code(patient_cert_code);

        List<DoctorPatients> patients=patientService.getAllPatientByDoctorCert(pageDao,cert_code);
        jsonResultDao.setCode(0);
        jsonResultDao.setMsg("success");
        jsonResultDao.setData(patients);
        jsonResultDao.setCount(pageDao.getPatient_cert_code()==null?patientService.getPatientsCounts():patients.size());
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
        jsonResultDao.setCount(pageDao.getPatient_cert_code()==null?patientService.getPatientsCounts():patientSickbeds.size());
        return jsonResultDao;
    }
}
