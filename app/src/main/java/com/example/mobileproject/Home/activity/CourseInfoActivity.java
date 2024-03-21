package com.example.mobileproject.Home.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mobileproject.R;
import com.example.mobileproject.baseactivity.BaseActivity;
import com.example.mobileproject.baseactivity.RecommendBaseActivity;

public class CourseInfoActivity extends RecommendBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);

        //액션바 타이틀, 뒤로가기
        setupActionBar("코스 상세 정보", true);

        TextView placeUrlText = findViewById(R.id.placeUrl);

        ImageButton button = findViewById(R.id.courseNext);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CourseInfoActivity.this, CourseInfoActivity2.class));
            }
        });

        placeUrlText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = placeUrlText.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.close_menu, menu); // your_menu.xml 이 메뉴 리소스의 이름
        return true;
    }
}



//button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, CourseInfoActivity.class);
//                startActivity(intent);
//            }
//        });