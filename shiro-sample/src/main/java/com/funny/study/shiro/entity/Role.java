package com.funny.study.shiro.entity;

import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author funnystack
 * @since 2018-03-20
 */
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private Date createDate;
    @TableField(update = "new()")
    private Date modifiedDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "Role{" +
        ", id=" + id +
        ", name=" + name +
        ", createDate=" + createDate +
        ", modifiedDate=" + modifiedDate +
        "}";
    }
}
