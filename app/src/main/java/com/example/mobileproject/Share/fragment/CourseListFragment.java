package com.example.mobileproject.Share.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.Share.adapter.CourseListAdapter;
import com.example.mobileproject.Share.data.CourseList;
import com.example.mobileproject.basefragment.ShareBaseFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class CourseListFragment extends ShareBaseFragment {

    private List<CourseList> courseList;
    private RecyclerView recyclerView;
    private CourseListAdapter courseListAdapter;

    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_text, container, false);

        //TODO 코드 삭제

        db = FirebaseFirestore.getInstance();
        courseList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.text_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        courseListAdapter = new CourseListAdapter(courseList);
        recyclerView.setAdapter(courseListAdapter);

        db.collection("share_course").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            for (DocumentSnapshot d : queryDocumentSnapshots.getDocuments()) {
                                CourseList cl = d.toObject(CourseList.class);
                                if (cl != null && cl.getName() != null) { // Check if cl and cl.getName() is not null
                                    courseList.add(cl);
                                    Log.d("db", cl.getName());
                                }
                            }
                            courseListAdapter.notifyDataSetChanged();
                        }
                    }
                });



        return view;
    }

}
