package com.example.mobileproject.Share.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mobileproject.Home.activity.CourseInfoActivity;
import com.example.mobileproject.R;
import com.example.mobileproject.Share.adapter.Place_list_Adapter;
import com.example.mobileproject.Share.adapter.Slide_list_Adapter;
import com.example.mobileproject.basefragment.ShareBaseFragment;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class ShareFragment extends ShareBaseFragment {

    CourseListFragment courseListFragment;

    FrameLayout list_frame , place_frame;

    Button slideButton , placeButton;
    ListView slide_list , place_list;

    List<String> place_items , slide_items;

    Place_list_Adapter place_item_adapter;
    Slide_list_Adapter slide_item_adapter;

    FirebaseFirestore db;

    public ShareFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_share, container , false);;

        db = FirebaseFirestore.getInstance();


        courseListFragment = new CourseListFragment();

        Bundle place_bundle = new Bundle();
        Bundle slide_bundle = new Bundle();


        placeButton = view.findViewById(R.id.place);
        slideButton = view.findViewById(R.id.slide_down);
        slide_list= view.findViewById(R.id.slide_down_list);

        place_list = view.findViewById(R.id.place_list);

        place_items = new ArrayList<>();
        place_items.add("서울시");
        place_items.add("경기도");
        place_items.add("강원도");
        place_items.add("충청도");
        place_items.add("경상도");
        place_items.add("전라도");
        place_items.add("제주도");

        slide_items = new ArrayList<>();
        slide_items.add("최신순");
        slide_items.add("조회순");
        slide_items.add("추천순");

        View.OnClickListener[] place_clickListeners = new View.OnClickListener[place_items.size()];
        View.OnClickListener[] slide_clickListeners = new View.OnClickListener[slide_items.size()];

        for (int i = 0; i < place_clickListeners.length; i++) {
            final int index = i;
            place_clickListeners[i] = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "클릭한 아이템: " + place_items.get(index), Toast.LENGTH_SHORT).show();
                    // 클릭한 아이템에 대한 동작 수행
                    placeButton.setText(place_items.get(index));
                    place_slideUp(); // 아이템 선택 후 리스트 숨기기

                    place_bundle.putString("p_key", place_items.get(index));

                    Log.d("bundle", place_bundle.get("p_key").toString());

                    courseListFragment.setArguments(place_bundle);
                }
            };
        }

        for (int i = 0; i < slide_clickListeners.length; i++) {
            final int index = i;
            slide_clickListeners[i] = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity() , "클릭한 아이템: " + slide_items.get(index), Toast.LENGTH_SHORT).show();
                    // 클릭한 아이템에 대한 동작 수행
                    slideButton.setText(slide_items.get(index));
                    slide_slideUp(); // 아이템 선택 후 리스트 숨기기

                    slide_bundle.putString("s_key", slide_items.get(index));

                    Log.d("bundle", slide_bundle.get("s_key").toString());

                    courseListFragment.setArguments(slide_bundle);
                    Log.d("setbundle" , slide_bundle+"값");
                }
            };
        }


        place_item_adapter = new Place_list_Adapter(getActivity(), place_items);
        place_item_adapter.setItemClickListeners(place_clickListeners);
        place_list.setAdapter(place_item_adapter);



        place_frame = view.findViewById(R.id.place_frame);

        list_frame = view.findViewById(R.id.list_frame);

        slide_item_adapter = new Slide_list_Adapter(getActivity() , slide_items);
        slide_item_adapter.setItemClickListeners(slide_clickListeners);
        slide_list.setAdapter(slide_item_adapter);


        list_frame.bringToFront();
        place_frame.bringToFront();




        slideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slide_list.getVisibility() == View.GONE) {
                    slide_slideDown();
                } else {
                    slide_slideUp();
                }
            }
        });

        placeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (place_list.getVisibility() == View.GONE) {
                    place_slideDown();
                } else {
                    place_slideUp();
                }
            }
        });
/*
        //TODO 코드추가 추가했더니 액션바가 이상함
        getFragmentManager().beginTransaction().replace(R.id.fragment, courseListFragment).commit();
*/
        Button temp_btn = view.findViewById(R.id.temp_btn);

        temp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CourseInfoActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


    private void slide_slideDown() {
        Animation slideAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);
        slide_list.startAnimation(slideAnimation);
        slide_list.setVisibility(View.VISIBLE);
    }

    private void slide_slideUp() {
        Animation slideAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up);
        slide_list.startAnimation(slideAnimation);
        slide_list.setVisibility(View.GONE);
    }


    private void place_slideDown() {
        Animation slideAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_down);
        place_list.startAnimation(slideAnimation);
        place_list.setVisibility(View.VISIBLE);
    }

    private void place_slideUp() {
        Animation slideAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_up);
        place_list.startAnimation(slideAnimation);
        place_list.setVisibility(View.GONE);
    }
}