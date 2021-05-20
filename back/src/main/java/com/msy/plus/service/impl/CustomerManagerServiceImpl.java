package com.msy.plus.service.impl;

import com.msy.plus.mapper.CustomerManagerMapper;
import com.msy.plus.entity.CustomerManager;
import com.msy.plus.service.CustomerManagerService;
import com.msy.plus.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author MoShuYing
* @date 2021/05/20
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerManagerServiceImpl extends AbstractService<CustomerManager> implements CustomerManagerService {
    @Resource
    private CustomerManagerMapper customerManagerMapper;

}
