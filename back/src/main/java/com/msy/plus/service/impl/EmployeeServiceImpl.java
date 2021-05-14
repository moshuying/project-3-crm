package com.msy.plus.service.impl;

import com.msy.plus.mapper.EmployeeMapper;
import com.msy.plus.entity.Employee;
import com.msy.plus.service.EmployeeService;
import com.msy.plus.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/13
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl extends AbstractService<Employee> implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> listAll() {
//        employeeMapper.
        return super.listAll();
    }
}
