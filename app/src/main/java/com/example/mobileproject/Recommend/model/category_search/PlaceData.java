package com.example.mobileproject.Recommend.model.category_search;

public class PlaceData {
    //TODO 필드 목록 수정
    private String name;
    private Integer count_like;
    private Integer count_bookmark;
    private String addressName;

    public PlaceData() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public PlaceData(String name, Integer count_like, Integer count_bookmark, String addressName) {
        this.name = name;
        this.count_like = count_like;
        this.count_bookmark = count_bookmark;
        this.addressName = addressName;
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

    public Integer getCount_bookmark() {
        return count_bookmark;
    }

    public void setCount_bookmark(Integer count_bookmark) {
        this.count_bookmark = count_bookmark;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
}
