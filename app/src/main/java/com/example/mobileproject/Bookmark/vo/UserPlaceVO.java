package com.example.mobileproject.Bookmark.vo;

import java.io.Serializable;

public class UserPlaceVO implements Serializable {

    private String placeName;
    private String addressName;
    private String categoryName;
    private String imgURL;
    private String phone;
    private String x;
    private String y;

    public UserPlaceVO(){

    }

    public UserPlaceVO(String placeName, String addressName, String categoryName, String imgURL, String phone, String x, String y) {
        this.placeName = placeName;
        this.addressName = addressName;
        this.categoryName = categoryName;
        this.imgURL = imgURL;
        this.phone = phone;
        this.x = x;
        this.y = y;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImgURL() {
        return "https:" + imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
