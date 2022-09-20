package com.msy.plus.service;

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

    List<CustomerManagerList> listAllWithDictionary(String keyword, Integer status);

    List<Analysis> queryAnalysis(AnalysisQuery analysisQuery);
}
