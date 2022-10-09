package com.yly.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yly.crm.mapper.DepartmentMapper;
import com.yly.crm.entity.Department;
import com.yly.crm.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author MoShuYing
* @date 2021/05/12
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
