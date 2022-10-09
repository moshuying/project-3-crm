package com.yly.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yly.crm.entity.DictionaryContents;
import com.yly.crm.entity.DictionaryDetails;
import org.springframework.stereotype.Component;

@Component
public interface DictionaryDetailsMapper extends BaseMapper<DictionaryDetails> {
    IPage<DictionaryContents> listWithKeyword(IPage<DictionaryContents> page, int id, String keyword);

}