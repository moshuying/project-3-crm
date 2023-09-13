package com.yly.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.mysql.cj.util.StringUtils;
import com.yly.crm.dto.CustomerFollowUpHistoryDTO;
import com.yly.crm.entity.CustomerContacts;
import com.yly.crm.entity.Employee;
import com.yly.crm.mapper.CustomerFollowUpHistoryMapper;
import com.yly.crm.entity.CustomerFollowUpHistory;
import com.yly.crm.service.CustomerFollowUpHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

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
    public  IPage<CustomerFollowUpHistoryDTO>  listAndSearch(IPage<CustomerFollowUpHistoryDTO> qpage, String keyword, Long customerEntId, Date startTime, Date endTime, Integer type) {
//        String st=null,et=null;
//        if(startTime!=null){
//            st=new Timestamp(startTime.getTime()).toString();
//        }
//        if(endTime!=null){
//            et=new Timestamp(endTime.getTime()).toString();
//        }

        MPJLambdaWrapper wrapper = new MPJLambdaWrapper<CustomerFollowUpHistoryDTO>()
                .selectAll(CustomerFollowUpHistory.class)
                .selectAs(CustomerContacts::getName,CustomerFollowUpHistoryDTO::getCustomerName)
                .selectAs(Employee::getName,CustomerFollowUpHistoryDTO::getInputName)
                .leftJoin(CustomerContacts.class,CustomerContacts::getId, CustomerFollowUpHistory::getCustomerid)
                .leftJoin(Employee.class, Employee::getId,CustomerFollowUpHistory::getInputuser)
                .eq(Optional.ofNullable(customerEntId).isPresent(), CustomerFollowUpHistory::getCustomerEntId, customerEntId)
                .orderBy(true,false,CustomerFollowUpHistory::getTracetime)
                .like(!StringUtils.isNullOrEmpty(keyword),CustomerFollowUpHistory::getTracedetails,keyword);


        return customerFollowUpHistoryMapper.selectJoinPage(qpage, CustomerFollowUpHistoryDTO.class,wrapper);
    }
}
