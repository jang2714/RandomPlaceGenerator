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

public class JeonbukFragment extends Fragment implements View.OnClickListener{

    int[] buttonIds = {
            R.id.btnJeonbukAll,
            R.id.btnJeonbukGochang,
            R.id.btnJeonbukGunsan,
            R.id.btnJeonbukGimje,
            R.id.btnJeonbukNamwon,
            R.id.btnJeonbukMuju,
            R.id.btnJeonbukBuan,
            R.id.btnJeonbukSunchang,
            R.id.btnJeonbukWanju,
            R.id.btnJeonbukIksan,
            R.id.btnJeonbukImsil,
            R.id.btnJeonbukJangsu,
            R.id.btnJeonbukJeonju,
            R.id.btnJeonbukJeongeup,
            R.id.btnJeonbukJinan
    };

    Button[] buttons = new Button[15];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_jeonbuk, container, false);
        for(int i = 0; i<buttonIds.length; i++){
            buttons[i] = root.findViewById(buttonIds[i]);
            buttons[i].setOnClickListener(this);
        }
        return root;
    }

    //서울 전체 누를 시 다음 화면으로 넘어감
    public void onClick(View view) {
        if(view.getId() == R.id.btnJeonbukAll)
            RecommendActivity3.selected_region += "";
        else{
            RecommendActivity3.selected_region += ((Button) view).getText().toString();
        }
        Intent intent = new Intent(getActivity(), RecommendActivity2.class);
        startActivity(intent);
    }
}