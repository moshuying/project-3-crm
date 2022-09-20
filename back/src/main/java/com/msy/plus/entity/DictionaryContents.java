package com.msy.plus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @TableName dictionary_contents
 */
@TableName(value ="dictionary_contents")
@Data
public class DictionaryContents implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典目录编号
     */
    @TableField(value = "sn")
    private String sn;

    /**
     * 字典目录名称
     */
    @TableField(value = "title")
    private String title;

    /**
     * 字典目录简介
     */
    @TableField(value = "intro")
    private String intro;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}