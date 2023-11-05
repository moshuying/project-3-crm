package com.yly.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yly.crm.entity.BaobeiDO;
import com.yly.crm.mapper.BaobeiMapper;

import com.yly.crm.service.BaobeiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class BaobeiServiceImpl extends ServiceImpl<BaobeiMapper, BaobeiDO>
        implements BaobeiService {

//    @Resource private BaobeiMapper baobeiMapper;
//
//    public List<BaobeiEntity> listAllAndSearch(String keyword, Date startTime, Date endTime, Integer type) {
//        BaobeiQuery bbquery  = BaobeiQuery.builder().customerNameKeyword(keyword).startTime(startTime).build();
//
//        List<BaobeiEntity> list = baobeiMapper.listAllAndSearch(bbquery);
//
//        System.out.println(list.toString());
//        return list;
//    }
}
