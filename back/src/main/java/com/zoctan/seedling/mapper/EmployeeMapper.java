package com.zoctan.seedling.mapper;

import com.zoctan.seedling.core.mapper.MyMapper;
import com.zoctan.seedling.entity.Employee;

import java.util.List;

public interface EmployeeMapper extends MyMapper<Employee> {
    List<Employee> listEmployeeWithRole(Employee employee);
}