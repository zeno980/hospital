package com.zakary.services;

import com.zakary.dao.DoctorDao;
import com.zakary.dao.PageDao;

import java.util.List;

public interface DoctorService {
    boolean login(DoctorDao doctorDao);
    List<DoctorDao> getDoctorAll(PageDao pageDao);
    boolean insertDoctor(DoctorDao doctorDao);
    int updateInformation(DoctorDao doctorDao);
    int deleteDoctor(DoctorDao doctorDao);
    int getDoctorsCounts();
}