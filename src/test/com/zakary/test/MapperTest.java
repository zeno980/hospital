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
        for(int i=1;i<=100000;i++){
            DoctorDao doctorDao = new DoctorDao();
            doctorDao.setPassword("e10adc3949ba59abbe56e057f20f883e");
            doctorDao.setDoctor_department("department"+i);
            doctorDao.setDoctor_gender("男");
            doctorDao.setType(1);
            doctorDao.setDoctor_tel("13113111311");
            doctorDao.setDoctor_name("doctor"+i);
            doctorDao.setDoctor_position("position"+i);
            doctorService.insertDoctor(doctorDao);
        }
    }
}
