package com.example.mobileproject.Bookmark.map;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;

import java.util.ArrayList;

public class mapCourseCategoryAdapter extends RecyclerView.Adapter<mapCourseCategoryAdapter.CustomViewHolder> {

    private ArrayList<mapCourseCategoryData> arrayList;

    public mapCourseCategoryAdapter(ArrayList<mapCourseCategoryData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_course_category_list, parent, false);

        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.image_view.setImageResource(arrayList.get(position).getImageView());
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        protected ImageView image_view;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.image_view = (ImageView) itemView.findViewById(R.id.map_category_img);
        }
    }

    public void updateList(){
        arrayList.clear();
        notifyDataSetChanged();
    }
}
