package com.msy.plus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.msy.plus.entity.Baobei;

import java.util.Date;
import java.util.List;

/**
* @author jiuyuehe
* @date 2022/08/29
*/
public interface BaobeiService extends IService<Baobei> {
    List<Baobei> listAllAndSearch(String keyword, Date startTime, Date endTime, Integer type);

}
