package com.yly.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yly.crm.entity.CFUHSearch;
import com.yly.crm.entity.CustomerFollowUpHistory;
import org.springframework.stereotype.Component;

@Component
public interface CustomerFollowUpHistoryMapper extends BaseMapper<CustomerFollowUpHistory> {
    IPage<CFUHSearch> listAndSearch(IPage<CFUHSearch> qpage, String keyword, String startTime, String endTime, Integer type);
}