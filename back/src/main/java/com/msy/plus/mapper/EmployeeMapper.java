package com.msy.plus.mapper;

import com.msy.plus.core.mapper.MyMapper;
import com.msy.plus.entity.Employee;

import java.util.List;

public interface EmployeeMapper extends MyMapper<Employee> {
    List<Employee> listEmployeeWithRole(Employee employee);
}