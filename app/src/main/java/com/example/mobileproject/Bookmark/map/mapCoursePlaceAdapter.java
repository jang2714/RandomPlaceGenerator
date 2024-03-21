package com.example.mobileproject.Bookmark.map;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.mobileproject.Bookmark.place.BookmarkPlaceData;
import com.example.mobileproject.R;

import java.util.ArrayList;
public class mapCoursePlaceAdapter extends RecyclerView.Adapter<mapCoursePlaceAdapter.CustomHolder> {
    private ArrayList<BookmarkPlaceData> arrayList_bk;
    private ArrayList<mapCoursePlaceData> arrayList;
    private mapCoursePlaceData placeData;
    private OnItemClickListener listener;
    int itemPosition;

    int num = 0;
    ArrayList<Integer> index = new ArrayList<>();

    //번호 이미지
    int[] imageID = {R.drawable.img_select1
            ,R.drawable.img_select2
            ,R.drawable.img_select3
            ,R.drawable.img_select4
            ,R.drawable.img_select5
            ,R.drawable.img_select6
            ,R.drawable.img_select7
            ,R.drawable.img_select8
            ,R.drawable.img_select9
            ,R.drawable.img_select10
    };

    public mapCoursePlaceAdapter(ArrayList<mapCoursePlaceData> arrayList, OnItemClickListener listener) {
        this.arrayList = arrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public mapCoursePlaceAdapter.CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_map_course_place, parent, false);
        CustomHolder holder = new CustomHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull mapCoursePlaceAdapter.CustomHolder holder, int position) {
        placeData = arrayList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(placeData.getMapImageView())
                .into(holder.mapImageView);


        //placeData = arrayList.get(holder.getAdapterPosition());


        //holder.mapImageView.setImageResource(arrayList.get(position).getMapImageView());
        holder.mapName.setText(arrayList.get(position).getMapName());
        holder.mapLocation.setText(arrayList.get(position).getMapLocation());
        holder.mapCategory.setText(arrayList.get(position).getMapCategory());
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO. 클릭 시 배경색 변경
                itemPosition = holder.getAdapterPosition();

                if(arrayList.get(itemPosition).isMapCheck()){

                    index.add(num);
                    num++;
                    int number = index.get((index.size() - 1));

                    holder.select.setImageResource(imageID[number]);
                    holder.select.setVisibility(View.VISIBLE);
                    holder.mapLayout.setBackgroundResource(R.drawable.layout_map_place_select); // 클릭 시 색깔 변경
                    arrayList.get(itemPosition).setMapCheck(false); // on/off

                    mapCoursePlaceData item = arrayList.get(itemPosition); // 클릭한 list 저장
                    listener.onItemClickSelect(item); //저장한 list 를 listener 에 저장 ( activity 에 보내줄 정보 )

                }else{

                    num--;
                    for(int i = 1; i <= index.size(); i++){
                        int value = index.get(i);
                        if(value != i){
                            for(int j = i; j <= index.size(); j++){
                                int oldValue = index.get(j);
                                int newValue = oldValue - 1;
                                index.set(j, newValue);
                            }
                        }

                        break;
                    }

                    holder.select.setVisibility(View.INVISIBLE);
                    holder.mapLayout.setBackgroundResource(R.drawable.layout_map_place_default); // 클릭 시 색깔 변경
                    arrayList.get(itemPosition).setMapCheck(true); // on/off

                    mapCoursePlaceData item = arrayList.get(itemPosition);
                    listener.onItemClickUnSelect(item);

                }//if-else
            }//onclick
        });//holder clickListener

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public class CustomHolder extends RecyclerView.ViewHolder {

        protected ImageView mapImageView;
        protected TextView mapName;
        protected TextView mapLocation;
        protected TextView mapCategory;
        protected ConstraintLayout mapLayout;
        protected ImageView select;

        public CustomHolder(@NonNull View itemView) {
            super(itemView);
            mapImageView = (ImageView) itemView.findViewById(R.id.mapImageView);
            mapName = (TextView) itemView.findViewById(R.id.mapName);
            mapLocation = (TextView) itemView.findViewById(R.id.mapLocation);
            mapCategory = (TextView) itemView.findViewById(R.id.mapCategory);
            mapLayout = (ConstraintLayout) itemView.findViewById(R.id.mapLayout);
            select = (ImageView) itemView.findViewById(R.id.selectNumber);

        }
    }
    //TODO.클릭 시 넘겨줄 값을 변경할 인터페이스
    public interface OnItemClickListener {
        void onItemClickSelect(mapCoursePlaceData item);

        void onItemClickUnSelect(mapCoursePlaceData item);
    }

}
