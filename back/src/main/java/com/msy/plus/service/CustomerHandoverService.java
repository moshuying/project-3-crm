package com.msy.plus.service;

import com.msy.plus.dto.CustomerHandoverList;
import com.msy.plus.entity.CustomerHandover;
import com.msy.plus.core.service.Service;

import java.util.Date;
import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/21
*/
public interface CustomerHandoverService extends Service<CustomerHandover> {
    List<CustomerHandoverList> listAndSearch(String keyword, Date startTime, Date endTime);
}
