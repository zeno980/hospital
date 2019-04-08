package com.zakary.controller;

import com.zakary.dao.DepartmentDao;
import com.zakary.dao.DoctorDao;
import com.zakary.dao.JsonResultDao;
import com.zakary.services.DepartmentService;
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
    @Autowired
    private DepartmentService departmentService;
    //这部分是页面映射
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }
    @RequestMapping("/resign")
    public String resignPage(){
        return "resign";
    }

    /**
     * 登录，此方法设置session
     * @param doctorDao
     * @param request
     * @return
     */
    @RequestMapping("/login.do")
    @ResponseBody
    public JsonResultDao login(@RequestBody DoctorDao doctorDao, HttpServletRequest request){
        doctorService.login(doctorDao);
        HttpSession session = request.getSession();
        session.setAttribute("cert_code", doctorDao.getCert_code());
        if(doctorDao.getPage().equals("doctor")){
            doctorDao.setPage("doctor/doctor");
        }else if(doctorDao.getPage().equals("patient")){
            doctorDao.setPage("patient/patient");
        }
        return new JsonResultDao(doctorDao.getPage());
    }

    /**
     * 获取基础部门
     * @return
     */
    @RequestMapping("/getDepartment.do")
    @ResponseBody
    public JsonResultDao getDepartment(){
        return new JsonResultDao(departmentService.getDepartments());
    }

    /**
     * 获取基础职位
     * @param doctorDao
     * @return
     */
    @RequestMapping("/getPosition.do")
    @ResponseBody
    public JsonResultDao getPosition(@RequestBody DepartmentDao doctorDao){
        return new JsonResultDao(departmentService.getPositions(doctorDao));
    }

    /**
     * 插入用户
     * @param doctorDao
     * @return
     */
    @RequestMapping("/insertDoctor.do")
    @ResponseBody
    public JsonResultDao rootInsertDoctor(@RequestBody DoctorDao doctorDao){
        doctorService.insertDoctor(doctorDao);
        return new JsonResultDao("success");
    }
}
