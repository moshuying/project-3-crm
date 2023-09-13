package com.yly.crm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseMapper;
import com.yly.crm.dto.CustomerFollowUpHistoryDTO;
import com.yly.crm.entity.CustomerFollowUpHistory;
import org.springframework.stereotype.Component;

@Component
public interface CustomerFollowUpHistoryMapper extends MPJBaseMapper<CustomerFollowUpHistory> {
    IPage<CustomerFollowUpHistoryDTO> listAndSearch(IPage<CustomerFollowUpHistoryDTO> qpage, String keyword, Long customerEntId, String startTime, String endTime, Integer type);
}