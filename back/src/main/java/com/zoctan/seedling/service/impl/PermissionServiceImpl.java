package com.zoctan.seedling.service.impl;

import com.zoctan.seedling.mapper.PermissionMapper;
import com.zoctan.seedling.entity.Permission;
import com.zoctan.seedling.service.PermissionService;
import com.zoctan.seedling.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author MoShuYing
* @date 2021/05/14
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl extends AbstractService<Permission> implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

}
