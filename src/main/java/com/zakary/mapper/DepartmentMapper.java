package com.zakary.mapper;

import com.zakary.dao.DepartmentDao;
import com.zakary.dao.PositionDao;

import java.util.List;

public interface DepartmentMapper {
    int insertDepartment(DepartmentDao departmentDao);
    int insertPosition(PositionDao positionDao);
    List<DepartmentDao> selectDepartments();
    List<PositionDao> selectPositions(DepartmentDao departmentDao);
    int updateDepartment(DepartmentDao departmentDao);
    int updatePosition(PositionDao positionDao);
    DepartmentDao selectDepartmentByName(DepartmentDao departmentDao);
    PositionDao selectPositionByName(PositionDao positionDao);
}
