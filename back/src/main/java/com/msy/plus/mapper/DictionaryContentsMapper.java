package com.msy.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msy.plus.entity.DictionaryContents;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DictionaryContentsMapper extends BaseMapper<DictionaryContents> {
    List<DictionaryContents> listWithKeyword(String keyword);
}