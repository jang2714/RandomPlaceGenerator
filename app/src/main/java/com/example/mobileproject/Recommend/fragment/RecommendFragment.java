package com.example.mobileproject.Recommend.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mobileproject.R;
import com.example.mobileproject.Recommend.activitiy.RecommendActivity1;

public class RecommendFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);

        // Start your activity here
        Intent intent = new Intent(getActivity(), RecommendActivity1.class);
        startActivity(intent);

        return view;
    }
}
