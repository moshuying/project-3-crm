package com.msy.plus.mapper;

import com.msy.plus.core.mapper.MyMapper;
import com.msy.plus.entity.Employee;
import com.msy.plus.entity.EmployeeDetail;
import com.msy.plus.entity.EmployeeWithRoleDO;

import java.util.List;

public interface EmployeeMapper extends MyMapper<Employee> {
    EmployeeDetail getDetailById(Long id);
    List<EmployeeWithRoleDO> listEmployeeWithRole();
}