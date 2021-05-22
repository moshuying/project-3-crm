package com.msy.plus.service.impl;

import com.msy.plus.entity.CFUHSearch;
import com.msy.plus.mapper.CustomerFollowUpHistoryMapper;
import com.msy.plus.entity.CustomerFollowUpHistory;
import com.msy.plus.service.CustomerFollowUpHistoryService;
import com.msy.plus.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/21
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerFollowUpHistoryServiceImpl extends AbstractService<CustomerFollowUpHistory> implements CustomerFollowUpHistoryService {
    @Resource
    private CustomerFollowUpHistoryMapper customerFollowUpHistoryMapper;


    @Override
    public List<CFUHSearch> listAndSearch(String keyword, Date startTime, Date endTime, Integer type) {
        String st=null,et=null;
        if(startTime!=null){
            st=new Timestamp(startTime.getTime()).toString();
        }
        if(endTime!=null){
            et=new Timestamp(endTime.getTime()).toString();
        }
        return this.customerFollowUpHistoryMapper.listAndSearch(keyword, st,et, type);
    }
}
