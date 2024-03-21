package com.example.mobileproject.Home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;

import java.util.List;

public class BestAdapter extends RecyclerView.Adapter<BestAdapter.ViewHolder> {
    private List<String> mData;
    // 플래그 추가
    public static final int VIEW_TYPE_HOME = 0;
    public static final int VIEW_TYPE_BEST = 1;
    private int viewType;

    public BestAdapter(List<String> myDataset) {
        mData = myDataset;
        this.viewType = VIEW_TYPE_HOME;  // 기본 화면 타입을 home으로 설정
    }

    public BestAdapter(List<String> myDataset, int viewType) {
        mData = myDataset;
        this.viewType = viewType;  // 화면 타입을 받아서 설정
    }

    @Override
    public int getItemViewType(int position) {
        return viewType;
    }

    @Override
    public BestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == 0) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_best_home, parent, false);
        } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_best_detail, parent, false);
        }
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String currentPlace = mData.get(position);
        holder.tv_name.setText(currentPlace);

        //TODO Glide 라이브러리 사용하여 이미지 구현
   /*     Glide.with(holder.itemView)
                .load(mData.get(position)) // assuming mData contains image URLs
                .into(holder.iv_image);*/
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public ImageView iv_image;

        public ViewHolder(View v) {
            super(v);
            tv_name = v.findViewById(R.id.tv_name);
            iv_image = v.findViewById(R.id.iv_image);
        }
    }
}