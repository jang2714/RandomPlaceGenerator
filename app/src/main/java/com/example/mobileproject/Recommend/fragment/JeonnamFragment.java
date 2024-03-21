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

public class JeonnamFragment extends Fragment implements View.OnClickListener{

    int[] buttonIds = new int[]{
            R.id.btnJeonnamAll,
            R.id.btnJeonnamGangjin,
            R.id.btnJeonnamGohung,
            R.id.btnJeonnamGokseong,
            R.id.btnJeonnamGwangyang,
            R.id.btnJeonnamGurye,
            R.id.btnJeonnamNaju,
            R.id.btnJeonnamDamyang,
            R.id.btnJeonnamMokpo,
            R.id.btnJeonnamMuan,
            R.id.btnJeonnamBoseong,
            R.id.btnJeonnamSuncheon,
            R.id.btnJeonnamSinan,
            R.id.btnJeonnamYeosu,
            R.id.btnJeonnamYeonggwang,
            R.id.btnJeonnamYeongam,
            R.id.btnJeonnamWando,
            R.id.btnJeonnamJangseong,
            R.id.btnJeonnamJangheung,
            R.id.btnJeonnamJindo,
            R.id.btnJeonnamHamyang,
            R.id.btnJeonnamHaenam,
            R.id.btnJeonnamHwasun
    };

    Button[] buttons = new Button[23];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_jeonnam, container, false);
        for(int i = 0; i<buttonIds.length; i++){
            buttons[i] = root.findViewById(buttonIds[i]);
            buttons[i].setOnClickListener(this);
        }
        return root;
    }

    //서울 전체 누를 시 다음 화면으로 넘어감
    public void onClick(View view) {
        if(view.getId() == R.id.btnJeonnamAll)
            RecommendActivity3.selected_region += "";
        else{
            RecommendActivity3.selected_region += ((Button) view).getText().toString();
        }
        Intent intent = new Intent(getActivity(), RecommendActivity2.class);
        startActivity(intent);
    }
}