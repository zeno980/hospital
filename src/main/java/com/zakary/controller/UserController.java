package com.zakary.controller;

import com.zakary.dao.DoctorDao;
import com.zakary.dao.JsonResultDao;
import com.zakary.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired  //这个别忘了
    private DoctorService doctorService;
    //这部分是页面映射
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }
    @RequestMapping("/resign")
    public String resignPage(){
        return "resign";
    }

    @RequestMapping("/login.do")
    @ResponseBody
    public JsonResultDao login(@RequestBody DoctorDao doctorDao, HttpServletRequest request){
        doctorService.login(doctorDao);
        HttpSession session = request.getSession();
        session.setAttribute("doctor_id", doctorDao.getDoctor_id());
        return new JsonResultDao(doctorDao.getPage());
    }

    @RequestMapping("/register")
    @ResponseBody
    public JsonResultDao register(@RequestBody DoctorDao doctorDao,HttpServletRequest request){
        doctorService.insertDoctor(doctorDao);
        return new JsonResultDao("success");
    }
}
