package com.msy.plus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.msy.plus.entity.DictionaryContents;

import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/18
*/
public interface DictionaryContentsService extends IService<DictionaryContents> {
    List<DictionaryContents>  listWithKeyword(String keyword);
}
