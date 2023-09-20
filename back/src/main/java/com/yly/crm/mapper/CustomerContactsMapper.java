package com.yly.crm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseMapper;
import com.yly.crm.dto.AnalysisQuery;
import com.yly.crm.entity.Analysis;
import com.yly.crm.entity.CustomerContacts;
import com.yly.crm.dto.CustomerContactsDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


@Component
public interface CustomerContactsMapper extends MPJBaseMapper<CustomerContacts> {

    IPage<CustomerContactsDTO>  listAllWithDictionary(IPage<CustomerContactsDTO> customerManagerListIPage, String keyword, Integer status, Integer ceId);

    CustomerContacts getDetailById(Object id);

    IPage<Analysis>  queryAnalysis(IPage<Analysis> qpage , @Param("aq") AnalysisQuery analysisQuery);

}