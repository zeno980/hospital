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
        String inactive = request.getParameter("inactive");
        String id = request.getParameter("doctor_id");
        PageDao pageDao = new PageDao();
        pageDao.setPage((pageNum-1)*limit);
        pageDao.setLimit(limit);
        if(id!=null&&!id.trim().equals("")){
            pageDao.setCert_code(Integer.parseInt(id));
        }
        if(inactive != null && !inactive.trim().equals("")){
            pageDao.setActive("N");
        }else{
            pageDao.setActive("Y");
        }
        List<DoctorDao> doctors = doctorService.getDoctorAll(pageDao);
        JsonResultDao jsonResultDao = new JsonResultDao();
        jsonResultDao.setCode(0);
        jsonResultDao.setMsg("success");
        jsonResultDao.setData(doctors);
        jsonResultDao.setCount(pageDao.getCert_code()==null?doctorService.getDoctorsCounts(pageDao.getActive()):doctors.size());
        return jsonResultDao;
    }
    @RequestMapping("/deleteDoctor.do")
    @ResponseBody
    public JsonResultDao rootDeleteDoctor(@RequestBody DoctorDao doctorDao){
        doctorService.deleteDoctor(doctorDao);
        return new JsonResultDao("success");
    }

    @RequestMapping("/updateDoctor.do")
    @ResponseBody
    public JsonResultDao UpdateDoctor(@RequestBody DoctorDao doctorDao){
        doctorService.updateInformation(doctorDao);
        return new JsonResultDao("success");
    }
    @ResponseBody
    @RequestMapping("/getInactiveDoctorCounts.do")
    public JsonResultDao getInactiveDoctorCounts(){
        return new JsonResultDao(doctorService.getDoctorsCounts("N"));
    }
}
