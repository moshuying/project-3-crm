package com.yly.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yly.crm.dto.CustomerFollowUpHistoryDTO;
import com.yly.crm.entity.CustomerFollowUpHistory;

import java.util.Date;

/**
* @author MoShuYing
* @date 2021/05/21
*/
public interface CustomerFollowUpHistoryService extends IService<CustomerFollowUpHistory> {
    IPage<CustomerFollowUpHistoryDTO> listAndSearch(IPage<CustomerFollowUpHistoryDTO> qpage, String keyword, Long customerEntId, Date startTime, Date endTime, Integer type);
}
