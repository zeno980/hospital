package com.zakary.controller;

import com.zakary.dao.DoctorDao;
import com.zakary.dao.JsonResultDao;
import com.zakary.dao.PageDao;
import com.zakary.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @RequestMapping("/doctor")
    public String doctor(){ return "doctor";}

    @RequestMapping("/getDoctors.do")
    @ResponseBody
    public JsonResultDao getDoctors(HttpServletRequest request){
        int pageNum = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        String id = request.getParameter("doctor_id");
        PageDao pageDao = new PageDao();
        pageDao.setPage((pageNum-1)*limit);
        pageDao.setLimit(limit);
        if(id!=null&&!id.trim().equals("")){
            pageDao.setDoctor_id(Integer.parseInt(id));
        }
        List<DoctorDao> doctors = doctorService.getDoctorAll(pageDao);
        JsonResultDao jsonResultDao = new JsonResultDao();
        jsonResultDao.setCode(0);
        jsonResultDao.setMsg("success");
        jsonResultDao.setData(doctors);
        jsonResultDao.setCount(pageDao.getDoctor_id()==null?doctorService.getDoctorsCounts():doctors.size());
        return jsonResultDao;
    }
    @RequestMapping("/deleteDoctor.do")
    @ResponseBody
    public JsonResultDao rootDeleteDoctor(@RequestBody DoctorDao doctorDao){
        return new JsonResultDao(doctorService.deleteDoctor(doctorDao));
    }
    @RequestMapping("/insertDoctor.do")
    @ResponseBody
    public JsonResultDao rootInsertDoctor(@RequestBody DoctorDao doctorDao){
        return new JsonResultDao(doctorService.insertDoctor(doctorDao));
    }

    @RequestMapping("/updateDoctor.do")
    @ResponseBody
    public JsonResultDao UpdateDoctor(@RequestBody DoctorDao doctorDao){
        return new JsonResultDao(doctorService.updateInformation(doctorDao));
    }
}
