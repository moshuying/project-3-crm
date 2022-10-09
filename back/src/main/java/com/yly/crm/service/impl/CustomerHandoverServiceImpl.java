package com.yly.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yly.crm.dto.CustomerHandoverList;
import com.yly.crm.mapper.CustomerHandoverMapper;
import com.yly.crm.entity.CustomerHandover;
import com.yly.crm.service.CustomerHandoverService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
* @author MoShuYing
* @date 2021/05/21
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerHandoverServiceImpl extends ServiceImpl<CustomerHandoverMapper, CustomerHandover> implements CustomerHandoverService {
    @Resource
    private CustomerHandoverMapper customerHandoverMapper;

    @Override
    public IPage<CustomerHandoverList>  listAndSearch(IPage<CustomerHandoverList> customerHandoverListIPage, String keyword, Date startTime, Date endTime) {
        return this.customerHandoverMapper.listAndSearch(customerHandoverListIPage,keyword, startTime, endTime);
    }
}
