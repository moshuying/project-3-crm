package com.msy.plus.service.impl;

import com.msy.plus.mapper.CustomerFollowUpHistoryMapper;
import com.msy.plus.entity.CustomerFollowUpHistory;
import com.msy.plus.service.CustomerFollowUpHistoryService;
import com.msy.plus.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author MoShuYing
* @date 2021/05/21
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerFollowUpHistoryServiceImpl extends AbstractService<CustomerFollowUpHistory> implements CustomerFollowUpHistoryService {
    @Resource
    private CustomerFollowUpHistoryMapper customerFollowUpHistoryMapper;

}
