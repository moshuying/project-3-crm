package com.yly.crm.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yly.crm.entity.DictionaryContents;
import com.yly.crm.mapper.DictionaryDetailsMapper;
import com.yly.crm.entity.DictionaryDetails;
import com.yly.crm.service.DictionaryDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author MoShuYing
* @date 2021/05/18
 * mp update
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class DictionaryDetailsServiceImpl extends ServiceImpl<DictionaryDetailsMapper, DictionaryDetails> implements DictionaryDetailsService {
    @Resource
    private DictionaryDetailsMapper dictionaryDetailsMapper;

    public IPage<DictionaryContents> listWithKeyword(IPage<DictionaryContents> page, int id, String keyword) {
        return dictionaryDetailsMapper.listWithKeyword(page, id,keyword);
    }
}
