package com.zakary.services;

import com.zakary.dao.Doctor;
import com.zakary.dao.Page;

import java.util.List;

public interface RootManagerDoctorService {
    List<Doctor> getDoctorAll(Page page);
    boolean insertDoctor(Doctor doctor);
    int updateInformation(Doctor doctor);
    int deleteDoctor(Doctor doctor);
    int getDoctorsCounts();
    List<Doctor> getDoctorsById(Integer doctorId);
}
