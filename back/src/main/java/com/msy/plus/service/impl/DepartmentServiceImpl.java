package com.msy.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msy.plus.mapper.DepartmentMapper;
import com.msy.plus.entity.Department;
import com.msy.plus.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author MoShuYing
* @date 2021/05/12
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
