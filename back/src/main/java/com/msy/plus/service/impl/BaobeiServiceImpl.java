package com.msy.plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msy.plus.entity.Baobei;
import com.msy.plus.mapper.BaobeiMapper;
import com.msy.plus.query.BaobeiQuery;
import com.msy.plus.service.BaobeiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class BaobeiServiceImpl extends ServiceImpl<BaobeiMapper, Baobei>
        implements BaobeiService {

    @Resource private BaobeiMapper baobeiMapper;

    public List<Baobei> listAllAndSearch(String keyword, Date startTime, Date endTime, Integer type) {
        BaobeiQuery bbquery  = BaobeiQuery.builder().customerNameKeyword(keyword).startTime(startTime).build();

        List<Baobei> list = baobeiMapper.listAllAndSearch(bbquery);

        System.out.println(list.toString());
        return list;
    }
}
