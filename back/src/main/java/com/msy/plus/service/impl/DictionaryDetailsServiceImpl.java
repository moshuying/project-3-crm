package com.msy.plus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msy.plus.entity.DictionaryContents;
import com.msy.plus.mapper.DictionaryDetailsMapper;
import com.msy.plus.entity.DictionaryDetails;
import com.msy.plus.service.DictionaryDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/18
 * mp update
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class DictionaryDetailsServiceImpl extends ServiceImpl<DictionaryDetailsMapper,DictionaryDetails> implements DictionaryDetailsService {
    @Resource
    private DictionaryDetailsMapper dictionaryDetailsMapper;

    public IPage<DictionaryContents> listWithKeyword(IPage<DictionaryContents> page, int id, String keyword) {
        return dictionaryDetailsMapper.listWithKeyword(page, id,keyword);
    }
}
