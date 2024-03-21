package com.example.mobileproject.baseactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.Bookmark.map.mapActivity;

//코스 생성 - 북마크(장소) 선택 화면
//뒤로가기 버튼이 intent로 이루어져야 함
public class CreateCourseBaseActivity extends AppCompatActivity {

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

        Intent BackIntent = new Intent(this, mapActivity.class);
        startActivity(BackIntent);
        return true;
    }
}

