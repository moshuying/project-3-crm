package com.msy.plus.service.impl;

import com.msy.plus.mapper.DictionaryContentsMapper;
import com.msy.plus.entity.DictionaryContents;
import com.msy.plus.service.DictionaryContentsService;
import com.msy.plus.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author MoShuYing
* @date 2021/05/18
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class DictionaryContentsServiceImpl extends AbstractService<DictionaryContents> implements DictionaryContentsService {
    @Resource
    private DictionaryContentsMapper dictionaryContentsMapper;

}
