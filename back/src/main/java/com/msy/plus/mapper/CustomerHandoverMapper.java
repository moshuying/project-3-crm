package com.msy.plus.mapper;

import com.msy.plus.core.mapper.MyMapper;
import com.msy.plus.dto.CustomerHandoverList;
import com.msy.plus.entity.CustomerHandover;

import java.util.Date;
import java.util.List;

public interface CustomerHandoverMapper extends MyMapper<CustomerHandover> {
    List<CustomerHandoverList> listAndSearch(String keyword, Date startTime, Date endTime);
}