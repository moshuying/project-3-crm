package com.yly.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yly.crm.entity.Products;
import com.yly.crm.service.ProductsService;
import com.yly.crm.mapper.ProductsMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【products】的数据库操作Service实现
* @createDate 2022-10-11 09:56:57
*/
@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products>
    implements ProductsService{

}




