package com.example.mobileproject.Home.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.Home.adapter.BestAdapter;
import com.example.mobileproject.baseactivity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class BestPlaceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best);

        //액션바 타이틀, 뒤로가기
        setupActionBar("이번달 인기 장소", true);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_best);
        recyclerView.setHasFixedSize(true); // if your recyclerview's size is fixed, set this to true for performance improvement

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<String> myDataset = new ArrayList<>();

        //TODO 장소, 코스일 때 제목 조건문
        for (int i = 0; i < 10; i++) {
            myDataset.add("장소 " + (i+1));
        }

        BestAdapter mAdapter = new BestAdapter(myDataset, BestAdapter.VIEW_TYPE_BEST);
        recyclerView.setAdapter(mAdapter);

    }
}