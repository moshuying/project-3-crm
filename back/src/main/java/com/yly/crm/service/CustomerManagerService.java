package com.yly.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yly.crm.dto.AnalysisQuery;
import com.yly.crm.dto.CustomerManagerList;
import com.yly.crm.entity.Analysis;
import com.yly.crm.entity.CustomerManager;

/**
* @author MoShuYing
* @date 2021/05/20
*/
public interface CustomerManagerService extends IService<CustomerManager> {

    IPage<CustomerManagerList>  listAllWithDictionary(IPage<CustomerManagerList> customerManagerListIPageString, String keyword, Integer status);

    IPage<Analysis> queryAnalysis(IPage<Analysis> qpage, AnalysisQuery analysisQuery);
}
