package com.msy.plus.service.impl;

import com.msy.plus.entity.DictionaryContents;
import com.msy.plus.mapper.DictionaryDetailsMapper;
import com.msy.plus.entity.DictionaryDetails;
import com.msy.plus.service.DictionaryDetailsService;
import com.msy.plus.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/18
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class DictionaryDetailsServiceImpl extends AbstractService<DictionaryDetails> implements DictionaryDetailsService {
    @Resource
    private DictionaryDetailsMapper dictionaryDetailsMapper;

    @Override
    public List<DictionaryContents> listWithKeyword(int id,String keyword) {
        return dictionaryDetailsMapper.listWithKeyword(id,keyword);
    }
}
