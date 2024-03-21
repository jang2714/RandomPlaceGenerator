package com.example.mobileproject.Bookmark.place;

public class BookmarkPlaceData {
    private String placeName;
    private String addressName;
    private String categoryName;
    //TODO 이미지변수 일단은 주석처리 + 기본 생성자 추가 + 변수명 변경
    private String imgURL;

    public BookmarkPlaceData(){

    }

    public BookmarkPlaceData(String placeName, String addressName, String categoryName , String imgURL) {
        this.placeName = placeName;
        this.addressName = addressName;
        this.categoryName = categoryName;
        this.imgURL = imgURL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL =  "https:" + imgURL;
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
    //public int getPlaceImageVIew() {
    //return placeImageVIew;
    //}

    //public void setPlaceImageVIew(int placeImageVIew) {
    //this.placeImageVIew = placeImageVIew;
    //}



}
