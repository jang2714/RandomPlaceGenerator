package com.example.mobileproject.Home.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.Home.adapter.AlarmAdapter;
import com.example.mobileproject.Home.data.AlarmData;
import com.example.mobileproject.R;
import com.example.mobileproject.baseactivity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class AlarmActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private AlarmAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //액션바 타이틀, 뒤로가기
        setupActionBar("공지사항", true);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //String[] strArray = {"양지은", "임경빈", "유장호", "선지성", "김우빈", "손석구", "아이유", "봉미선", "신짱구"};
        List<AlarmData> alarmData = new ArrayList<>();
        // 테스트 데이터 생성
        for(int i = 0; i < 1; i++) {
            alarmData.add(new AlarmData("업데이트", "우리어디가 앱이 베타버전으로 배포 되었습니다"));
        }

        adapter = new AlarmAdapter(alarmData);
        recyclerView.setAdapter(adapter);
    }

}