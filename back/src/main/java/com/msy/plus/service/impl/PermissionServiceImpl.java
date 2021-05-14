package com.msy.plus.service.impl;

import com.msy.plus.mapper.PermissionMapper;
import com.msy.plus.entity.Permission;
import com.msy.plus.service.PermissionService;
import com.msy.plus.core.service.AbstractService;
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
