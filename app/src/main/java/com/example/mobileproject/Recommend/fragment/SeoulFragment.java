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

public class SeoulFragment extends Fragment implements View.OnClickListener{

    int[] buttonIds = new int[]{
            R.id.btnSeoulAll,
            R.id.btnSeoulGangnam,
            R.id.btnSeoulGangdong,
            R.id.btnSeoulGangbuk,
            R.id.btnSeoulGangseo,
            R.id.btnSeoulGwanak,
            R.id.btnSeoulGwangjin,
            R.id.btnSeoulGuro,
            R.id.btnSeoulGeumcheon,
            R.id.btnSeoulNowon,
            R.id.btnSeoulDobong,
            R.id.btnSeoulDongdaemun,
            R.id.btnSeoulDongjak,
            R.id.btnSeoulMapo,
            R.id.btnSeoulSeodaemun,
            R.id.btnSeoulSeocho,
            R.id.btnSeoulSeongdong,
            R.id.btnSeoulSeongbuk,
            R.id.btnSeoulSongpa,
            R.id.btnSeoulYangcheon,
            R.id.btnSeoulYeongdeungpo,
            R.id.btnSeoulYongsan,
            R.id.btnSeoulEunpyeong,
            R.id.btnSeoulJongno,
            R.id.btnSeoulJunggu,
            R.id.btnSeoulJungnang
    };

    Button[] buttons = new Button[26];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_seoul, container, false);
        for(int i = 0; i<buttonIds.length; i++){
            buttons[i] = root.findViewById(buttonIds[i]);
            buttons[i].setOnClickListener(this);
        }
        return root;
    }

    //서울 전체 누를 시 다음 화면으로 넘어감
    public void onClick(View view) {
        if(view.getId() == R.id.btnSeoulAll)
            RecommendActivity3.selected_region += "";
        else{
            RecommendActivity3.selected_region += ((Button) view).getText().toString();
        }
        Intent intent = new Intent(getActivity(), RecommendActivity2.class);
        startActivity(intent);
    }
}