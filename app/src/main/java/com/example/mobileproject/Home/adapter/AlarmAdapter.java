package com.example.mobileproject.Home.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileproject.R;
import com.example.mobileproject.Home.data.AlarmData;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {

    private List<AlarmData> alarmData;

    public AlarmAdapter(List<AlarmData> alarmData) {
        this.alarmData = alarmData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alram, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AlarmData alarmData = this.alarmData.get(position);
        holder.tvTitle.setText(alarmData.getTitle());
        holder.tvContent.setText(alarmData.getContent());
    }

    @Override
    public int getItemCount() {
        return alarmData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_alramTitle);
            tvContent = itemView.findViewById(R.id.tv_alramContent);
        }
    }
}
