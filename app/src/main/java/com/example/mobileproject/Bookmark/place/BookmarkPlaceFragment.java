package com.example.mobileproject.Bookmark.place;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BookmarkPlaceFragment extends Fragment {


    //TODO db변수선언 및 아래 더미값 주석처리
    FirebaseFirestore db;

    // 필요한 멤버 변수들 선언
    private ArrayList<BookmarkPlaceData> arrayListItem; // 즐겨찾기 아이템 목록을 담는 ArrayList
    private BookmarkPlaceListItemAdapter bookmarkListItemAdapter; // 즐겨찾기 아이템 어댑터
    private RecyclerView recyclerView; // RecyclerView 객체
    private LinearLayoutManager linearLayoutManager; // RecyclerView의 레이아웃 매니저
    private BookmarkPlaceData bookmarkData;

    Button btnKaAll, btnKaRestaurant, btnKaCafe, btnKaPark, btnKaCulture, btnKaPlay, btnKaVacation, btnKaShopping;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bookmark_place_view, container, false);

        btnKaAll = view.findViewById(R.id.btnKaAll);
        btnKaRestaurant = view.findViewById(R.id.btnKaRestaurant);
        btnKaCafe = view.findViewById(R.id.btnKaCafe);
        btnKaCulture = view.findViewById(R.id.btnKaCultare);
        btnKaPark = view.findViewById(R.id.btnKaPark);
        btnKaPlay = view.findViewById(R.id.btnKaPlay);
//        btnKaVacation = view.findViewById(R.id.btnKaVacation);
//        btnKaShopping = view.findViewById(R.id.btnKaShopping);

        // RecyclerView 객체와 레이아웃 매니저 객체 생성
        recyclerView = view.findViewById(R.id.listViewPlace);
        linearLayoutManager = new LinearLayoutManager(inflater.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        // ArrayList 객체 생성
        arrayListItem = new ArrayList<>();

        // 즐겨찾기 아이템 어댑터 객체 생성
        bookmarkListItemAdapter = new BookmarkPlaceListItemAdapter(getActivity() , arrayListItem);

        // RecyclerView에 어댑터 연결
        recyclerView.setAdapter(bookmarkListItemAdapter);


        //TODO 코드추가
        db = FirebaseFirestore.getInstance();

        db.collection("bookmark_place").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()){

                            BookmarkPlaceData bpd= documentSnapshot.toObject(BookmarkPlaceData.class);

                            arrayListItem.add(bpd);
                            Log.d("값추가" , "배열에 북마크값 추가" +bpd.getImgURL()+ bpd.getPlaceName() + bpd.getCategoryName());
                        }
                        bookmarkListItemAdapter.notifyDataSetChanged();
                    }
                });

        // 이미지, 이름, 위치 정보를 담은 배열에서 정보를 가져와서
        // BookmarkListItem 객체를 생성하여 ArrayList에 추가
        //TODO 아래코드 다 수정


        btnKaAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnKaAll.setTextColor(Color.parseColor("#83C5F4"));
                btnKaRestaurant.setTextColor(Color.parseColor("#000000"));
                btnKaCafe.setTextColor(Color.parseColor("#000000"));
                btnKaCulture.setTextColor(Color.parseColor("#000000"));
                btnKaPark.setTextColor(Color.parseColor("#000000"));
                btnKaPlay.setTextColor(Color.parseColor("#000000"));
                arrayListItem.clear();

                db.collection("bookmark_place")
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()){

                                    BookmarkPlaceData bpd = documentSnapshot.toObject(BookmarkPlaceData.class);

                                    arrayListItem.add(bpd);
                                }
                                bookmarkListItemAdapter.notifyDataSetChanged();
                            }
                        });


            }
        });

        btnKaRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnKaAll.setTextColor(Color.parseColor("#000000"));
                btnKaRestaurant.setTextColor(Color.parseColor("#83C5F4"));
                btnKaCafe.setTextColor(Color.parseColor("#000000"));
                btnKaCulture.setTextColor(Color.parseColor("#000000"));
                btnKaPark.setTextColor(Color.parseColor("#000000"));
                btnKaPlay.setTextColor(Color.parseColor("#000000"));
                arrayListItem.clear();

                db.collection("bookmark_place")
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()){

                                    BookmarkPlaceData bpd = documentSnapshot.toObject(BookmarkPlaceData.class);

                                    if (bpd.getCategoryName().equals("음식점")) {
                                        arrayListItem.add(bpd);
                                    }
                                }
                                bookmarkListItemAdapter.notifyDataSetChanged();
                            }
                        });


            }
        });

        btnKaCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnKaAll.setTextColor(Color.parseColor("#000000"));
                btnKaRestaurant.setTextColor(Color.parseColor("#000000"));
                btnKaCafe.setTextColor(Color.parseColor("#83C5F4"));
                btnKaCulture.setTextColor(Color.parseColor("#000000"));
                btnKaPark.setTextColor(Color.parseColor("#000000"));
                btnKaPlay.setTextColor(Color.parseColor("#000000"));
                arrayListItem.clear();

                db.collection("bookmark_place")
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()){

                                    BookmarkPlaceData bpd = documentSnapshot.toObject(BookmarkPlaceData.class);

                                    if (bpd.getCategoryName().equals("카페")) {
                                        arrayListItem.add(bpd);
                                    }
                                }
                                bookmarkListItemAdapter.notifyDataSetChanged();
                            }
                        });


            }
        });

        btnKaCulture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnKaAll.setTextColor(Color.parseColor("#000000"));
                btnKaRestaurant.setTextColor(Color.parseColor("#000000"));
                btnKaCafe.setTextColor(Color.parseColor("#000000"));
                btnKaCulture.setTextColor(Color.parseColor("#83C5F4"));
                btnKaPark.setTextColor(Color.parseColor("#000000"));
                btnKaPlay.setTextColor(Color.parseColor("#000000"));
                arrayListItem.clear();

                db.collection("bookmark_place")
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()){

                                    BookmarkPlaceData bpd = documentSnapshot.toObject(BookmarkPlaceData.class);

                                    if (bpd.getCategoryName().equals("문화")) {
                                        arrayListItem.add(bpd);
                                    }
                                }
                                bookmarkListItemAdapter.notifyDataSetChanged();
                            }
                        });


            }
        });

        btnKaPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnKaAll.setTextColor(Color.parseColor("#000000"));
                btnKaRestaurant.setTextColor(Color.parseColor("#000000"));
                btnKaCafe.setTextColor(Color.parseColor("#000000"));
                btnKaCulture.setTextColor(Color.parseColor("#000000"));
                btnKaPark.setTextColor(Color.parseColor("#83C5F4"));
                btnKaPlay.setTextColor(Color.parseColor("#000000"));
                arrayListItem.clear();

                db.collection("bookmark_place")
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()){

                                    BookmarkPlaceData bpd = documentSnapshot.toObject(BookmarkPlaceData.class);

                                    if (bpd.getCategoryName().equals("공원")) {
                                        arrayListItem.add(bpd);
                                    }

                                }
                                bookmarkListItemAdapter.notifyDataSetChanged();
                            }
                        });


            }
        });

        btnKaPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnKaAll.setTextColor(Color.parseColor("#000000"));
                btnKaRestaurant.setTextColor(Color.parseColor("#000000"));
                btnKaCafe.setTextColor(Color.parseColor("#000000"));
                btnKaCulture.setTextColor(Color.parseColor("#000000"));
                btnKaPark.setTextColor(Color.parseColor("#000000"));
                btnKaPlay.setTextColor(Color.parseColor("#83C5F4"));
                arrayListItem.clear();

                db.collection("bookmark_place")
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()){

                                    BookmarkPlaceData bpd = documentSnapshot.toObject(BookmarkPlaceData.class);

                                    if (bpd.getCategoryName().equals("놀거리")) {
                                        arrayListItem.add(bpd);
                                    }

                                }
                                bookmarkListItemAdapter.notifyDataSetChanged();
                            }
                        });


            }
        });
//
//        btnKaVacation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                arrayListItem.clear();
//
//                db.collection("bookmark_place")
//                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                            @Override
//                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//                                for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()){
//
//                                    BookmarkPlaceData bpd = documentSnapshot.toObject(BookmarkPlaceData.class);
//
//                                    if (bpd.getCategoryName().equals("휴양지")) {
//                                        arrayListItem.add(bpd);
//                                    }
//
//                                }
//                                bookmarkListItemAdapter.notifyDataSetChanged();
//                            }
//                        });
//
//
//            }
//        });
//
//        btnKaShopping.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                arrayListItem.clear();
//
//                db.collection("bookmark_place")
//                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                            @Override
//                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//                                for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()){
//
//                                    BookmarkPlaceData bpd = documentSnapshot.toObject(BookmarkPlaceData.class);
//
//                                    if (bpd.getCategoryName().equals("쇼핑")) {
//                                        arrayListItem.add(bpd);
//                                    }
//
//                                }
//                                bookmarkListItemAdapter.notifyDataSetChanged();
//                            }
//                        });
//
//
//            }
//        });


        return view;

    }


}
//TODO. 카테고리 추가 07.06
