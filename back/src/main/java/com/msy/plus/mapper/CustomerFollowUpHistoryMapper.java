package com.msy.plus.mapper;

import com.msy.plus.core.mapper.MyMapper;
import com.msy.plus.entity.CFUHSearch;
import com.msy.plus.entity.CustomerFollowUpHistory;

import java.util.List;

public interface CustomerFollowUpHistoryMapper extends MyMapper<CustomerFollowUpHistory> {
    List<CFUHSearch> listAndSearch(String keyword, String startTime, String endTime, Integer type);
}