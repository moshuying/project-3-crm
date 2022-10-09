package com.yly.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yly.crm.dto.AnalysisQuery;
import com.yly.crm.dto.CustomerManagerList;
import com.yly.crm.entity.Analysis;
import com.yly.crm.entity.CustomerManager;
import com.yly.crm.mapper.CustomerManagerMapper;
import com.yly.crm.service.CustomerManagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author MoShuYing
* @date 2021/05/20
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerManagerServiceImpl extends ServiceImpl<CustomerManagerMapper, CustomerManager> implements CustomerManagerService {

    @Resource
    private CustomerManagerMapper customerManagerMapper;


    public IPage<CustomerManagerList> listAllWithDictionary(IPage<CustomerManagerList> customerManagerListIPage,String keyword, Integer status) {
        return customerManagerMapper.listAllWithDictionary(customerManagerListIPage, keyword,status);
    }

    public IPage<Analysis> queryAnalysis(IPage<Analysis> qpage,AnalysisQuery analysisQuery) {
        if(analysisQuery.getName()==null){
            analysisQuery.setName("");
        }
        return customerManagerMapper.queryAnalysis(qpage, analysisQuery);
    }
}
