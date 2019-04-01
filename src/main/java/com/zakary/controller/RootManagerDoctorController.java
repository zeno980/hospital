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

    @RequestMapping("/RootInsertDoctor")
    @ResponseBody
    public JSONEntity rootInsertDoctor(@RequestBody Doctor doctor){
        return new JSONEntity(rootManagerDoctorService.insertDoctor(doctor));
    }

    @RequestMapping("/RootUpdateDoctor")
    @ResponseBody
    public JSONEntity rootUpdateDoctor(@RequestBody Doctor doctor){
        return new JSONEntity(rootManagerDoctorService.updateInformation(doctor));
    }

    @RequestMapping("/RootDeleteDoctor")
    @ResponseBody
    public JSONEntity rootDeleteDoctor(@RequestBody Doctor doctor){
        return new JSONEntity(rootManagerDoctorService.deleteDoctor(doctor));
    }
}
