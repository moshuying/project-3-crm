package com.yly.crm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yly.crm.entity.Baobei;
import com.yly.crm.query.BaobeiQuery;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jiuyuehe
 * @date 2022/08/27
 */
@Component
public interface BaobeiMapper extends BaseMapper<Baobei> {


  /**
   * 按条件查询账户
   *
   * @param baobeiQuery 账户查询条件
   * @return 账户
   */
  List<Baobei> listAllAndSearch(BaobeiQuery baobeiQuery);

  /**
   * 按账户名更新最后登陆时间
   *
   * @param name 账户名
   * @return 影响行数
   */
//  int updateLoginTimeByName(@Param("name") String name);
}
