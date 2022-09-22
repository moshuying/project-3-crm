package com.msy.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.msy.plus.entity.DictionaryContents;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DictionaryContentsMapper extends BaseMapper<DictionaryContents> {
    IPage<DictionaryContents> listWithKeyword(IPage<DictionaryContents> page, String keyword);
}