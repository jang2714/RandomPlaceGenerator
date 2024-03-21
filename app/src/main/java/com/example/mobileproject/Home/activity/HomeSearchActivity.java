package com.example.mobileproject.Home.activity;

import android.os.Bundle;

import com.example.mobileproject.R;
import com.example.mobileproject.baseactivity.BaseActivity;

public class HomeSearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_home);

        setupActionBar("장소 검색", true); //액션바 타이틀 설정

    }
}