package com.yly.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yly.crm.mapper.PermissionMapper;
import com.yly.crm.entity.Permission;
import com.yly.crm.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author MoShuYing
* @date 2021/05/14
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

}
