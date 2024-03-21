package com.example.mobileproject.basefragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mobileproject.Home.activity.AlarmActivity;
import com.example.mobileproject.Home.activity.ShareSearchActivity;
import com.example.mobileproject.R;

//공유 프래그먼트 액션바 클래스
public class ShareBaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 프래그먼트에서 액션바 메뉴를 사용할 수 있도록 설정
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle("공유");
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.share_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search_share:
                // 검색 동작 처리
                Intent searchIntent = new Intent(getActivity(), ShareSearchActivity.class);
                startActivity(searchIntent);
                return true;
            case R.id.action_alarm:
                // 알람 동작 처리
                Intent alarmIntent = new Intent(getActivity(), AlarmActivity.class);
                startActivity(alarmIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


