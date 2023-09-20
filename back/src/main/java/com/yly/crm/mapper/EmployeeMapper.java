package com.yly.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yly.crm.entity.Employee;
import com.yly.crm.entity.EmployeeDetail;
import com.yly.crm.entity.EmployeeWithRoleDO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeMapper extends BaseMapper<Employee> {
    EmployeeDetail getDetailById(Long id);

    /**
     * 分页查询员工
     * @return
     */
    IPage<EmployeeWithRoleDO> listEmployeeWithRole(IPage<EmployeeWithRoleDO> page, String keyword, int dept);

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