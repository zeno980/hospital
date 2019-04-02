package com.zakary.test;

import com.zakary.dao.DoctorDao;
import com.zakary.services.DoctorService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MapperTest extends BaseTest {
    @Autowired
    private DoctorService doctorService;
    @Test
    public void test1(){
        DoctorDao doctorDao = new DoctorDao();
        doctorDao.setDoctor_id(1);
        doctorDao.setPassword("e10adc3949ba59abbe56e057f20f883e");
        boolean b = doctorService.login(doctorDao);
        System.out.println(b);
    }
}
