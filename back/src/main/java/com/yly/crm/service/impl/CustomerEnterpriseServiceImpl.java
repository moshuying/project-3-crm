package com.yly.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yly.crm.entity.CustomerEnterprise;
import com.yly.crm.service.CustomerEnterpriseService;
import com.yly.crm.mapper.CustomerEnterpriseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【customer_enterprise】的数据库操作Service实现
* @createDate 2022-10-09 17:35:46
*/
@Service
public class CustomerEnterpriseServiceImpl extends ServiceImpl<CustomerEnterpriseMapper, CustomerEnterprise>
    implements CustomerEnterpriseService{

    @Autowired
    private CustomerEnterpriseMapper  customerEnterpriseMapper;

    @Override
    public IPage<CustomerEnterprise> listByKeyword(IPage<CustomerEnterprise> cepage, String keyword, Integer status) {

        return null;
    }
}




