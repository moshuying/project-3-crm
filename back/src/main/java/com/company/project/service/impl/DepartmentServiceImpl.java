package com.company.project.service.impl;

import com.company.project.dao.DepartmentMapper;
import com.company.project.model.Department;
import com.company.project.service.DepartmentService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2021/05/12.
 */
@Service
@Transactional
public class DepartmentServiceImpl extends AbstractService<Department> implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

}
