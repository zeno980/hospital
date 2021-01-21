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
    @RequestMapping("/register" )
    public String register(){
        return "register";
    }
    @RequestMapping(value = "/doctorRegister.do",method=RequestMethod.POST)
    public String docregister(Doctor user) {
        Integer doctorId=user.getDoctorid();
        // 如果数据库中没有该用户，可以注册，否则跳转页面
        if (service.findByDoctorId(doctorId) == null) {
            // 添加用户
            service.register(user);// 注册成功跳转到主页面
            return "loginTest";
        }else {
            // 注册失败跳转到错误页面
            return "error";
        }

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
