package com.example.mobileproject.Recommend.fragment;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.mobileproject.R;
import com.example.mobileproject.Recommend.activitiy.RecommendActivity2;
import com.example.mobileproject.Recommend.activitiy.RecommendActivity3;

public class GyeongbukFragment extends Fragment implements View.OnClickListener{

    int[] buttonIds = {
            R.id.btnGyeongbukAll,
            R.id.btnGyeongbukGyeongsan,
            R.id.btnGyeongbukGyeongju,
            R.id.btnGyeongbukGoryeong,
            R.id.btnGyeongbukGumi,
            R.id.btnGyeongbukGunwi,
            R.id.btnGyeongbukGimcheon,
            R.id.btnGyeongbukMungyeong,
            R.id.btnGyeongbukBonghwa,
            R.id.btnGyeongbukSangju,
            R.id.btnGyeongbukSeongju,
            R.id.btnGyeongbukAndong,
            R.id.btnGyeongbukYeongdeok,
            R.id.btnGyeongbukYeongyang,
            R.id.btnGyeongbukYeongju,
            R.id.btnGyeongbukYeongcheon,
            R.id.btnGyeongbukYecheon,
            R.id.btnGyeongbukUlleung,
            R.id.btnGyeongbukUljin,
            R.id.btnGyeongbukUiseong,
            R.id.btnGyeongbukCheongdo,
            R.id.btnGyeongbukCheongsong,
            R.id.btnGyeongbukChilgok,
            R.id.btnGyeongbukPohang
    };
    Button[] buttons = new Button[24];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_gyeongbuk, container, false);
        for(int i = 0; i<buttonIds.length; i++){
            buttons[i] = root.findViewById(buttonIds[i]);
            buttons[i].setOnClickListener(this);
        }
        return root;
    }

    //서울 전체 누를 시 다음 화면으로 넘어감
    public void onClick(View view) {
        if(view.getId() == R.id.btnGyeongbukAll)
            RecommendActivity3.selected_region += "";
        else{
            RecommendActivity3.selected_region += ((Button) view).getText().toString();
        }
        Intent intent = new Intent(getActivity(), RecommendActivity2.class);
        startActivity(intent);
    }
}