package com.example.mobileproject.Bookmark.map;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.Bookmark.vo.MapMarkVO;
import com.example.mobileproject.Bookmark.vo.SelectVO;
import com.example.mobileproject.Bookmark.vo.UserPlaceVO;
import com.example.mobileproject.R;
import com.example.mobileproject.Bookmark.util.KakaoMap;
import com.example.mobileproject.baseactivity.BaseActivity;
import com.google.firebase.firestore.auth.User;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPolyline;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class mapRecommendMadedActivity extends BaseActivity {

    ImageButton btn_back, btn_start;

    MapView mapView;
    KakaoMap kakaoMap;
    ViewGroup mapContainer;

    MapMarkVO[] mapMarker;
    String[] name = {"서울특별시청", "시청역", "홍대입구역", "멘야산다이메", "연하동", "캘리포니아 도넛", "현대백화점 신촌점", "아오이토리", "마시타야"};
    String[] category = {"쇼핑", "산책", "산책", "음식점", "음식점", "카페", "쇼핑", "카페", "음식점"};

    ArrayList<UserPlaceVO> userPlaceVO = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_recommend_maded);

        btn_start = findViewById(R.id.btnStart);
        //카카오맵 api 지도

//        Intent intent = getIntent();
//        ArrayList<UserPlaceVO> userList = (ArrayList<UserPlaceVO>) intent.getSerializableExtra("userList");
//        userPlaceVO.addAll(userList);

        ArrayList<UserPlaceVO> receivedList = SelectVO.getInstance().getUserPlaceVO();
        userPlaceVO.addAll(receivedList);

        //TODO.kakaoMap api 사용
        initView();

/*
        mapView = new MapView(this);
        kakaoMap = new KakaoMap(mapView);
        mapContainer = findViewById(R.id.map_view);
        mapContainer.addView(kakaoMap.getMapView());

        //뒤로가기
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
*/

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapContainer.removeView(kakaoMap.getMapView());
                Intent intent = new Intent(mapRecommendMadedActivity.this, mapNavigationActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    //TODO. 마커와 line추가
    private void initView() {

        //카카오맵 api 지도 네이티브
//        mapView = new MapView(this);
        kakaoMap = KakaoMap.getInstance();
        KakaoMap.getInstance().initialize(this);
        mapContainer = findViewById(R.id.map_view);
        mapContainer.addView(kakaoMap.getMapView());


        kakaoMap.MyLocation(); //현재 위치 표시

        //TODO.변경( 이게 되는지 안 되는지도 모르는 상태 )
        double[][] latlon = new double[userPlaceVO.size()][2]; // 장소의 경도, 위도

//        //서울특별시 위도경도
//        latlon[0][0] = 37.5666612;
//        latlon[0][1] = 126.9783785;
//
//        //시청역 위도경도
//        latlon[1][0] = 37.5657037;
//        latlon[1][1] = 126.9768616;
//
//        //홍대입구역 위도경도
//        latlon[2][0] = 37.5551463;
//        latlon[2][1] = 126.9215309;
//
//        //멘야산다이메 위도경도
//        latlon[3][0] = 37.5536013;
//        latlon[3][1] = 126.9214235;
//
//        //연하동 위도경도
//        latlon[4][0] = 37.5604702;
//        latlon[4][1] = 126.9235247;
//
//        //캘리포니아 도넛 위도경도
//        latlon[5][0] = 37.5511476;
//        latlon[5][1] = 126.920215;


        for(int i = 0; i < userPlaceVO.size(); i++){
            latlon[i][0] = Double.parseDouble(userPlaceVO.get(i).getY());
            latlon[i][1] = Double.parseDouble(userPlaceVO.get(i).getX());
        }

        // 현재 위치의 위도와 경도
//        double currentLat = latlon[0][0];
//        double currentLon = latlon[0][1];
//
//        // 거리 계산
//        double[] distances = new double[latlon.length];
//        for (int i = 0; i < latlon.length; i++) {
//            distances[i] = calculateDistance(currentLat, currentLon, latlon[i][0], latlon[i][1]);
//        }
//
//        // 인덱스 배열 생성 및 정렬
//        Integer[] indices = new Integer[latlon.length];
//        for (int i = 0; i < latlon.length; i++) {
//            indices[i] = i;
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            Arrays.sort(indices, Comparator.comparingDouble(a -> distances[a]));
//        }

        // 정렬된 장소 출력




        //TODO.PolyLine 추가
        MapPolyline polyline = new MapPolyline();
        polyline.setLineColor(0xFF00FF00); // 색깔지정
// 정렬된 장소 출력
        for (int i = 0; i < userPlaceVO.size(); i++) {
            System.out.println("장소 " + i + ": 위도 " + latlon[i][0] + ", 경도 " + latlon[i][1]);
            polyline.addPoint(MapPoint.mapPointWithGeoCoord(latlon[i][0], latlon[i][1]));
        }
        kakaoMap.getMapView().addPolyline(polyline); // 폴리 라인 맵에 추가

        mapMarker = new MapMarkVO[userPlaceVO.size()];

        for(int i = 0; i < userPlaceVO.size(); i++){
            mapMarker[i] = new MapMarkVO(Double.parseDouble(userPlaceVO.get(i).getY()), Double.parseDouble(userPlaceVO.get(i).getX()), userPlaceVO.get(i).getPlaceName());
        } // 마커찍는 메소드

        kakaoMap.MapPOIItem(mapMarker); //지도 마커 표시



    }

    // TODO.위도와 경도를 이용한 거리 계산
//    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
//        // 거리 계산 알고리즘을 구현해야 합니다.
//        // 여기서는 단순히 위도 차이와 경도 차이를 더한 값으로 거리를 근사적으로 계산합니다.
//        double latDiff = Math.abs(lat1 - lat2);
//        double lonDiff = Math.abs(lon1 - lon2);
//        return latDiff + lonDiff;
//    }
}