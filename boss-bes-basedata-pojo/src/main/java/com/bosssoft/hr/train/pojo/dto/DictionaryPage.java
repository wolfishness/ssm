package com.bosssoft.hr.train.pojo.dto;

/**
 * 分页查询实体类
 * @author lujinshan
 * @date 2019/7/28 18:18
 */
public class DictionaryPage {
    /**
     *字典名
     */
    private String name;
    /**
     *  字典类型
     */
    private String category;
    /**
     *状态位
     */
    private String status;
    /**
     * 页数
     */
    private int index;

    public DictionaryPage(){

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "DictionaryPage{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", status=" + status +
                ", index=" + index +
                '}';
    }
}
