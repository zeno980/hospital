package com.zakary.controller;

import com.alibaba.fastjson.JSONObject;
import com.zakary.dao.Doctor;
import com.zakary.dao.JSONEntity;
import com.zakary.dao.Page;
import com.zakary.dao.TableDatas;
import com.zakary.services.RootManagerDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        Page page = new Page();
        page.setPage((pagenum-1)*limit);
        page.setLimit(limit);
        List<Doctor> list = rootManagerDoctorService.getDoctorAll(page);
        TableDatas tableDatas = new TableDatas();
        tableDatas.setCode(0);
        tableDatas.setMsg("成功");
        tableDatas.setCount(rootManagerDoctorService.getDoctorsCounts());
        tableDatas.setData(list);
        return tableDatas;
    }
}
