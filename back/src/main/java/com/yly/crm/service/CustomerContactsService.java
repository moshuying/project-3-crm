package com.yly.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yly.crm.dto.AnalysisQuery;
import com.yly.crm.dto.CustomerContactsDTO;
import com.yly.crm.entity.Analysis;
import com.yly.crm.entity.CustomerContacts;

/**
* @author MoShuYing
* @date 2021/05/20
*/
public interface CustomerContactsService extends IService<CustomerContacts> {

    CustomerContactsDTO getDetailById(long id);

    IPage<CustomerContactsDTO>  listAllWithDictionary(IPage<CustomerContactsDTO> customerManagerListIPageString, String keyword, Integer status, Integer ceId);

    IPage<Analysis> queryAnalysis(IPage<Analysis> qpage, AnalysisQuery analysisQuery);
}
