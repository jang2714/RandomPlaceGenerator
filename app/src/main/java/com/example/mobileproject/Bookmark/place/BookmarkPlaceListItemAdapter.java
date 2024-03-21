package com.example.mobileproject.Bookmark.place;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.mobileproject.Home.activity.PlaceInfoActivity;
import com.example.mobileproject.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BookmarkPlaceListItemAdapter extends RecyclerView.Adapter<BookmarkPlaceListItemAdapter.CustomViewHolder> {
    private Context context;
    // 데이터를 저장하는 ArrayList
    private ArrayList<BookmarkPlaceData> arrayList;
    // 이미지, 이름, 위치 정보를 담은 배열

    // 생성자를 통해 ArrayList를 전달받음
    public BookmarkPlaceListItemAdapter(Context context , ArrayList<BookmarkPlaceData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    // 새로운 뷰 홀더 생성
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //onCreate이랑 비슷하게 recyclerView가 처음 생성될 때 생명주기를 뜻한다.
        // 뷰 홀더를 위한 뷰 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_place_list, parent,false);
        // 뷰 홀더 객체 생성
        Log.d("holder값" , "holder보내줌");
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    // 뷰 홀더의 내용을 변경하는 함수
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        //TODO 코드수정
        BookmarkPlaceData bookmarkPlaceData = arrayList.get(holder.getAdapterPosition());
        Log.d("settext" , bookmarkPlaceData.getPlaceName());

        Glide.with(context)
                .load(bookmarkPlaceData.getImgURL())
                .into(holder.placeImageView);
        // 뷰 홀더에 들어갈 데이터를 가져옴
        //holder.placeImageView.setImageResource(arrayList.get(position).getPlaceImageView());
        holder.placeName.setText(bookmarkPlaceData.getPlaceName());
        holder.placeLocation.setText(bookmarkPlaceData.getAddressName());

        holder.itemView.setBackgroundResource(R.drawable.layout_place_shape);

        // 뷰 클릭 리스너 설정
        holder.itemView.setTag(position);
        //클릭이벤트
        //TODO 클릭이벤트 코드 변경
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curName = holder.placeName.getText().toString();

                Intent intent = new Intent(view.getContext(), BookmarkPlaceitemActivity.class);
                intent.putExtra("placeName", curName);

                Log.d("intent 값 전달" , curName);
                view.getContext().startActivity(intent);

                Toast.makeText(view.getContext(), curName, Toast.LENGTH_SHORT).show();
            }
        });*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curName = holder.placeName.getText().toString();

                Intent intent = new Intent(view.getContext(), PlaceInfoActivity.class); // PlaceInfoActivity로 이동
                intent.putExtra("placeName", curName); // placeName 데이터 함께 전달

                Log.d("intent 값 전달" , curName);
                view.getContext().startActivity(intent);

                Toast.makeText(view.getContext(), curName, Toast.LENGTH_SHORT).show();
            }
        });


        // 삭제 버튼 클릭 리스너 설정
        //TODO 디비값 삭제 이벤트 추가
        holder.placeDelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(holder.getAdapterPosition());

                String curName = holder.placeName.getText().toString();

                FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("bookmark_place")
                        .whereEqualTo("placeName" , curName)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()){
                                    String documentId = documentSnapshot.getId();

                                    db.collection("bookmark_place").document(documentId)
                                            .delete()
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    // 삭제 성공 시, 해당 데이터를 목록에서도 제거
                                                    for (int i = 0; i < arrayList.size(); i++) {
                                                        if (arrayList.get(i).getPlaceName().equals(curName)) {
                                                            arrayList.remove(i);
                                                            break;
                                                        }
                                                    }
                                                    notifyDataSetChanged();
                                                }

                                            });
                                }
                            }
                        });
            }
        });
    }

    // 데이터 개수 반환
    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    // 아이템 삭제 함수
    public void remove(int position) {
        try {
            arrayList.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    // 뷰 홀더 클래스
    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView placeImageView;
        protected TextView placeName;
        protected TextView placeLocation;
        protected ImageView placeDelBtn;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            //activity가 이니기 때문에 itmeView에서 받아온다.
            this.placeImageView = (ImageView)itemView.findViewById(R.id.placeImageView);
            this.placeName = (TextView) itemView.findViewById(R.id.placeName);
            this.placeLocation = (TextView) itemView.findViewById(R.id.placeLocation);
            this.placeDelBtn = (ImageView) itemView.findViewById(R.id.placeDelBtn);
        }
    }
    public void updateList(ArrayList<BookmarkPlaceData> newList) {
        arrayList.clear(); // 기존 데이터를 모두 제거합니다.
        arrayList.addAll(newList); // 새로운 데이터로 ArrayList를 업데이트합니다.
        notifyDataSetChanged(); // RecyclerView에 변경된 데이터를 알립니다.
    }



}
