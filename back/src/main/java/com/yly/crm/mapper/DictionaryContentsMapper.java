package com.yly.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yly.crm.entity.DictionaryContents;
import org.springframework.stereotype.Component;

@Component
public interface DictionaryContentsMapper extends BaseMapper<DictionaryContents> {
    IPage<DictionaryContents> listWithKeyword(IPage<DictionaryContents> page, String keyword);
}