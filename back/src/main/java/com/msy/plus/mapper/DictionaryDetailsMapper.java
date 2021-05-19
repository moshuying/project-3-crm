package com.msy.plus.mapper;

import com.msy.plus.core.mapper.MyMapper;
import com.msy.plus.entity.DictionaryContents;
import com.msy.plus.entity.DictionaryDetails;

import java.util.List;

public interface DictionaryDetailsMapper extends MyMapper<DictionaryDetails> {
    List<DictionaryContents> listWithKeyword(int id,String keyword);

}