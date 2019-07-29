package com.bosssoft.hr.train.pojo.entity;

import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Dictionary实体
 * @author lujinshan
 * @date 2019.07.24
 * @version 1.0
 */
@Table(name = "t_dictionary")
public class Dictionary {
    /**
     *  数据字典ID
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long categoryId;
    /**
     *字典名
     */
    private String name;
    /**
     *  字典类型
     */
    private String category;
    /**
     *字典值
     */
    private String value;
    /**
     *备注
     */
    private String remark;
    /**
     *状态位
     */
    private Integer status;
    /**
     *机构ID
     */
    private  Long orgId;
    /**
     *创建人
     */
    private Long createdBy;
    /**
     *创建时间
     */
    private Timestamp createdTime;
    /**
     *修改人
     */
    private Long updatedBy;
    /**
     *修改时间
     */
    private Timestamp updatedTime;
    /**
     *版本
     */
    private Long version;

    public Dictionary(){

    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", orgId=" + orgId +
                ", createdBy=" + createdBy +
                ", createdTime=" + createdTime +
                ", updatedBy=" + updatedBy +
                ", updatedTime=" + updatedTime +
                ", version=" + version +
                '}';
    }
}

