package com.yly.crm.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName customer_enterprise
 */
@TableName(value ="customer_enterprise")
@Data
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
     * 企业地址
     */
    private String entAddr;

    /**
     * 企业验证状态：业务人员验证
     */
    private Integer entVerify;

    /**
     * 企业与厂家合同类型：1、直接客户、2、渠道代理、3、供应商、4、战略合作、5、其他合作形式
     */
    private Integer entCooperationType;

    /**
     * 企业官网
     */
    private String entWebsite;

    /**
     * 该企业属于哪个销售人员负责id
     */
    private Integer entBelongBizerId;

    /**
     * 该企业属于哪个销售人员负责姓名
     */
    private String entBelongBizerName;

    /**
     * 企业工商信息截图
     */
    private String entInfoImg;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 更新人名字
     */
    private String updateUser;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}