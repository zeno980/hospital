package com.zakary.controller;

import com.alibaba.fastjson.JSONObject;
import com.zakary.dao.Doctor;
import com.zakary.dao.JSONEntity;
import com.zakary.dao.Page;
import com.zakary.dao.TableDatas;
import com.zakary.services.RootManagerDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    private RootManagerDoctorService rootManagerDoctorService;

    @RequestMapping("doctor")
    public String doctor(){ return "doctor";}

    @RequestMapping("getDoctors.do")
    @ResponseBody
    public TableDatas getDoctors(HttpServletRequest request){
        int pagenum = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        String id = request.getParameter("doctorId");
        TableDatas tableDatas = new TableDatas();
        Page page = new Page();
        page.setPage((pagenum-1)*limit);
        page.setLimit(limit);
        if(id!=null&&!id.trim().equals("")){
            List<Doctor> doctors = rootManagerDoctorService.getDoctorsById(Integer.parseInt(id));
            tableDatas.setCount(0);
            tableDatas.setMsg("成功");
            tableDatas.setCount(doctors.size());
            tableDatas.setData(doctors);
            return tableDatas;
        }
        List<Doctor> list = rootManagerDoctorService.getDoctorAll(page);
        tableDatas.setCode(0);
        tableDatas.setMsg("成功");
        tableDatas.setCount(rootManagerDoctorService.getDoctorsCounts());
        tableDatas.setData(list);
        return tableDatas;
    }
    @RequestMapping("/RootDeleteDoctor")
    @ResponseBody
    public JSONEntity rootDeleteDoctor(@RequestBody Doctor doctor){
        return new JSONEntity(rootManagerDoctorService.deleteDoctor(doctor));
    }
}
