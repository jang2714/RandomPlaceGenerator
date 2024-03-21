package com.example.mobileproject.Bookmark.course;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;

import java.util.ArrayList;

public class BookmarkCourseFragment extends Fragment {

    // 필요한 멤버 변수들 선언
    private ArrayList<BookmarkCourseData> arrayListItem; // 즐겨찾기 아이템 목록을 담는 ArrayList
    private BookmarkCourseListItemAdapter BMCosListItemAdapter; // 즐겨찾기 아이템 어댑터
    private RecyclerView recyclerView; // RecyclerView 객체
    private LinearLayoutManager linearLayoutManager; // RecyclerView의 레이아웃 매니저

    String[] courseName = {"홍대에서 데이트", "연남 데이트 코스로 정말 좋아요~!!", "서울 마포구"};
    String[] courseLocation = {"멘야산다이메 - 메가박스 - 와우공원", "깡통만두 - 한옥마을 - 노티드", "지환이네 - 학카 - 지환이네"};
    int[] courseImages = {R.drawable.exam_ramen, R.drawable.exam_move, R.drawable.exam_mega};




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       //화면 inflate
        View view = inflater.inflate(R.layout.bookmark_course_view, container, false);
        /*
        View view_btn = inflater.inflate(R.layout.bookmark_fragment, container, false);


        // RecyclerView 객체와 레이아웃 매니저 객체 생성
        recyclerView = view.findViewById(R.id.listViewCos);
        linearLayoutManager = new LinearLayoutManager(inflater.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        // ArrayList 객체 생성
        arrayListItem = new ArrayList<>();

        // 즐겨찾기 아이템 어댑터 객체 생성
        BMCosListItemAdapter = new BookmarkCourseListItemAdapter(arrayListItem);

        // RecyclerView에 어댑터 연결
        recyclerView.setAdapter(BMCosListItemAdapter);

        // 이미지, 이름, 위치 정보를 담은 배열에서 정보를 가져와서
        // BookmarkListItem 객체를 생성하여 ArrayList에 추가
        for(int i = 0; i < 3; i++){
            BookmarkCourseData bookmarkListItem = new BookmarkCourseData(courseName[i], courseLocation[i], courseImages[i]);
            arrayListItem.add(bookmarkListItem);
        }
        */
        return view;

    }
}
