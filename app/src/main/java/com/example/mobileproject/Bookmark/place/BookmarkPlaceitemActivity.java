package com.example.mobileproject.Bookmark.place;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.Recommend.model.category_search.Document;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BookmarkPlaceitemActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BookmarkPlaceitemAdapter bookmarkPlaceitemAdapter;

    String placeName;
    ArrayList<Document> arrayList;
    FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);

        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.place_rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList = new ArrayList<>();
        bookmarkPlaceitemAdapter = new BookmarkPlaceitemAdapter(this, arrayList);

        recyclerView.setAdapter(bookmarkPlaceitemAdapter);

        Intent intent = getIntent();
        if (intent != null) {
            placeName = intent.getStringExtra("placeName");
            Log.d("intent 값 받기", placeName);
        }

        fetchDataFromFirestore();
    }

    private void fetchDataFromFirestore() {
        CollectionReference collectionRef = db.collection("bookmark_place");
        collectionRef.whereEqualTo("placeName", placeName)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                            Document doc = documentSnapshot.toObject(Document.class);
                            arrayList.add(doc);
                            Log.d("Place 데이터 추가", doc.getPlaceName());
                        }
                        bookmarkPlaceitemAdapter.notifyDataSetChanged();
                    }
                });
    }
}
