package com.yly.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 字典跟各个业务表的绑定表
 * @TableName dictionary_table_biz_ref
 */
@TableName(value ="dictionary_table_biz_ref")
@Data
public class DictionaryTableBizRef implements Serializable {
    /**
     * 字典与业务绑定表
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 字典的id
     */
    private Integer dicId;

    /**
     * 业务设置表：1、企业表，2、联系人表，3、员工表、4跟进表、5、业务表、6其他
     */
    private Integer bizName;

    /**
     * 数据库表名称
     */
    private String tableName;

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
        DictionaryTableBizRef other = (DictionaryTableBizRef) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDicId() == null ? other.getDicId() == null : this.getDicId().equals(other.getDicId()))
            && (this.getBizName() == null ? other.getBizName() == null : this.getBizName().equals(other.getBizName()))
            && (this.getTableName() == null ? other.getTableName() == null : this.getTableName().equals(other.getTableName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDicId() == null) ? 0 : getDicId().hashCode());
        result = prime * result + ((getBizName() == null) ? 0 : getBizName().hashCode());
        result = prime * result + ((getTableName() == null) ? 0 : getTableName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dicId=").append(dicId);
        sb.append(", bizName=").append(bizName);
        sb.append(", tableName=").append(tableName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}