package com.yly.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yly.crm.entity.Employee;
import com.yly.crm.entity.EmployeeDetail;
import com.yly.crm.entity.EmployeeWithRoleDO;
import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/15
*/
public interface EmployeeService extends IService<Employee> {
    EmployeeDetail getDetailById(Long id);

    /**
     * 分页查询员工
     * @return
     */
    IPage<EmployeeWithRoleDO> listEmployeeWithRole(IPage<EmployeeWithRoleDO> page, String keyword, Integer dept);

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
