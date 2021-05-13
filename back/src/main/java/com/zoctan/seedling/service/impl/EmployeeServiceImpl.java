package com.zoctan.seedling.service.impl;

import com.zoctan.seedling.mapper.EmployeeMapper;
import com.zoctan.seedling.entity.Employee;
import com.zoctan.seedling.service.EmployeeService;
import com.zoctan.seedling.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author MoShuYing
* @date 2021/05/13
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl extends AbstractService<Employee> implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

}
