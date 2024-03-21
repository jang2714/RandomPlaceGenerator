package com.example.mobileproject.Bookmark.course;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;

import java.util.ArrayList;

public class BookmarkCourseListItemAdapter extends RecyclerView.Adapter<BookmarkCourseListItemAdapter.CustomViewHolder> {

    // 데이터를 저장하는 ArrayList
    private ArrayList<BookmarkCourseData> arrayList;

    // 생성자를 통해 ArrayList를 전달받음
    public BookmarkCourseListItemAdapter(ArrayList<BookmarkCourseData> arrayList) {
        this.arrayList = arrayList;
    }


    // 새로운 뷰 홀더 생성
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 뷰 홀더를 위한 뷰 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmark_course_list, parent,false);
        // 뷰 홀더 객체 생성
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    // 뷰 홀더의 내용을 변경하는 함수
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        // 뷰 홀더에 들어갈 데이터를 가져옴
        holder.cosImageView.setImageResource(arrayList.get(position).getCourseImageView());
        holder.cosName.setText(arrayList.get(position).getCourseName());
        holder.cosLocation.setText(arrayList.get(position).getCourseLocation());

        // 뷰 클릭 리스너 설정
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curName = holder.cosName.getText().toString();
                Toast.makeText(view.getContext(), curName, Toast.LENGTH_SHORT).show();
            }
        });


    }

    // 데이터 개수 반환
    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }



    // 뷰 홀더 클래스
    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView cosImageView;
        protected TextView cosName;
        protected TextView cosLocation;
        protected ImageView cosMenuBtn;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cosImageView = (ImageView)itemView.findViewById(R.id.courseImageView);
            this.cosName = (TextView) itemView.findViewById(R.id.courseName);
            this.cosLocation = (TextView) itemView.findViewById(R.id.courseLocation);
            //this.cosMenuBtn = (ImageView) itemView.findViewById(R.id.courseMenuBtn);
        }
    }
}
