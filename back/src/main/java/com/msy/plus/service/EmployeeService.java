package com.msy.plus.service;

import com.msy.plus.entity.Employee;
import com.msy.plus.core.service.Service;
import com.msy.plus.entity.EmployeeDetail;
import com.msy.plus.entity.EmployeeWithRoleDO;
import com.msy.plus.entity.RolePermissionDO;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.util.List;
import java.util.Map;

/**
* @author MoShuYing
* @date 2021/05/15
*/
public interface EmployeeService extends Service<Employee> {
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
    void saveRoles(Long id,List<Long> roles);

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
