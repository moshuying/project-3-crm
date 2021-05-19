package com.msy.plus.service;

import com.msy.plus.entity.DictionaryContents;
import com.msy.plus.core.service.Service;

import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/18
*/
public interface DictionaryContentsService extends Service<DictionaryContents> {
    List<DictionaryContents>  listWithKeyword(String keyword);
}
