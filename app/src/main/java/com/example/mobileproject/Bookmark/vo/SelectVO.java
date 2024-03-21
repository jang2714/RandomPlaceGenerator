package com.example.mobileproject.Bookmark.vo;

import java.util.ArrayList;

public class SelectVO {

    private static SelectVO instance;
    private ArrayList<UserPlaceVO> userPlaceVO;

    private SelectVO(){
        userPlaceVO = new ArrayList<>();
    }

    public static SelectVO getInstance() {
        if(instance == null){
            instance = new SelectVO();
        }
        return instance;
    }

    public ArrayList<UserPlaceVO> getUserPlaceVO() {
        return userPlaceVO;
    }

    public void setUserPlaceVO(ArrayList<UserPlaceVO> userPlaceVO) {
        this.userPlaceVO = userPlaceVO;
    }
}

//TODO.싱글톤 방식으로 값을 넘겨줌 07.06 - new