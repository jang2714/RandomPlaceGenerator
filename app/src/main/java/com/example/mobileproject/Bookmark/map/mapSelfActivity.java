package com.example.mobileproject.Bookmark.map;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.R;
import com.example.mobileproject.baseactivity.BaseActivity;

public class mapSelfActivity extends BaseActivity {

    mapSelfFragment mapSelfFragment;
    ImageButton map_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_self);

        mapSelfFragment = new mapSelfFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, mapSelfFragment)
                .commit();

        map_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
}