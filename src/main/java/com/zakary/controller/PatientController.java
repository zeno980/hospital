package com.zakary.controller;


import com.zakary.dao.*;
import com.zakary.dao.utils.DoctorPatients;
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

    //第一个功能，添加病人
    @PostMapping("/doctor_addpatient")
    @ResponseBody
    public JsonResultDao addPatient(@RequestBody TreatmentDao treatmentDao){
        patientService.insertPatient(treatmentDao);
        JsonResultDao jsonResultDao=new JsonResultDao();
        jsonResultDao.setCode(0);
        jsonResultDao.setMsg("success");
        return jsonResultDao;
    }

    //第二个功能，查看患者的基本信息
    @RequestMapping("/doctor_patients")
    @ResponseBody
    public JsonResultDao getPatients(HttpServletRequest request){
        int pageNum=Integer.parseInt(request.getParameter("page"));
        int limit=Integer.parseInt(request.getParameter("limit"));
        JsonResultDao jsonResultDao=new JsonResultDao();
        String id=request.getParameter("id");
        PageDao pageDao = new PageDao();
        pageDao.setPage((pageNum-1)*limit);
        pageDao.setLimit(limit);

        List<DoctorPatients> patients=patientService.getAllPatientByDoctorId(id);
        jsonResultDao.setCode(0);
        jsonResultDao.setMsg("success");
        jsonResultDao.setData(patients);
        jsonResultDao.setCount(pageDao.getDoctor_id()==null?patientService.getPatientsCounts():patients.size());
        return jsonResultDao;
    }

}
