package com.zakary.test;

import com.zakary.dao.*;
import com.zakary.dao.utils.DoctorPatients;
import com.zakary.services.DepartmentService;
import com.zakary.services.DoctorService;
import com.zakary.services.PatientService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MapperTest extends BaseTest {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Test
    public void test1(){
        DepartmentDao departmentDao = new DepartmentDao();
        departmentDao.setDepartment_name("部门3");
        departmentService.insertDepartment(departmentDao);
    }
    @Test
    public void test2(){
        PositionDao positionDao = new PositionDao();
        positionDao.setDepartment_name("部门2");
        positionDao.setDepartment_id(1);
        positionDao.setPosition_name("部门2的职位1");
        departmentService.insertPosition(positionDao);
    }
    @Test
    public void test3(){
        System.out.println(departmentService.getDepartments());
    }
    @Test
    public void test4(){
        DepartmentDao departmentDao = new DepartmentDao();
        departmentDao.setDepartment_id(1);
        System.out.println(departmentService.getPositions(departmentDao));
    }
    @Test
    public void test5(){
        for(int i=1;i<=10000;i++){
            DoctorDao doctorDao = new DoctorDao();
            doctorDao.setDoctor_position("doctorPosition"+i);
            doctorDao.setDoctor_name("doctor"+i);
            doctorDao.setDoctor_tel("12345678912");
            doctorDao.setType(0);
            doctorDao.setCert_code(i+"");
            doctorDao.setDoctor_department("doctorDepartment"+i);
            doctorDao.setPassword("e10adc3949ba59abbe56e057f20f883e");
            doctorDao.setDoctor_gender("男");
            doctorService.insertDoctor(doctorDao);
        }
    }
    @Test
    public void test6(){
        String id="2";
        int pageNum=1;
        int limit=10;
        PageDao pageDao = new PageDao();
        pageDao.setPage((pageNum-1)*limit);
        pageDao.setLimit(limit);
        pageDao.setPatient_id(1);
        List<DoctorPatients> patients=patientService.getAllPatientByDoctorId(pageDao,id);

    }
    @Test
    public void test7(){
        Date date=new Date(2000,1,1);
        System.out.println(date);
        TreatmentDao treatmentDao=new TreatmentDao();
        treatmentDao.setDoctor_id(2);
        treatmentDao.setPatient_id(5);
        treatmentDao.setTreatment_name("fff");
        treatmentDao.setTreatment_time(date);
        treatmentDao.setTreatment_fee(500.0);
        patientService.insertPatient(treatmentDao);
    }
}
