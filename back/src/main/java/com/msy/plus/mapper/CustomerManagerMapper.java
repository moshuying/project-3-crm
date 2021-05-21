package com.msy.plus.mapper;

import com.msy.plus.core.mapper.MyMapper;
import com.msy.plus.entity.CustomerManager;
import com.msy.plus.dto.CustomerManagerList;

import java.util.List;

public interface CustomerManagerMapper extends MyMapper<CustomerManager> {
    List<CustomerManagerList> listAllWithDictionary(String keyword,Integer status);
    CustomerManager getDetailById(Object id);
}