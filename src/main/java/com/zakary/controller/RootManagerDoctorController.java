package com.zakary.controller;

import com.zakary.dao.Doctor;
import com.zakary.dao.JSONEntity;
import com.zakary.services.DoctorService;
import com.zakary.services.RootManagerDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class RootManagerDoctorController {

//    @RequestMapping("/rootDoctor")
//    public  String rootDoctorPage(){
//        return "rootDoctor";
//    }

    @Autowired
    private RootManagerDoctorService rootManagerDoctorService;

    @RequestMapping("/rootDoctor")
    public String rootDoctor(Model m) {
        List<Doctor> doctors = rootManagerDoctorService.getDoctorAll();
        m.addAttribute("doctors",doctors);
        return "rootDoctor";
    }

    @Autowired
    private DoctorService service;//自动注入数据库中的对象

    @RequestMapping("/RootInsertDoctor")
    @ResponseBody
    public JSONEntity rootInsertDoctor(@RequestBody Doctor doctor){
        System.out.println(doctor);
        JSONEntity jsonEntity = new JSONEntity();
        Doctor realUser = service.getDoctor(doctor);
        if((doctor.getDoctorid()==null)
                ||(doctor.getDoctorname()==null)
                ||(doctor.getDoctordepartment()==null)
                ||(doctor.getDoctorposition()==null)
                ||(doctor.getDoctorgender()==null)
                ||(doctor.getDoctortel()==null)
                ||(doctor.getPassword()==null)) {
            jsonEntity.setCode("empty");
            jsonEntity.setData("输入值不能为空！");
        }
        else{
            if(realUser!=null){
                jsonEntity.setCode("alreadyExist");
                jsonEntity.setData("数据库中此编号已存在！");
            }
            else{
                doctor.setType(1);
                rootManagerDoctorService.insertDoctor(doctor);
                jsonEntity.setCode("success");
                jsonEntity.setData("添加成功！");
            }
        }
        return jsonEntity;
    }

    @RequestMapping("/RootUpdateDoctor")
    @ResponseBody
    public JSONEntity rootUpdateDoctor(@RequestBody Doctor doctor){
        System.out.print("需要修改的信息：");
        System.out.println(doctor);
        JSONEntity jsonEntity=new JSONEntity();
        Doctor realUser=service.getDoctor(doctor);
        System.out.print("数据库中的信息：");
        System.out.println(realUser);
        if((doctor.getDoctorname()==null)
                ||(doctor.getDoctordepartment()==null)
                ||(doctor.getDoctorposition()==null)
                ||(doctor.getDoctorgender()==null)
                ||(doctor.getDoctortel()==null)
                ||(doctor.getPassword()==null)) {
            jsonEntity.setCode("empty");
            jsonEntity.setData("输入值不能为空！");
        }
        else{
            doctor.setType(1);
            rootManagerDoctorService.updateInformation(doctor);
            jsonEntity.setCode("success");
            jsonEntity.setData("修改成功！");
        }
        return jsonEntity;
    }

    @RequestMapping("/RootDeleteDoctor")
    @ResponseBody
    public JSONEntity rootDeleteDoctor(@RequestBody Doctor doctor){
        JSONEntity jsonEntity=new JSONEntity();
        Doctor realUser=service.getDoctor(doctor);
        System.out.print("数据库需要删除的中的信息：");
        if(realUser.getDoctorid()!=1) {
            rootManagerDoctorService.deleteDoctor(doctor);
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
