package com.msy.plus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.msy.plus.dto.CustomerHandoverList;
import com.msy.plus.entity.CustomerHandover;

import java.util.Date;
import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/21
*/
public interface CustomerHandoverService extends IService<CustomerHandover> {
    List<CustomerHandoverList> listAndSearch(String keyword, Date startTime, Date endTime);
}
