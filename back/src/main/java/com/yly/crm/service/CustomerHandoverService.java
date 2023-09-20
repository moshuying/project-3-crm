package com.yly.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yly.crm.dto.CustomerHandoverList;
import com.yly.crm.entity.CustomerHandover;

import java.util.Date;

/**
* @author MoShuYing
* @date 2021/05/21
*/
public interface CustomerHandoverService extends IService<CustomerHandover> {
    IPage<CustomerHandoverList>  listAndSearch(IPage<CustomerHandoverList> customerHandoverListIPage, String keyword, Date startTime, Date endTime);
}
