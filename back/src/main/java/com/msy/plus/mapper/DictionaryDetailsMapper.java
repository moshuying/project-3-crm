package com.msy.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msy.plus.entity.DictionaryContents;
import com.msy.plus.entity.DictionaryDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DictionaryDetailsMapper extends BaseMapper<DictionaryDetails> {
    List<DictionaryContents> listWithKeyword(int id,String keyword);

}