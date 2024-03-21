package com.example.mobileproject.baseactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.Bookmark.BookmarkFragment;
import com.example.mobileproject.Bookmark.map.mapActivity;
import com.example.mobileproject.Bookmark.map.mapNavigationActivity;
import com.example.mobileproject.Home.activity.MainActivity;
import com.example.mobileproject.Login.activity.LoginActivity;
import com.example.mobileproject.R;

public class RecommendBaseActivity extends AppCompatActivity {
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

    // x 클릭 시 홈 프래그먼트로 이동
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_close:
//                Intent intent = new Intent(this, MainActivity.class);

//                    Intent intent = new Intent(this, MainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 액티비티 스택을 지우고 새로운 액티비티를 시작
//                    intent.putExtra("LOAD_HOME_FRAGMENT", true); // 홈 프래그먼트를 로드하는 플래그
//                    startActivity(intent);
                    finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
