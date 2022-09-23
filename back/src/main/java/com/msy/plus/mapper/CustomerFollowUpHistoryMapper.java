package com.msy.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.msy.plus.entity.CFUHSearch;
import com.msy.plus.entity.CustomerFollowUpHistory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerFollowUpHistoryMapper extends BaseMapper<CustomerFollowUpHistory> {
    IPage<CFUHSearch> listAndSearch(IPage<CFUHSearch> qpage, String keyword, String startTime, String endTime, Integer type);
}