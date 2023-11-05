package com.yly.crm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 作废，测试用的表
 * @TableName baobei
 */
@TableName(value ="baobei")
@Data
public class BaobeiDO implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 
     */
    private Integer biz_id;

    /**
     * 渠道企业id
     */
    private Integer cent_id;

    /**
     * 渠道企业名称
     */
    private String cent_name;

    /**
     * 保护到期时间，每次延长3个月
     */
    private Date protected_period;

    /**
     * 渠道销售id
     */
    private Integer cuser_id;

    /**
     * 描述信息
     */
    private String project_desc;

    /**
     * 
     */
    private Date create_at;

    /**
     * 报备人
     */
    private Integer create_user_id;

    /**
     * 报备状态
     */
    private Object success;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}