package com.example.mobileproject.Bookmark.map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.Bookmark.vo.MapMarkVO;
import com.example.mobileproject.Bookmark.vo.SelectVO;
import com.example.mobileproject.Bookmark.vo.UserPlaceVO;
import com.example.mobileproject.Home.activity.MainActivity;
import com.example.mobileproject.R;
import com.example.mobileproject.Bookmark.BookmarkFragment;
import com.example.mobileproject.Bookmark.util.KakaoMap;
import com.example.mobileproject.baseactivity.BaseActivity;
import com.example.mobileproject.baseactivity.RecommendBaseActivity;

import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

public class mapNavigationActivity extends RecommendBaseActivity {

    ImageButton btn_x;
    mapNavigationFragment mapNavigationFragment;
    MapView mapView;
    KakaoMap kakaoMap;
    ViewGroup mapContainer;

    MapMarkVO[] mapMarker = new MapMarkVO[1];
    ArrayList<UserPlaceVO> userPlaceVO = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_navigation);

        mapNavigationFragment = new mapNavigationFragment();
        View view_main  = findViewById(R.id.map_view);
        View view_sub = findViewById(R.id.container);


        ArrayList<UserPlaceVO> receivedList = SelectVO.getInstance().getUserPlaceVO();
        userPlaceVO.addAll(receivedList);

        //TODO.kakaoMap api 사용
        initView();

        //fragment 실행
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, mapNavigationFragment)
                .commit();

        view_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = view_sub.getLayoutParams();
                params.height = dpToPx( mapNavigationActivity.this, 41);
                view_sub.setLayoutParams(params);
            }
        });

        view_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params = view_sub.getLayoutParams();
                params.height = dpToPx(mapNavigationActivity.this, 205);
                view_sub.setLayoutParams(params);
            }
        });

    }
    public static int dpToPx(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density);
    }

    //TODO. 마커와 line추가
    private void initView() {

        //카카오맵 api 지도 네이티브
//      mapView = new MapView(this);
        kakaoMap = KakaoMap.getInstance();
        KakaoMap.getInstance().initialize(this);
        mapContainer = findViewById(R.id.map_view);
        kakaoMap.MyLocation(); //현재 위치 표시
        mapContainer.addView(kakaoMap.getMapView());


        double[][] latlon = new double[userPlaceVO.size()][2];

        for(int i = 0; i < userPlaceVO.size(); i++){
            latlon[i][0] = Double.parseDouble(userPlaceVO.get(i).getY());
            latlon[i][1] = Double.parseDouble(userPlaceVO.get(i).getX());
        }
        Log.d("NavigationView 실행 " , userPlaceVO.get(0).getPlaceName());


        Bundle bundle = new Bundle();
        bundle.putSerializable("userPlaceVO", userPlaceVO.get(0));

        mapNavigationFragment fragment = new mapNavigationFragment();
        fragment.setArguments(bundle);

        mapMarker[0] = new MapMarkVO(latlon[0][0], latlon[0][1], userPlaceVO.get(0).getPlaceName());

        kakaoMap.MapPOIItem(mapMarker); //지도 마커 표시

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.close_menu, menu); // your_menu.xml 이 메뉴 리소스의 이름
        return true;
    }
}