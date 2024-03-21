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

public class GyeonggiFragment extends Fragment implements View.OnClickListener{

    int[] buttonIds = {
            R.id.btnGyeonggiAll,
            R.id.btnGyeonggiGapyeong,
            R.id.btnGyeonggiGoyang,
            R.id.btnGyeonggiGwacheon,
            R.id.btnGyeonggiGwangmyeong,
            R.id.btnGyeonggiGwangju,
            R.id.btnGyeonggiGuri,
            R.id.btnGyeonggiGunpo,
            R.id.btnGyeonggiGimpo,
            R.id.btnGyeonggiNamyangju,
            R.id.btnGyeonggiDongducheon,
            R.id.btnGyeonggiBucheon,
            R.id.btnGyeonggiSeongnam,
            R.id.btnGyeonggiSuwon,
            R.id.btnGyeonggiSiheung,
            R.id.btnGyeonggiAnsan,
            R.id.btnGyeonggiAnseong,
            R.id.btnGyeonggiAnyang,
            R.id.btnGyeonggiYangju,
            R.id.btnGyeonggiYangpyeong,
            R.id.btnGyeonggiYeoju,
            R.id.btnGyeonggiYeoncheon,
            R.id.btnGyeonggiOsan,
            R.id.btnGyeonggiYongin,
            R.id.btnGyeonggiUiwang,
            R.id.btnGyeonggiUijeongbu,
            R.id.btnGyeonggiIcheon,
            R.id.btnGyeonggiPaju,
            R.id.btnGyeonggiPyeongtaek,
            R.id.btnGyeonggiPocheon,
            R.id.btnGyeonggiHanam,
            R.id.btnGyeonggiHwaseong
    };

    Button[] buttons = new Button[32];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_gyeonggi, container, false);
        for(int i = 0; i<buttonIds.length; i++){
            buttons[i] = root.findViewById(buttonIds[i]);
            buttons[i].setOnClickListener(this);
        }
        return root;
    }

    //서울 전체 누를 시 다음 화면으로 넘어감
    public void onClick(View view) {
        if(view.getId() == R.id.btnGyeonggiAll)
            RecommendActivity3.selected_region += "";
        else{
            RecommendActivity3.selected_region += ((Button) view).getText().toString();
        }
        Intent intent = new Intent(getActivity(), RecommendActivity2.class);
        startActivity(intent);
    }
}