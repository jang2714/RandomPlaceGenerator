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

public class ChungnamFragment extends Fragment implements View.OnClickListener{

    // 버튼 아이디 배열
    int[] buttonIds = {
            R.id.btnChungnamAll,
            R.id.btnChungnamGyeryong,
            R.id.btnChungnamGongju,
            R.id.btnChungnamGeumsan,
            R.id.btnChungnamNonsan,
            R.id.btnChungnamDangjin,
            R.id.btnChungnamBoryeong,
            R.id.btnChungnamBuyeo,
            R.id.btnChungnamSeosan,
            R.id.btnChungnamSeocheon,
            R.id.btnChungnamAsan,
            R.id.btnChungnamYesan,
            R.id.btnChungnamCheonan,
            R.id.btnChungnamCheongyang,
            R.id.btnChungnamTaean,
            R.id.btnChungnamHongseong
    };
    Button[] buttons = new Button[16];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.fragment_chungnam, container, false);
        for(int i = 0; i<buttonIds.length; i++){
            buttons[i] = root.findViewById(buttonIds[i]);
            buttons[i].setOnClickListener(this);
        }
        return root;
    }

    //서울 전체 누를 시 다음 화면으로 넘어감
    public void onClick(View view) {
        if(view.getId() == R.id.btnChungnamAll)
            RecommendActivity3.selected_region += "";
        else{
            RecommendActivity3.selected_region += ((Button) view).getText().toString();
        }
        Intent intent = new Intent(getActivity(), RecommendActivity2.class);
        startActivity(intent);
    }
}