package com.zakary.services;

import com.zakary.dao.DepartmentDao;
import com.zakary.dao.PositionDao;

import java.util.List;

public interface DepartmentService {
    void insertDepartment(DepartmentDao departmentDao);
    void insertPosition(PositionDao positionDao);
    List<DepartmentDao> getDepartments();
    List<PositionDao> getPositions(DepartmentDao departmentDao);
}
