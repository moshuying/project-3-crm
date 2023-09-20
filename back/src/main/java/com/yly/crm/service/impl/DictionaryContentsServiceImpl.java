package com.yly.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yly.crm.mapper.DictionaryContentsMapper;
import com.yly.crm.entity.DictionaryContents;
import com.yly.crm.service.DictionaryContentsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author MoShuYing
* @date 2021/05/18
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class DictionaryContentsServiceImpl extends ServiceImpl<DictionaryContentsMapper, DictionaryContents> implements DictionaryContentsService {
    @Resource
    private DictionaryContentsMapper dictionaryContentsMapper;

    @Override
    public IPage<DictionaryContents>  listWithKeyword(IPage<DictionaryContents> page,String keyword) {
        return dictionaryContentsMapper.listWithKeyword(page, keyword);
    }
}
