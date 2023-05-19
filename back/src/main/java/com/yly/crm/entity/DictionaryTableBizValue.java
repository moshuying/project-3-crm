package com.yly.crm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName dictionary_table_biz_value
 */
@TableName(value ="dictionary_table_biz_value")
@Data
public class DictionaryTableBizValue implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 业务名称
     */
    private String bizTableName;

    /**
     * 
     */
    private Integer bizDicId;

    /**
     * 业务字段名称
     */
    private String bizDicTitle;

    /**
     * 业务字段值
     */
    private String bizDicValueId;

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
        DictionaryTableBizValue other = (DictionaryTableBizValue) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBizTableName() == null ? other.getBizTableName() == null : this.getBizTableName().equals(other.getBizTableName()))
            && (this.getBizDicId() == null ? other.getBizDicId() == null : this.getBizDicId().equals(other.getBizDicId()))
            && (this.getBizDicTitle() == null ? other.getBizDicTitle() == null : this.getBizDicTitle().equals(other.getBizDicTitle()))
            && (this.getBizDicValueId() == null ? other.getBizDicValueId() == null : this.getBizDicValueId().equals(other.getBizDicValueId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBizTableName() == null) ? 0 : getBizTableName().hashCode());
        result = prime * result + ((getBizDicId() == null) ? 0 : getBizDicId().hashCode());
        result = prime * result + ((getBizDicTitle() == null) ? 0 : getBizDicTitle().hashCode());
        result = prime * result + ((getBizDicValueId() == null) ? 0 : getBizDicValueId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bizTableName=").append(bizTableName);
        sb.append(", bizDicId=").append(bizDicId);
        sb.append(", bizDicTitle=").append(bizDicTitle);
        sb.append(", bizDicValueId=").append(bizDicValueId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}