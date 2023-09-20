package com.yly.crm.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @TableName dictionary_details
 */
@TableName(value ="dictionary_details")
@Data
public class DictionaryDetails implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典明细名称
     */
    @TableField(value = "title")
    private String title;

    /**
     * 字典明细序号
     */
    @TableField(value = "sequence")
    private Integer sequence;

    /**
     *
     */
    @TableField(value = "parentId")
    private Integer parentid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}