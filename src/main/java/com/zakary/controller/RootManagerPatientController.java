package com.zakary.controller;


import com.zakary.dao.JSONEntity;
import com.zakary.dao.Patient;
import com.zakary.services.PatientService;
import com.zakary.services.RootManagerPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class RootManagerPatientController {

//    @RequestMapping("/rootDoctor")
//    public  String rootDoctorPage(){
//        return "rootDoctor";
//    }

    @Autowired
    private RootManagerPatientService rootManagerPatientService;

    @RequestMapping("/rootPatient")
    public String rootPatient(Model m) {
        List<Patient> patients = rootManagerPatientService.getPatientAll();
        m.addAttribute("patients",patients);
        return "rootPatient";
    }

    @Autowired
    private PatientService service;//自动注入数据库中的对象

    @RequestMapping("/RootInsertPatient")
    @ResponseBody
    public JSONEntity rootInsertPatient(@RequestBody Patient patient){
        System.out.println(patient);
        JSONEntity jsonEntity = new JSONEntity();
        Patient realUser = service.getPatient(patient);
        if((patient.getPatientid()==null)
                ||(patient.getPatientname()==null)
                ||(patient.getPatientage()==null)
                ||(patient.getPatientgender()==null)
                ||(patient.getPatienttel()==null)){

            jsonEntity.setCode("empty");
            jsonEntity.setData("输入值不能为空！");
        }
        else{
            if(realUser!=null){
                jsonEntity.setCode("alreadyExist");
                jsonEntity.setData("数据库中此编号已存在！");
            }
            else{
                patient.setType("1");
                rootManagerPatientService.insertPatient(patient);
                jsonEntity.setCode("success");
                jsonEntity.setData("添加成功！");
            }
        }
        return jsonEntity;
    }

    @RequestMapping("/RootUpdatePatient")
    @ResponseBody
    public JSONEntity rootUpdatePatient(@RequestBody Patient patient){
        System.out.print("需要修改的信息：");
        System.out.println(patient);
        JSONEntity jsonEntity=new JSONEntity();
        Patient realUser=service.getPatient(patient);
        System.out.print("数据库中的信息：");
        System.out.println(realUser);
        if((patient.getPatientname()==null)
                ||(patient.getPatientage()==null)
                ||(patient.getPatientgender()==null)
                ||(patient.getPatienttel()==null)) {
            jsonEntity.setCode("empty");
            jsonEntity.setData("输入值不能为空！");
        }
        else{
            patient.setType("1");
            rootManagerPatientService.updateInformation(patient);
            jsonEntity.setCode("success");
            jsonEntity.setData("修改成功！");
        }
        return jsonEntity;
    }

    @RequestMapping("/RootDeletePatient")
    @ResponseBody
    public JSONEntity rootDeleteDoctor(@RequestBody Patient patient){
        JSONEntity jsonEntity=new JSONEntity();
        Patient realUser=service.getPatient(patient);
        System.out.print("数据库需要删除的中的信息：");
        if(realUser.getPatientid()!=1) {
            rootManagerPatientService.deletePatient(patient);
            jsonEntity.setCode("success");
            jsonEntity.setData("删除成功！");
        }
        else{
            jsonEntity.setCode("error");
            jsonEntity.setData("不能删除管理员用户");
        }
        return jsonEntity;
    }
}
