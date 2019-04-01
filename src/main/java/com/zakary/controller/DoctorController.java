package com.zakary.controller;

import com.alibaba.fastjson.JSONObject;
import com.zakary.dao.JSONEntity;
import com.zakary.services.RootManagerDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DoctorController {

    @Autowired
    private RootManagerDoctorService rootManagerDoctorService;

    @RequestMapping("doctor")
    public String doctor(){ return "doctor";}

    @RequestMapping("getDoctors.do")
    @ResponseBody
    public JSONEntity getDoctors(){
        return new JSONEntity(rootManagerDoctorService.getDoctorAll());
    }
}
