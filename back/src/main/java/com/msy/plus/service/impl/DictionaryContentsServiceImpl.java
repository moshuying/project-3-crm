package com.msy.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msy.plus.mapper.DictionaryContentsMapper;
import com.msy.plus.entity.DictionaryContents;
import com.msy.plus.service.DictionaryContentsService;
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
public class DictionaryContentsServiceImpl extends ServiceImpl<DictionaryContentsMapper, DictionaryContents> implements DictionaryContentsService {
    @Resource
    private DictionaryContentsMapper dictionaryContentsMapper;

    @Override
    public List<DictionaryContents> listWithKeyword(String keyword) {
        return dictionaryContentsMapper.listWithKeyword(keyword);
    }
}
