package com.msy.plus.service;

import com.msy.plus.entity.Employee;
import com.msy.plus.core.service.Service;

import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/13
*/
public interface EmployeeService extends Service<Employee> {
    @Override
    List<Employee> listAll();
}
