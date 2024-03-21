package com.example.mobileproject.Share.data;

import com.google.firebase.Timestamp;

public class CourseList {

    private String name;
    private Integer count_like;
    private Integer count_see;
    private Integer count_bookmark;
    private Timestamp date;

    public CourseList() {

    }

    public CourseList(String name, Integer count_like, Integer count_see, Integer count_bookmark, Timestamp date) {
        this.name = name;
        this.count_like = count_like;
        this.count_see = count_see;
        this.count_bookmark = count_bookmark;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount_like() {
        return count_like;
    }

    public void setCount_like(Integer count_like) {
        this.count_like = count_like;
    }

    public Integer getCount_see() {
        return count_see;
    }

    public void setCount_see(Integer count_see) {
        this.count_see = count_see;
    }

    public Integer getCount_bookmark() {
        return count_bookmark;
    }

    public void setCount_bookmark(Integer count_bookmark) {
        this.count_bookmark = count_bookmark;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
