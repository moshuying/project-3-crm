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

}