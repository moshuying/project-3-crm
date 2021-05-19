package com.msy.plus.service;

import com.msy.plus.entity.DictionaryContents;
import com.msy.plus.entity.DictionaryDetails;
import com.msy.plus.core.service.Service;

import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/18
*/
public interface DictionaryDetailsService extends Service<DictionaryDetails> {
    List<DictionaryContents> listWithKeyword(int id,String keyword);
}
