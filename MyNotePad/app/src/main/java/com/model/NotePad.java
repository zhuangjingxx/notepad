package com.model;

import java.util.Date;

/**
 * Created by 13410 on 2017/4/23.
 */

public class NotePad {

    private Integer id;
    private Date buildTime;
    private String firstLineOfContent;
    private Integer categoryId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public String getFirstLineOfContent() {
        return firstLineOfContent;
    }

    public void setFirstLineOfContent(String firstLineOfContent) {
        this.firstLineOfContent = firstLineOfContent;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
