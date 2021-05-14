package com.zoctan.seedling.service;

import com.zoctan.seedling.entity.Employee;
import com.zoctan.seedling.core.service.Service;

import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/13
*/
public interface EmployeeService extends Service<Employee> {
    @Override
    List<Employee> listAll();
}
