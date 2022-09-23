package com.msy.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.msy.plus.dto.CustomerHandoverList;
import com.msy.plus.entity.CustomerHandover;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface CustomerHandoverMapper extends BaseMapper<CustomerHandover> {
    IPage<CustomerHandoverList>  listAndSearch(IPage<CustomerHandoverList> customerHandoverListIPage,String keyword, Date startTime, Date endTime);
}