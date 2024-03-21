package com.example.mobileproject.Home.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.R;
import com.example.mobileproject.baseactivity.BaseActivity;

public class PlaceInfoActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info);

        //액션바 타이틀, 뒤로가기
        setupActionBar("장소 상세 정보", true);

        TextView placeUrlText = findViewById(R.id.placeUrl);

        // 인텐트에서 전달된 데이터 받아오기
        Intent intent = getIntent();
        String placeName = intent.getStringExtra("placeName"); // placeName 데이터 받아오기

        // TODO: placeName을 이용하여 필요한 작업을 수행합니다.

        placeUrlText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = placeUrlText.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}

//        클릭할 View.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(현재 context 추가, PlaceInfoActivity.class);
//                startActivity(intent);
//            }
//        });

