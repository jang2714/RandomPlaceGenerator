package com.example.mobileproject.Bookmark.map;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.mobileproject.Bookmark.vo.SelectVO;
import com.example.mobileproject.Bookmark.vo.UserPlaceVO;
import com.example.mobileproject.R;
import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class mapNavigationFragment extends Fragment {

    TextView title, location, category;
    ImageView image;
    ArrayList<UserPlaceVO> userPlaceVO = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bookmark_map_destination, container, false);

        title = view.findViewById(R.id.destinationTitle);
        location = view.findViewById(R.id.destinationLocation);
        category = view.findViewById(R.id.destinationCategory);
        image = view.findViewById(R.id.destinationImage);



        ArrayList<UserPlaceVO> receivedList = SelectVO.getInstance().getUserPlaceVO();
        userPlaceVO.addAll(receivedList);


        Log.d("네비게이션", userPlaceVO.get(0).getPlaceName());
        title.setText(userPlaceVO.get(0).getPlaceName());
        location.setText(userPlaceVO.get(0).getAddressName());
        category.setText(userPlaceVO.get(0).getCategoryName());
        Glide.with(getContext())
                .load(userPlaceVO.get(0).getImgURL())
                .into(image);

        return view;
    }
}
