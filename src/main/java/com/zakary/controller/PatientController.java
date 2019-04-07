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
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    //第一个功能，添加病人在treatment表里，病人必须在patient表里存在
    @PostMapping("/doctor_addpatient")
    @ResponseBody
    public JsonResultDao addPatient(@RequestBody TreatmentDao treatmentDao){
        patientService.insertPatient(treatmentDao);
        JsonResultDao jsonResultDao=new JsonResultDao();
        jsonResultDao.setCode(0);
        jsonResultDao.setMsg("success");
        return jsonResultDao;
    }

    //第二个功能，查看此医生治疗的所有患者的基本信息,搜索患者
    @RequestMapping("/doctor_patients")
    @ResponseBody
    public JsonResultDao getPatients(HttpServletRequest request){
        int pageNum=Integer.parseInt(request.getParameter("page"));
        int limit=Integer.parseInt(request.getParameter("limit"));
        JsonResultDao jsonResultDao=new JsonResultDao();
        String doctor_cert_code=request.getParameter("doctor_cert_code");
        String patient_cert_code=request.getParameter("patient_cert_code");
        PageDao pageDao = new PageDao();
        pageDao.setPage((pageNum-1)*limit);
        pageDao.setLimit(limit);
        if(patient_cert_code!=null||!patient_cert_code.trim().equals(""))
            pageDao.setPatient_cert_code(patient_cert_code);

        List<DoctorPatients> patients=patientService.getAllPatientByDoctorCert(pageDao,doctor_cert_code);
        jsonResultDao.setCode(0);
        jsonResultDao.setMsg("success");
        jsonResultDao.setData(patients);
        jsonResultDao.setCount(pageDao.getPatient_cert_code()==null?patientService.getPatientsCounts():patients.size());
        return jsonResultDao;
    }

    //安排病房
    @RequestMapping("/ArrangeSickbed")
    @ResponseBody
    public JsonResultDao arrangeSickbed(HttpServletRequest request){
        SickbedDao sickbedDao=new SickbedDao();
        JsonResultDao jsonResultDao=new JsonResultDao();
        String patient_cert_code=request.getParameter("patient_cert_code");
        //int cert_code=Integer.parseInt(request.getParameter("cert_code"));
        int sickroom_id=Integer.parseInt(request.getParameter("sickroom"));
        int sickbed_id=Integer.parseInt(request.getParameter("sickbed"));
        //sickbedDao.setPatient_id(patient_id);
        sickbedDao.setPatient_cert_code(patient_cert_code);
        sickbedDao.setSickroom_id(sickroom_id);
        sickbedDao.setSickbed_id(sickbed_id);
        sickbedDao.setSickbed_state("full");

        patientService.arrangeSickbed(sickbedDao);

        return jsonResultDao;
    }

    //病房管理 查询病房信息,搜索患者所属的病房
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
        if(patient_cert_code!=null||!patient_cert_code.trim().equals(""))//查询患者的病床信息
            pageDao.setPatient_cert_code(patient_cert_code);
        List<PatientSickbed> patientSickbeds=patientService.getPatientsSickbedInfo(pageDao);
        jsonResultDao.setCode(0);
        jsonResultDao.setMsg("success");
        jsonResultDao.setData(patientSickbeds);
        jsonResultDao.setCount(pageDao.getPatient_cert_code()==null?patientService.getPatientsCounts():patientSickbeds.size());
        return jsonResultDao;
    }
}
