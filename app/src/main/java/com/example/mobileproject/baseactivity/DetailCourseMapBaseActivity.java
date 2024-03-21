package com.example.mobileproject.baseactivity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

//TODO 코스 상세정보 Activity extends 이걸로 바꾸기
//코스 상세정보 + 지도 아이콘 클릭 시
public class DetailCourseMapBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // 액션바 설정
    protected void setupActionBar(String title, boolean displayHomeAsUpEnabled) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
        }
    }
    //뒤로가기
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

