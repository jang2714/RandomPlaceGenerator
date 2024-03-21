package com.example.mobileproject.Bookmark.map;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mobileproject.Bookmark.vo.UserPlaceVO;
import com.example.mobileproject.R;
import com.example.mobileproject.Bookmark.util.KakaoMap;
import com.example.mobileproject.Bookmark.vo.MapMarkVO;
import com.example.mobileproject.baseactivity.CreateCourseBaseActivity;
import com.example.mobileproject.baseactivity.RecommendBaseActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.List;


public class mapActivity extends RecommendBaseActivity {

    Button btn_cos_setting;
    MapView mapView;
    ViewGroup mapContainer;
    KakaoMap kakaoMap;

    MapMarkVO[] mapMarker;
    /*
    MapMarkVO[] mapMarker = new MapMarkVO[6];
    String[] name = {"서울특별시청", "시청역", "홍대입구역", "멘야산다이메", "연하동", "캘리포니아 도넛"};
    String[] category = {"쇼핑", "산책", "산책", "음식점", "음식점", "카페"};

*/

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION};

    ArrayList<UserPlaceVO> userPlaceVO = new ArrayList<>();
    UserPlaceVO userPlaceList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //액션바 타이틀, 뒤로가기
        setupActionBar("코스 생성", true);

        //TODO 이미지버튼 변수 변경
        ImageButton btn_cos_create = findViewById(R.id.btnCosCreate);
        ImageButton btn_cos_recommend = findViewById(R.id.btnCosRecommend);





        //TODO bookmark_place 값 가져오기
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        try{
            db.collection("bookmark_place")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                //TODO 리스트 bookmark place 생성
                                //TODO for문으로 UserPlaceVO.class에 추가
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    UserPlaceVO place = document.toObject(UserPlaceVO.class);
                                    userPlaceVO.add(place);
                                    Log.d("장소: " ,userPlaceVO.get(0).getPlaceName());
                                }
                                //TODO 어댑터에 연결

                                Log.d("장소 개수: " , String.valueOf(userPlaceVO.size()));
                                initView();
                            } else {
                            }
                        }
                    });
        }catch(Exception e){
            e.printStackTrace();
        }finally {

        }



        //코스 추천 버튼 이벤트
        btn_cos_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapContainer.removeView(kakaoMap.getMapView());
                Intent intent = new Intent(mapActivity.this, mapCoursePlaceActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //코스 수동 설정 버튼 이벤트
        btn_cos_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mapActivity.this, "공사 중", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void initView() {

        //카카오맵 api 지도 네이티브
//        mapView = new MapView(this);
        kakaoMap = KakaoMap.getInstance();
        KakaoMap.getInstance().initialize(this);
//        kakaoMap = new KakaoMap(mapView);
        mapContainer = findViewById(R.id.map_view);
        mapContainer.addView(kakaoMap.getMapView());

        mapMarker = new MapMarkVO[userPlaceVO.size()];

        kakaoMap.MyLocation(); //현재 위치 표시

        Log.d("장소크기확인", String.valueOf(userPlaceVO.size()));
        for(int i = 0; i < userPlaceVO.size(); i++){
            userPlaceList = userPlaceVO.get(i);
            mapMarker[i] = new MapMarkVO(Double.parseDouble(userPlaceList.getY()), Double.parseDouble(userPlaceList.getX()), userPlaceList.getPlaceName());

            Log.d("장소좌표확인", userPlaceList.getPlaceName());
        } // 마커찍는 메소드
        kakaoMap.MapPOIItem(mapMarker); //지도 마커 표시
    }
    /*

        private void initView() {

            //카카오맵 api 지도 네이티브
            mapView = new MapView(this);
            kakaoMap = new KakaoMap(mapView);
            ViewGroup mapContainer = findViewById(R.id.map_view);
            mapContainer.addView(kakaoMap.getMapView());


            kakaoMap.MyLocation(); //현재 위치 표시

            //TODO.북마크에서 가져온 장소에 개수만큼 2차원 배열 생성
            double[][] latlon = new double[6][2]; // 장소의 경도, 위도

            //서울특별시 위도경도
            latlon[0][0] = 37.5666612;
            latlon[0][1] = 126.9783785;

            //시청역 위도경도
            latlon[1][0] = 37.5657037;
            latlon[1][1] = 126.9768616;

            //홍대입구역 위도경도
            latlon[2][0] = 37.5551463;
            latlon[2][1] = 126.9215309;

            //멘야산다이메 위도경도
            latlon[3][0] = 37.5536013;
            latlon[3][1] = 126.9214235;

            //연하동 위도경도
            latlon[4][0] = 37.5604702;
            latlon[4][1] = 126.9235247;

            //캘리포니아 도넛 위도경도
            latlon[5][0] = 37.5511476;
            latlon[5][1] = 126.920215;



            for(int i = 0; i < 6; i++){
                mapMarker[i] = new MapMarkVO(latlon[i][0], latlon[i][1], name[i], category[i]);
            }

            kakaoMap.MapPOIItem(mapMarker); //지도 마커 표시



        }
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.close_menu, menu); // your_menu.xml 이 메뉴 리소스의 이름
        return true;
    }
}