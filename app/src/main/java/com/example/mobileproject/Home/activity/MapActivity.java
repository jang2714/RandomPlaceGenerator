package com.example.mobileproject.Home.activity;

import android.os.Bundle;

import com.example.mobileproject.R;
import com.example.mobileproject.baseactivity.BaseActivity;

public class MapActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        setupActionBar("지도", true); //액션바 타이틀 설정

    }
}