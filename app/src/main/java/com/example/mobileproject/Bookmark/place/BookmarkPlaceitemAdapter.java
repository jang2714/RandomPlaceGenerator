package com.example.mobileproject.Bookmark.place;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobileproject.Home.data.BestPlaceData;
import com.example.mobileproject.R;
import com.example.mobileproject.Recommend.model.category_search.Document;
import com.example.mobileproject.Recommend.model.category_search.PlaceData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BookmarkPlaceitemAdapter extends RecyclerView.Adapter<BookmarkPlaceitemAdapter.VH> {

    private Context context;
    private ArrayList<Document> arrayList;
    private ArrayList<PlaceData> placeList;
    private FirebaseFirestore db;

    public BookmarkPlaceitemAdapter(Context context, ArrayList<Document> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.placeList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        fetchDataFromFirestore();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_detailplacesub1, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Document doc = arrayList.get(position);

        if (doc != null) {
            Glide.with(context)
                    .load(doc.getImgURL())
                    .into(holder.place_image);
            Log.d("이미지값" , doc.getImgURL());
            holder.place_text.setText(doc.getPlaceName());
            holder.phone_text.setText("전화번호: " + doc.getPhone());
            holder.address_text.setText("주소: " + doc.getAddressName());
            holder.category_text.setText("카테고리: " + doc.getCategoryName());
            if (!placeList.isEmpty()) {
                for (PlaceData place : placeList) {
                    if (place.getAddressName().equals(doc.getAddressName())) {
                        holder.btn_like.setText(String.valueOf(place.getCount_like()));
                        holder.btn_bookmark.setText(String.valueOf(place.getCount_bookmark()));
                        break;
                    }
                }
            }
        }

        holder.btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = holder.btn_like.getText().toString();
                int test =0;

                test = Integer.parseInt(num) + 1;
                num = String.valueOf(test);
                holder.btn_like.setText(num);
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView place_text, phone_text, category_text, address_text, locate_text;
        Button btn_like, btn_bookmark;
        ImageView place_image;

        public VH(View itemView) {
            super(itemView);

            place_image = itemView.findViewById(R.id.place_img);
            place_text = itemView.findViewById(R.id.place_text);
            phone_text = itemView.findViewById(R.id.number_text);
            category_text = itemView.findViewById(R.id.explain_text);
            address_text = itemView.findViewById(R.id.local_text);

                btn_like = itemView.findViewById(R.id.place_sub_like_button);
                btn_bookmark = itemView.findViewById(R.id.place_sub_bookmark_button);
        }
    }

    private void fetchDataFromFirestore() {
        db.collection("place_data").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                            PlaceData bp = documentSnapshot.toObject(PlaceData.class);
                            placeList.add(bp);
                        }
                        notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("BookmarkPlaceAdapter", "데이터 가져오기 실패: " + e.getMessage());
                    }
                });
    }
}
