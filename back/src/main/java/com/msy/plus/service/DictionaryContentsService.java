package com.msy.plus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.msy.plus.entity.DictionaryContents;

import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/18
*/
public interface DictionaryContentsService extends IService<DictionaryContents> {
    IPage<DictionaryContents> listWithKeyword(IPage<DictionaryContents> page,String keyword);
}
