package com.msy.plus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.msy.plus.dto.AnalysisQuery;
import com.msy.plus.dto.CustomerManagerList;
import com.msy.plus.entity.Analysis;
import com.msy.plus.entity.CustomerManager;

import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/20
*/
public interface CustomerManagerService extends IService<CustomerManager> {

    IPage<CustomerManagerList>  listAllWithDictionary(IPage<CustomerManagerList> customerManagerListIPageString, String keyword, Integer status);

    IPage<Analysis> queryAnalysis(IPage<Analysis> qpage, AnalysisQuery analysisQuery);
}
