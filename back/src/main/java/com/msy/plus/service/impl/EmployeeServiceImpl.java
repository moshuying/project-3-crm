package com.msy.plus.service.impl;

import com.msy.plus.entity.EmployeeDetail;
import com.msy.plus.entity.EmployeeWithRoleDO;
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
* @date 2021/05/15
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl extends AbstractService<Employee> implements EmployeeService {


    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public EmployeeDetail getDetailById(Long id) {
        return this.employeeMapper.getDetailById(id);
    }

    @Override
    public List<EmployeeWithRoleDO> listEmployeeWithRole(String keyword,Integer dept){
        if(dept==null){ dept=0; }
        return this.employeeMapper.listEmployeeWithRole(keyword,dept);
    }

    @Override
    public void saveRoles(Long id, List<Long> roles) {
        this.employeeMapper.saveRoles(id,roles);
    }

    @Override
    public int deleteEmployeeWithRole(Long id) {
        return this.employeeMapper.deleteEmployeeWithRole(id);
    }

    @Override
    public int deleteEmployeeWithRoleItem(Long id, Long roleId) {
        return this.employeeMapper.deleteEmployeeWithRoleItem(id,roleId);
    }

    @Override
    public List<Long> getAllEmployeeRoleTableRow(Long id) {
        return this.employeeMapper.getAllEmployeeRoleTableRow(id);
    }
}
