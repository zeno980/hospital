package com.zakary.services;

import com.zakary.dao.Doctor;

import java.util.List;

public interface RootManagerDoctorService {
    List<Doctor> getDoctorAll();
    int insertDoctor(Doctor doctor);
    int updateInformation(Doctor doctor);
    int deleteDoctor(Doctor doctor);
}
