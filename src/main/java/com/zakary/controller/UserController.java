package com.zakary.controller;

import com.zakary.dao.Doctor;
import com.zakary.dao.JSONEntity;
import com.zakary.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    @Autowired  //这个别忘了
    private DoctorService doctorService;
    //这部分是页面映射
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
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
    public JSONEntity login(@RequestBody Doctor doctor, HttpServletRequest request){
        doctorService.getDoctor(doctor);
        HttpSession session = request.getSession();
        session.setAttribute("doctorId",doctor.getDoctorid());
        return new JSONEntity(doctor.getPage());
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
