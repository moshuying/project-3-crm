package com.yly.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yly.crm.dto.AnalysisQuery;
import com.yly.crm.entity.Analysis;
import com.yly.crm.entity.CustomerManager;
import com.yly.crm.dto.CustomerManagerList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


@Component
public interface CustomerManagerMapper extends BaseMapper<CustomerManager> {

    IPage<CustomerManagerList>  listAllWithDictionary(IPage<CustomerManagerList> customerManagerListIPage, String keyword, Integer status);

    CustomerManager getDetailById(Object id);

    IPage<Analysis>  queryAnalysis(IPage<Analysis> qpage , @Param("aq") AnalysisQuery analysisQuery);
}