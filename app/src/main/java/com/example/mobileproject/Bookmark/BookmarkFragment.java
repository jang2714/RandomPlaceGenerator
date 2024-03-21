package com.example.mobileproject.Bookmark;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import androidx.fragment.app.FragmentTransaction;

import com.example.mobileproject.R;
import com.example.mobileproject.basefragment.BookmarkBaseFragment;
import com.example.mobileproject.Bookmark.course.BookmarkCourseFragment;
import com.example.mobileproject.Bookmark.place.BookmarkPlaceFragment;
import com.example.mobileproject.Bookmark.map.mapActivity;

public class BookmarkFragment extends BookmarkBaseFragment {

    BookmarkPlaceFragment fragment_place;
    Button btn_place, btn_cos;
    ImageButton btn_map;

    public BookmarkFragment() {
        // 빈 생성자가 필요함
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);

        fragment_place = new BookmarkPlaceFragment();
        btn_place = view.findViewById(R.id.btnPlace);
        btn_cos = view.findViewById(R.id.btnCos);

        getChildFragmentManager().beginTransaction()
                .add(R.id.container, fragment_place)
                .commit();

        btn_place.setBackgroundResource(R.drawable.button_bookmark_active);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(0, Color.TRANSPARENT);
        drawable.setStroke(5, Color.BLACK, 0, 2);

        btn_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_place.setTextColor(Color.parseColor("#83C5F4"));
                btn_cos.setTextColor(Color.parseColor("#000000"));
                btn_place.setBackgroundResource(R.drawable.button_bookmark_active);
                btn_cos.setBackgroundResource(R.drawable.button_bookmark_default);
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                BookmarkPlaceFragment bmPlaceFragment = new BookmarkPlaceFragment();
                transaction.replace(R.id.container, bmPlaceFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btn_cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_place.setTextColor(Color.parseColor("#000000"));
                btn_cos.setTextColor(Color.parseColor("#83C5F4"));
                btn_cos.setBackgroundResource(R.drawable.button_bookmark_active);
                btn_place.setBackgroundResource(R.drawable.button_bookmark_default);
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                BookmarkCourseFragment bmCosFragment = new BookmarkCourseFragment();
                transaction.replace(R.id.container, bmCosFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        /*
        // 지도 아이콘 클릭 시 지도로 이동
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), mapActivity.class);
                startActivity(intent);
            }
        });

        */

        return view;
    }
}

//TODO. 색상 추가 07.06