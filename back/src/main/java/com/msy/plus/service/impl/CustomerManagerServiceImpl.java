package com.msy.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msy.plus.dto.AnalysisQuery;
import com.msy.plus.dto.CustomerManagerList;
import com.msy.plus.entity.Analysis;
import com.msy.plus.entity.CustomerManager;
import com.msy.plus.mapper.CustomerManagerMapper;
import com.msy.plus.service.CustomerManagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/20
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerManagerServiceImpl extends ServiceImpl<CustomerManagerMapper, CustomerManager> implements CustomerManagerService {

    @Resource
    private CustomerManagerMapper customerManagerMapper;


    public List<CustomerManagerList> listAllWithDictionary(String keyword, Integer status) {
        return customerManagerMapper.listAllWithDictionary(keyword,status);
    }

    public List<Analysis> queryAnalysis(AnalysisQuery analysisQuery) {
        if(analysisQuery.getName()==null){
            analysisQuery.setName("");
        }
        return customerManagerMapper.queryAnalysis(analysisQuery);
    }
}
