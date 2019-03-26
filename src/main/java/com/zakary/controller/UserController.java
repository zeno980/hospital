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

    @RequestMapping("/login")
    public String loginPage(){
        return "loginTest";
    }

    @RequestMapping("/index")
    public String indexPage(){
        return "index";
    }

    @RequestMapping("/login.do")
    @ResponseBody
    public JSONEntity login(@RequestBody Doctor doctor){
        System.out.println(doctor);
        Doctor realUser = service.getDoctor(doctor);
        JSONEntity jsonEntity = new JSONEntity();

        if(doctor.equals(realUser)){
            jsonEntity.setData("登录成功");
            jsonEntity.setCode("success");
            return jsonEntity;
        }
        else{
            jsonEntity.setData("账号或密码错误");
            jsonEntity.setCode("error");
            return jsonEntity;
        }
    }
    //@RequestMapping(value = "/", method = RequestMethod.GET)
    //public String index() {
    //    return "welcome";
    //}
}
