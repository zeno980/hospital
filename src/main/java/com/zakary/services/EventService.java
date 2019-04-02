package com.zakary.services;
import com.zakary.dao.*;
public interface EventService {
    int insertDocter(final DoctorDao d);
    int insertPatient(final PatientDao p);
    int deleteDoctor(final DoctorDao d);
    int deletePatient(final PatientDao p);
}
