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

    @RequestMapping("/login.do")
    @ResponseBody
    public JSONEntity login(@RequestBody Doctor doctor, HttpServletRequest request){
        doctorService.getDoctor(doctor);
        HttpSession session = request.getSession();
        session.setAttribute("doctorId",doctor.getDoctorid());
        return new JSONEntity(doctor.getPage());
    }
}
