package com.msy.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.msy.plus.dto.AnalysisQuery;
import com.msy.plus.entity.Analysis;
import com.msy.plus.entity.CustomerManager;
import com.msy.plus.dto.CustomerManagerList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface CustomerManagerMapper extends BaseMapper<CustomerManager> {

    IPage<CustomerManagerList>  listAllWithDictionary(IPage<CustomerManagerList> customerManagerListIPage,String keyword, Integer status);

    CustomerManager getDetailById(Object id);

    IPage<Analysis>  queryAnalysis(IPage<Analysis> qpage , @Param("aq") AnalysisQuery analysisQuery);
}