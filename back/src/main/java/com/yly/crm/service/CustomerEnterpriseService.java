package com.yly.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yly.crm.entity.CustomerEnterprise;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【customer_enterprise】的数据库操作Service
* @createDate 2022-10-09 17:35:46
*/
public interface CustomerEnterpriseService extends IService<CustomerEnterprise> {

    IPage<CustomerEnterprise> listByKeyword (IPage<CustomerEnterprise> cepage, String keyword, Integer status);

}
