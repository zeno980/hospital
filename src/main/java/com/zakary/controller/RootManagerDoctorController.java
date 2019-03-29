package com.zakary.controller;

import com.zakary.dao.Doctor;
import com.zakary.services.RootManagerDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
