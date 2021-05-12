package com.zoctan.seedling.service.impl;

import com.zoctan.seedling.mapper.DepartmentMapper;
import com.zoctan.seedling.entity.Department;
import com.zoctan.seedling.service.DepartmentService;
import com.zoctan.seedling.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author MoShuYing
* @date 2021/05/12
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class DepartmentServiceImpl extends AbstractService<Department> implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

}
