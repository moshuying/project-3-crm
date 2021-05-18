package com.msy.plus.entity;

import javax.persistence.*;

@Table(name = "dictionary_details")
public class DictionaryDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 字典明细名称
     */
    private String title;

    /**
     * 字典明细序号
     */
    private Integer sequence;

    @Column(name = "parentId")
    private Integer parentid;

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
     * 获取字典明细名称
     *
     * @return title - 字典明细名称
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置字典明细名称
     *
     * @param title 字典明细名称
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取字典明细序号
     *
     * @return sequence - 字典明细序号
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置字典明细序号
     *
     * @param sequence 字典明细序号
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * @return parentId
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * @param parentid
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
}