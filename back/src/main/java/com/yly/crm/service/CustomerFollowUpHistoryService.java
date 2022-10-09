package com.yly.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yly.crm.entity.CFUHSearch;
import com.yly.crm.entity.CustomerFollowUpHistory;

import java.util.Date;

/**
* @author MoShuYing
* @date 2021/05/21
*/
public interface CustomerFollowUpHistoryService extends IService<CustomerFollowUpHistory> {
    IPage<CFUHSearch> listAndSearch(IPage<CFUHSearch> qpage,String keyword, Date startTime, Date endTime, Integer type);
}
