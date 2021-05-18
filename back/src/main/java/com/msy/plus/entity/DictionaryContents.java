package com.msy.plus.entity;

import javax.persistence.*;

@Table(name = "dictionary_contents")
public class DictionaryContents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 字典目录编号
     */
    private String sn;

    /**
     * 字典目录名称
     */
    private String title;

    /**
     * 字典目录简介
     */
    private String intro;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取字典目录编号
     *
     * @return sn - 字典目录编号
     */
    public String getSn() {
        return sn;
    }

    /**
     * 设置字典目录编号
     *
     * @param sn 字典目录编号
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * 获取字典目录名称
     *
     * @return title - 字典目录名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置字典目录名称
     *
     * @param title 字典目录名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取字典目录简介
     *
     * @return intro - 字典目录简介
     */
    public String getIntro() {
        return intro;
    }

    /**
     * 设置字典目录简介
     *
     * @param intro 字典目录简介
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }
}