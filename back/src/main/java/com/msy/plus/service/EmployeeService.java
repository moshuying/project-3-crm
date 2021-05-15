package com.msy.plus.service;

import com.msy.plus.entity.Employee;
import com.msy.plus.core.service.Service;
import com.msy.plus.entity.EmployeeDetail;
import com.msy.plus.entity.EmployeeWithRoleDO;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/15
*/
public interface EmployeeService extends Service<Employee> {
    EmployeeDetail getDetailById(Long id);
    List<EmployeeWithRoleDO> listEmployeeWithRole();
}
