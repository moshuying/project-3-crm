package com.yly.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

/**
 * 字典跟各个业务表的绑定表
 * @TableName dictionary_table_biz_ref
 */
@TableName(value ="dictionary_table_biz_ref", autoResultMap = true)
@Data
public class DictionaryTableBizRef implements Serializable {
    /**
     * 字典与业务绑定表
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 业务设置表：1、企业表，2、联系人表，3、员工表、4跟进表、5、业务表、6其他
     */
    private String bizName;

    /**
     * 数据库表名称
     */
    private String tableName;

    /**
     * 主要是字典的名字与id的数组{dic_id:2, dic_name:type}多个数字组成
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<DictionaryContents> schemaJson;

    /**
     * 是否为系统默认
     */
    private Integer isSys;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}