package com.msy.plus.mapper;

import com.msy.plus.core.mapper.MyMapper;
import com.msy.plus.entity.Employee;
import com.msy.plus.entity.EmployeeDetail;
import com.msy.plus.entity.EmployeeWithRoleDO;

import java.util.List;

public interface EmployeeMapper extends MyMapper<Employee> {
    EmployeeDetail getDetailById(Long id);

    /**
     * 分页查询员工
     * @return
     */
    List<EmployeeWithRoleDO> listEmployeeWithRole();

    /**
     * 保存员工角色信息
     * @param id
     * @param roles
     * @return
     */
    void saveRoles(Long id, List<Long> roles);

    /**
     * 删除员工权限
     * @param id
     * @return
     */
    int deleteEmployeeWithRole(Long id);
    int deleteEmployeeWithRoleItem(Long id,Long roleId);

    /**
     * 获取所有中间表的id
     * @param id
     * @return List<RolePermissionDO>
     */
    List<Long> getAllEmployeeRoleTableRow(Long id);

}