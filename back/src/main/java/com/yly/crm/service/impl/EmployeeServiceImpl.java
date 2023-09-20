package com.yly.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yly.crm.entity.EmployeeDetail;
import com.yly.crm.entity.EmployeeWithRoleDO;
import com.yly.crm.mapper.EmployeeMapper;
import com.yly.crm.entity.Employee;
import com.yly.crm.service.EmployeeService;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {


    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public EmployeeDetail getDetailById(Long id) {
        return this.employeeMapper.getDetailById(id);
    }

    @Override
    public IPage<EmployeeWithRoleDO> listEmployeeWithRole(IPage<EmployeeWithRoleDO> page, String keyword, Integer dept){
        if(dept==null){ dept=0; }
        return this.employeeMapper.listEmployeeWithRole(page,keyword,dept);
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
