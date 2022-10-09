package com.yly.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yly.crm.entity.DictionaryContents;
import com.yly.crm.entity.DictionaryDetails;

/**
* @author MoShuYing
* @date 2021/05/18
*/
public interface DictionaryDetailsService extends IService<DictionaryDetails> {
    IPage<DictionaryContents> listWithKeyword(IPage<DictionaryContents> page, int id, String keyword);
}
