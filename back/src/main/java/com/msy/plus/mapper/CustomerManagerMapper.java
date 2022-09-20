package com.msy.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msy.plus.dto.AnalysisQuery;
import com.msy.plus.entity.Analysis;
import com.msy.plus.entity.CustomerManager;
import com.msy.plus.dto.CustomerManagerList;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface CustomerManagerMapper extends BaseMapper<CustomerManager> {
    List<CustomerManagerList> listAllWithDictionary(String keyword,Integer status);
    CustomerManager getDetailById(Object id);
    List<Analysis> queryAnalysis(AnalysisQuery analysisQuery);
}