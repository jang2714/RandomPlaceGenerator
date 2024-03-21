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

public class GangwonFragment extends Fragment implements View.OnClickListener{

    int[] buttonIds = {
            R.id.btnGangwonAll,
            R.id.btnGangwonGangneung,
            R.id.btnGangwonGoseong,
            R.id.btnGangwonDonghae,
            R.id.btnGangwonSamcheok,
            R.id.btnGangwonSokcho,
            R.id.btnGangwonYanggu,
            R.id.btnGangwonYangyang,
            R.id.btnGangwonYeongwol,
            R.id.btnGangwonWonju,
            R.id.btnGangwonInje,
            R.id.btnGangwonJeongseon,
            R.id.btnGangwonCheorwon,
            R.id.btnGangwonChuncheon,
            R.id.btnGangwonTaebaek,
            R.id.btnGangwonPyeongchang,
            R.id.btnGangwonHongcheon,
            R.id.btnGangwonHwacheon,
            R.id.btnGangwonHoengseong
    };
    Button[] buttons = new Button[19];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_gangwon, container, false);
        for(int i = 0; i<buttonIds.length; i++){
            buttons[i] = root.findViewById(buttonIds[i]);
            buttons[i].setOnClickListener(this);
        }
        return root;
    }

    //서울 전체 누를 시 다음 화면으로 넘어감
    public void onClick(View view) {
        if(view.getId() == R.id.btnGangwonAll)
            RecommendActivity3.selected_region += "";
        else{
            RecommendActivity3.selected_region += ((Button) view).getText().toString();
        }
        Intent intent = new Intent(getActivity(), RecommendActivity2.class);
        startActivity(intent);
    }
}