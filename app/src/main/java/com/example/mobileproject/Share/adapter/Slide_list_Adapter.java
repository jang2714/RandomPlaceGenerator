package com.example.mobileproject.Share.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mobileproject.R;

import java.util.List;


public class Slide_list_Adapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> itemList;
    private View.OnClickListener[] itemClickListeners;

    public Slide_list_Adapter(Context context, List<String> itemList) {
        super(context, 0, itemList);
        this.context = context;
        this.itemList = itemList;
        this.itemClickListeners = new View.OnClickListener[itemList.size()];
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_slide, parent, false);
        }

        String item = itemList.get(position);

        TextView place_list_item = convertView.findViewById(R.id.slide_list_item);
        place_list_item.setText(item);

        // 각 아이템에 클릭 이벤트 추가
        convertView.setOnClickListener(itemClickListeners[position]);

        return convertView;
    }

    public void setItemClickListeners(View.OnClickListener[] clickListeners) {
        if (clickListeners.length == itemList.size()) {
            itemClickListeners = clickListeners;
        } else {
            throw new IllegalArgumentException("..");
        }
    }
}