package com.zakary.controller;

import com.zakary.dao.Doctor;
import com.zakary.dao.JSONEntity;
import com.zakary.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @Autowired  //这个别忘了
    private DoctorService service;
    //这部分是页面映射
    @RequestMapping("/login")
    public String loginPage(){
        return "loginTest";
    }
    @RequestMapping("/rootSelectPage")
    public String rootSelectPage(){
        return "rootSelect";
    }
    @RequestMapping("/rootPatient")
    public  String rootPatientPage(){
        return "rootPatient";
    }
    @RequestMapping("/doctorManagerPage")
    public String doctorManagerPage(){
        return "doctorManager";
    }

    @RequestMapping("/login.do")
    @ResponseBody
    public JSONEntity login(@RequestBody Doctor doctor){
        System.out.println(doctor);
        Doctor realUser = service.getDoctor(doctor);
        System.out.println(realUser);
        JSONEntity jsonEntity = new JSONEntity();
        //if(doctor.equals(realUser)){
        if(null!=realUser){
            //类型检查
            if(realUser.getType()==0)
                jsonEntity.setCode("root");
            else
                jsonEntity.setCode("doctor");
            jsonEntity.setData("登录成功");
            //jsonEntity.setCode("success");
            return jsonEntity;
        }
        else{
            jsonEntity.setData("账号或密码错误");
            jsonEntity.setCode("login");
            return jsonEntity;
        }
    }

    @RequestMapping("/rootSelect" )
    public JSONEntity selectPage(@RequestParam("user") String user){
        System.out.println(user);
        JSONEntity jsonEntity = new JSONEntity();
        if(user.equals("doctor"))
            jsonEntity.setCode("doctor");
        else if(user.equals("patient"))
            jsonEntity.setCode("patient");
        return jsonEntity;
    }
}
