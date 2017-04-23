package com.model;

import java.util.List;

/**
 * Created by 13410 on 2017/4/23.
 */

public class Category {
    private Integer id;
    private String categoryName;
    private List<NotePad> notePads=null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<NotePad> getNotePads() {
        return notePads;
    }

    public void setNotePads(List<NotePad> notePads) {
        this.notePads = notePads;
    }
}
