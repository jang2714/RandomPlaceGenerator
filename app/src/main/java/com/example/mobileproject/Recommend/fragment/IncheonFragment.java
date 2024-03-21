package com.example.mobileproject.Recommend.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import com.example.mobileproject.R;
import com.example.mobileproject.Recommend.activitiy.RecommendActivity2;
import com.example.mobileproject.Recommend.activitiy.RecommendActivity3;

public class IncheonFragment extends Fragment implements View.OnClickListener{
    int[] buttonIds = {
            R.id.btnIncheonAll,
            R.id.btnIncheonGanghwa,
            R.id.btnIncheonGyeyang,
            R.id.btnIncheonNamdong,
            R.id.btnIncheonDong,
            R.id.btnIncheonMichuhol,
            R.id.btnIncheonBupyeong,
            R.id.btnIncheonSeo,
            R.id.btnIncheonYeonsu,
            R.id.btnIncheonOngjin,
            R.id.btnIncheonJung
    };


    Button[] buttons = new Button[11];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_incheon, container, false);
        for(int i = 0; i<buttonIds.length; i++){
            buttons[i] = root.findViewById(buttonIds[i]);
            buttons[i].setOnClickListener(this);
        }
        return root;
    }

    //서울 전체 누를 시 다음 화면으로 넘어감
    public void onClick(View view) {
        if(view.getId() == R.id.btnIncheonAll)
            RecommendActivity3.selected_region += "";
        else{
            RecommendActivity3.selected_region += ((Button) view).getText().toString();
        }
        Intent intent = new Intent(getActivity(), RecommendActivity2.class);
        startActivity(intent);
    }
}