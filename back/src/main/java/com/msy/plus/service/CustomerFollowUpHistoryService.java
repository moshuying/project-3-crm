package com.msy.plus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.msy.plus.entity.CFUHSearch;
import com.msy.plus.entity.CustomerFollowUpHistory;

import java.util.Date;
import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/21
*/
public interface CustomerFollowUpHistoryService extends IService<CustomerFollowUpHistory> {
    List<CFUHSearch> listAndSearch(String keyword, Date startTime, Date endTime, Integer type);
}
