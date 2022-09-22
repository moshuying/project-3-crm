package com.msy.plus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.msy.plus.entity.DictionaryContents;
import com.msy.plus.entity.DictionaryDetails;

import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/18
*/
public interface DictionaryDetailsService extends IService<DictionaryDetails> {
    IPage<DictionaryContents> listWithKeyword(IPage<DictionaryContents> page, int id, String keyword);
}
