package com.zakary.services;

import com.zakary.dao.Doctor;

public interface DoctorService {
    /**
     * 将前台拿到的User到dao层查询，返回User类型
     * @param user
     * @return
     */
    Doctor getDoctor(Doctor user);
}