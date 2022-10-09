package com.yly.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yly.crm.entity.DictionaryContents;

/**
* @author MoShuYing
* @date 2021/05/18
*/
public interface DictionaryContentsService extends IService<DictionaryContents> {
    IPage<DictionaryContents> listWithKeyword(IPage<DictionaryContents> page,String keyword);
}
