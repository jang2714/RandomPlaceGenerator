package com.example.mobileproject.baseactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.Bookmark.map.mapActivity;
import com.example.mobileproject.Home.activity.MyPageActivity;

//TODO 코스 상세정보 Activity extends 이걸로 바꾸기
//코스 상세정보
public class DetailCourseBaseActivity extends AppCompatActivity {

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

