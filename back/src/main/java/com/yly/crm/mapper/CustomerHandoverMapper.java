package com.yly.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yly.crm.dto.CustomerHandoverList;
import com.yly.crm.entity.CustomerHandover;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface CustomerHandoverMapper extends BaseMapper<CustomerHandover> {
    IPage<CustomerHandoverList>  listAndSearch(IPage<CustomerHandoverList> customerHandoverListIPage,String keyword, Date startTime, Date endTime);
}