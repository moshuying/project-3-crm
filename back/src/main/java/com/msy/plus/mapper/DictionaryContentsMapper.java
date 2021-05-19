package com.msy.plus.mapper;

import com.msy.plus.core.mapper.MyMapper;
import com.msy.plus.entity.DictionaryContents;

import java.util.List;

public interface DictionaryContentsMapper extends MyMapper<DictionaryContents> {
    List<DictionaryContents> listWithKeyword(String keyword);
}