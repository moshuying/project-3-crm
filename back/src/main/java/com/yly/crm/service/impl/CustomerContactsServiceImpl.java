package com.yly.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.mysql.cj.util.StringUtils;
import com.yly.crm.dto.AnalysisQuery;
import com.yly.crm.dto.CustomerContactsDTO;
import com.yly.crm.entity.Analysis;
import com.yly.crm.entity.CustomerContacts;
import com.yly.crm.entity.CustomerEnterprise;
import com.yly.crm.mapper.CustomerContactsMapper;
import com.yly.crm.service.CustomerContactsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author MoShuYing
* @date 2021/05/20
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerContactsServiceImpl extends ServiceImpl<CustomerContactsMapper, CustomerContacts> implements CustomerContactsService {

    @Resource
    private CustomerContactsMapper customerContactsMapper;

    @Override
    public CustomerContactsDTO getDetailById(long id) {

        return customerContactsMapper.selectJoinOne(CustomerContactsDTO.class, new MPJLambdaWrapper<CustomerContacts>()
                        .selectAll(CustomerContacts.class)
                        .select(CustomerEnterprise::getEntId,CustomerEnterprise::getEntName)
                        .leftJoin(CustomerEnterprise.class, CustomerEnterprise::getEntId, CustomerContacts::getCustomerEntId)
                        .eq(CustomerContacts::getId, id)
                );
    }

    public IPage<CustomerContactsDTO> listAllWithDictionary(IPage<CustomerContactsDTO> customerManagerListIPage, String keyword, Integer status, Integer ceId) {
       // return customerContactsMapper.listAllWithDictionary(customerManagerListIPage, keyword,status,ceId);
        MPJLambdaWrapper<CustomerContacts> wrapper = new MPJLambdaWrapper<CustomerContacts>()
                .selectAll(CustomerContacts.class)
                .select(CustomerEnterprise::getEntId,CustomerEnterprise::getEntName)
                .leftJoin(CustomerEnterprise.class, CustomerEnterprise::getEntId, CustomerContacts::getCustomerEntId);

                if (!StringUtils.isNullOrEmpty(keyword)){
                    wrapper.like(CustomerContacts::getName,keyword);
                }

        return customerContactsMapper.selectJoinPage(customerManagerListIPage,CustomerContactsDTO.class,wrapper);
    }

    public IPage<Analysis> queryAnalysis(IPage<Analysis> qpage,AnalysisQuery analysisQuery) {
        if(analysisQuery.getName()==null){
            analysisQuery.setName("");
        }
        return customerContactsMapper.queryAnalysis(qpage, analysisQuery);
    }
}
