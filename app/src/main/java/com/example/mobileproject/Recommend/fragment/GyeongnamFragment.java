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

public class GyeongnamFragment extends Fragment implements View.OnClickListener{

    int[] buttonIds = new int[]{
            R.id.btnGyeongnamAll,
            R.id.btnGyeongnamGeoje,
            R.id.btnGyeongnamGeochang,
            R.id.btnGyeongnamGoseong,
            R.id.btnGyeongnamGimhae,
            R.id.btnGyeongnamNamhae,
            R.id.btnGyeongnamMiryang,
            R.id.btnGyeongnamSacheon,
            R.id.btnGyeongnamSancheong,
            R.id.btnGyeongnamYangsan,
            R.id.btnGyeongnamUryeong,
            R.id.btnGyeongnamJinju,
            R.id.btnGyeongnamChangnyeong,
            R.id.btnGyeongnamChangwon,
            R.id.btnGyeongnamTongyeong,
            R.id.btnGyeongnamHadong,
            R.id.btnGyeongnamHaman,
            R.id.btnGyeongnamHamyang,
            R.id.btnGyeongnamHapcheon
    };


    Button[] buttons = new Button[19];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_gyeongnam, container, false);
        for(int i = 0; i<buttonIds.length; i++){
            buttons[i] = root.findViewById(buttonIds[i]);
            buttons[i].setOnClickListener(this);
        }
        return root;
    }

    //서울 전체 누를 시 다음 화면으로 넘어감
    public void onClick(View view) {
        if(view.getId() == R.id.btnGyeongnamAll)
            RecommendActivity3.selected_region += "";
        else{
            RecommendActivity3.selected_region += ((Button) view).getText().toString();
        }
        Intent intent = new Intent(getActivity(), RecommendActivity2.class);
        startActivity(intent);
    }
}