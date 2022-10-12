package com.yly.crm.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 商机管理表，管理商机报备，内外报备，提醒跟进等
 * @TableName bizs
 */
@TableName(value ="bizs")
@Data
public class Bizs implements Serializable {
    /**
     * 
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 联系人id,微信第二次进来通过openid获取
     */
    @TableField(value = "contact_id")
    private Integer contactId;

    /**
     * 联系人名字
     */
    @TableField(value = "contact_name")
    private String contactName;

    /**
     * 联系人所属企业id，关联后才有
     */
    @TableField(value = "contact_ent_id")
    private Integer contactEntId;

    /**
     * 报备人手机号码
     */
    @TableField(value = "contact_tel")
    private String contactTel;

    /**
     * 如果报备客户存在显示
     */
    @TableField(value = "customer_ent_id")
    private Integer customerEntId;

    /**
     * 最终报备客户名称
     */
    @TableField(value = "biz_ent_name")
    private String bizEntName;

    /**
     * 客户商机描述, 根据这个配备资源
     */
    @TableField(value = "biz_desc")
    private String bizDesc;

    /**
     * 商机阶段，消息提醒判断
     */
    @TableField(value = "biz_stage")
    private String bizStage;

    /**
     * 报备产品
     */
    @TableField(value = "product_id")
    private Integer productId;

    /**
     * 冗余产品名称（冗余）
     */
    @TableField(value = "product_name")
    private String productName;

    /**
     * 产品详情描述
     */
    @TableField(value = "product_desc")
    private String productDesc;

    /**
     * 对口业务经理
     */
    @TableField(value = "employee_id")
    private Integer employeeId;

    /**
     * 业务经理姓名（冗余，用于查询）
     */
    @TableField(value = "employee_name")
    private String employeeName;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Bizs other = (Bizs) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getContactId() == null ? other.getContactId() == null : this.getContactId().equals(other.getContactId()))
            && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
            && (this.getContactEntId() == null ? other.getContactEntId() == null : this.getContactEntId().equals(other.getContactEntId()))
            && (this.getContactTel() == null ? other.getContactTel() == null : this.getContactTel().equals(other.getContactTel()))
            && (this.getCustomerEntId() == null ? other.getCustomerEntId() == null : this.getCustomerEntId().equals(other.getCustomerEntId()))
            && (this.getBizEntName() == null ? other.getBizEntName() == null : this.getBizEntName().equals(other.getBizEntName()))
            && (this.getBizDesc() == null ? other.getBizDesc() == null : this.getBizDesc().equals(other.getBizDesc()))
            && (this.getBizStage() == null ? other.getBizStage() == null : this.getBizStage().equals(other.getBizStage()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getProductDesc() == null ? other.getProductDesc() == null : this.getProductDesc().equals(other.getProductDesc()))
            && (this.getEmployeeId() == null ? other.getEmployeeId() == null : this.getEmployeeId().equals(other.getEmployeeId()))
            && (this.getEmployeeName() == null ? other.getEmployeeName() == null : this.getEmployeeName().equals(other.getEmployeeName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getContactId() == null) ? 0 : getContactId().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getContactEntId() == null) ? 0 : getContactEntId().hashCode());
        result = prime * result + ((getContactTel() == null) ? 0 : getContactTel().hashCode());
        result = prime * result + ((getCustomerEntId() == null) ? 0 : getCustomerEntId().hashCode());
        result = prime * result + ((getBizEntName() == null) ? 0 : getBizEntName().hashCode());
        result = prime * result + ((getBizDesc() == null) ? 0 : getBizDesc().hashCode());
        result = prime * result + ((getBizStage() == null) ? 0 : getBizStage().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductDesc() == null) ? 0 : getProductDesc().hashCode());
        result = prime * result + ((getEmployeeId() == null) ? 0 : getEmployeeId().hashCode());
        result = prime * result + ((getEmployeeName() == null) ? 0 : getEmployeeName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", contactId=").append(contactId);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactEntId=").append(contactEntId);
        sb.append(", contactTel=").append(contactTel);
        sb.append(", customerEntId=").append(customerEntId);
        sb.append(", bizEntName=").append(bizEntName);
        sb.append(", bizDesc=").append(bizDesc);
        sb.append(", bizStage=").append(bizStage);
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", productDesc=").append(productDesc);
        sb.append(", employeeId=").append(employeeId);
        sb.append(", employeeName=").append(employeeName);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}