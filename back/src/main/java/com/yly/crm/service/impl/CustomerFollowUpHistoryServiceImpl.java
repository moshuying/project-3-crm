package com.yly.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yly.crm.entity.CFUHSearch;
import com.yly.crm.mapper.CustomerFollowUpHistoryMapper;
import com.yly.crm.entity.CustomerFollowUpHistory;
import com.yly.crm.service.CustomerFollowUpHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
* @author MoShuYing
* @date 2021/05/21
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerFollowUpHistoryServiceImpl extends ServiceImpl<CustomerFollowUpHistoryMapper,CustomerFollowUpHistory> implements CustomerFollowUpHistoryService {
    @Resource
    private CustomerFollowUpHistoryMapper customerFollowUpHistoryMapper;


    @Override
    public  IPage<CFUHSearch>  listAndSearch(IPage<CFUHSearch> qpage,String keyword, Date startTime, Date endTime, Integer type) {
        String st=null,et=null;
        if(startTime!=null){
            st=new Timestamp(startTime.getTime()).toString();
        }
        if(endTime!=null){
            et=new Timestamp(endTime.getTime()).toString();
        }
        return this.customerFollowUpHistoryMapper.listAndSearch(qpage,keyword, st,et, type);
    }
}
