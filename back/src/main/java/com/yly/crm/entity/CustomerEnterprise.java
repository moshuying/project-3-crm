package com.yly.crm.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @TableName customer_enterprise
 */
@TableName(value ="customer_enterprise")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEnterprise implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer entId;

    /**
     * 企业名称
     */
    private String entName;

    /**
     * 企业统一信用代码
     */
    private String entSn;

    /**
     * 企业法人
     */
    private String entBoss;

    /**
     * 企业地址
     */
    private String entAddr;

    /**
     * 企业电话
     */
    private String entTel;

    /**
     * 企业邮箱
     */
    private String entEmail;

    /**
     * 企业官网
     */
    private String entWebsite;

    /**
     * 企业简单描述，来自爱企查
     */
    private String entDesc;

    /**
     * 企业与厂家合同类型：1、直接客户、2、渠道代理、3、供应商、4、战略合作、5、其他合作形式
     */
    private Integer entCooperationType;

    /**
     * 该企业属于哪个销售人员负责
     */
    private Integer entBelongBizerId;

    /**
     * 营销人员姓名
     */
    private String entBelongBizerName;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 更新人名字
     */
    private String updateUser;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}