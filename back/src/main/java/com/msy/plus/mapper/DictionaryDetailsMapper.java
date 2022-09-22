package com.msy.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.msy.plus.entity.DictionaryContents;
import com.msy.plus.entity.DictionaryDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DictionaryDetailsMapper extends BaseMapper<DictionaryDetails> {
    IPage<DictionaryContents> listWithKeyword(IPage<DictionaryContents> page,int id, String keyword);

}