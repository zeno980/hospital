package com.zakary.services;
import com.zakary.dao.*;
public interface EventService {
    int insertDocter(final Doctor d);
    int insertPatient(final Patient p);
    int deleteDoctor(final Doctor d);
    int deletePatient(final Patient p);
}
