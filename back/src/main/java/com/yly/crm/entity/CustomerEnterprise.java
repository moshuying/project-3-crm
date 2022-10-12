package com.yly.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 客户企业信息表，
 * @TableName customer_enterprise
 */
@TableName(value ="customer_enterprise")
@Data
public class CustomerEnterprise implements Serializable {
    /**
     * 
     */
    @TableId(value = "ent_id", type = IdType.AUTO)
    private Integer entId;

    /**
     * 企业名称
     */
    @TableField(value = "ent_name")
    private String entName;

    /**
     * 企业地址
     */
    @TableField(value = "ent_addr")
    private String entAddr;

    /**
     * 企业验证状态：业务人员
     */
    @TableField(value = "ent_verify")
    private Integer entVerify;

    /**
     * 企业官网
     */
    @TableField(value = "ent_website")
    private String entWebsite;

    /**
     * 该企业属于哪个销售人员负责
     */
    @TableField(value = "ent_belong_bizer")
    private String entBelongBizer;

    /**
     * 企业工商信息截图
     */
    @TableField(value = "ent_info_img")
    private String entInfoImg;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 更新人名字
     */
    @TableField(value = "update_user")
    private String updateUser;

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
        CustomerEnterprise other = (CustomerEnterprise) that;
        return (this.getEntId() == null ? other.getEntId() == null : this.getEntId().equals(other.getEntId()))
            && (this.getEntName() == null ? other.getEntName() == null : this.getEntName().equals(other.getEntName()))
            && (this.getEntAddr() == null ? other.getEntAddr() == null : this.getEntAddr().equals(other.getEntAddr()))
            && (this.getEntVerify() == null ? other.getEntVerify() == null : this.getEntVerify().equals(other.getEntVerify()))
            && (this.getEntWebsite() == null ? other.getEntWebsite() == null : this.getEntWebsite().equals(other.getEntWebsite()))
            && (this.getEntBelongBizer() == null ? other.getEntBelongBizer() == null : this.getEntBelongBizer().equals(other.getEntBelongBizer()))
            && (this.getEntInfoImg() == null ? other.getEntInfoImg() == null : this.getEntInfoImg().equals(other.getEntInfoImg()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getEntId() == null) ? 0 : getEntId().hashCode());
        result = prime * result + ((getEntName() == null) ? 0 : getEntName().hashCode());
        result = prime * result + ((getEntAddr() == null) ? 0 : getEntAddr().hashCode());
        result = prime * result + ((getEntVerify() == null) ? 0 : getEntVerify().hashCode());
        result = prime * result + ((getEntWebsite() == null) ? 0 : getEntWebsite().hashCode());
        result = prime * result + ((getEntBelongBizer() == null) ? 0 : getEntBelongBizer().hashCode());
        result = prime * result + ((getEntInfoImg() == null) ? 0 : getEntInfoImg().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", entId=").append(entId);
        sb.append(", entName=").append(entName);
        sb.append(", entAddr=").append(entAddr);
        sb.append(", entVerify=").append(entVerify);
        sb.append(", entWebsite=").append(entWebsite);
        sb.append(", entBelongBizer=").append(entBelongBizer);
        sb.append(", entInfoImg=").append(entInfoImg);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}