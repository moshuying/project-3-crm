package com.yly.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yly.crm.entity.Bizs;
import com.yly.crm.service.BizsService;
import com.yly.crm.mapper.BizsMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【bizs(商机管理表，管理商机报备，内外报备，提醒跟进等)】的数据库操作Service实现
* @createDate 2022-10-10 19:39:47
*/
@Service
public class BizsServiceImpl extends ServiceImpl<BizsMapper, Bizs>
    implements BizsService{

}




