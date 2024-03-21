package com.example.mobileproject.Recommend.activitiy;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileproject.Recommend.api.ApiClient;
import com.example.mobileproject.Recommend.api.ApiInterface;
import com.example.mobileproject.Recommend.api.Scraping;
import com.example.mobileproject.Recommend.model.category_search.CategoryResult;
import com.example.mobileproject.Recommend.model.category_search.Document;
import com.example.mobileproject.R;
import com.example.mobileproject.baseactivity.RecommendBaseActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class RecommendActivity3 extends RecommendBaseActivity {
    public static String selected_region;
    public static String selected_category[] = {"","","","","","","",""};
    TextView text_region;
    TextView text_category;
    TextView text_place_name;
    TextView text_place_address;
    TextView text_phone;
    TextView text_url;
    WebView webView;
    List<Document> documents;
    String source;
    public static String[] url = {"", "","", "","", "","", "","", "","", "","", "",""};
    public static int img_index = 0;

    //    private boolean isPageLoaded = false;
    String recommend_data[][] = new String[5][6];
    int index1 = 0;
    int[] same_num = new int[5];

    int same_num_index = 0;
    int index = 0;
    int category_index = 0;
    int category_cnt = 0; // 몇 개의 카테고리가 선택됐는지
    MapView mapView1;
    static boolean next = true;


    Call<CategoryResult> call;


    //TODO 아래 전역번수 새로선언
    String S_placeName , S_address , S_phone , S_longitude , S_latitude , S_category , S_URL;
    String pn , addr , phone , x , y , cate , imgURL;
    ImageButton btn_bookmark;
    FirebaseFirestore db;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend3);
        setupActionBar("추천 장소", true);
        //인플레이팅
        //TODO 버튼이랑 db코드 추가
        btn_bookmark = findViewById(R.id.btn_bookmark);
        db = FirebaseFirestore.getInstance();

        ImageButton btn_retry = findViewById(R.id.btn_rc_retry);
        ImageButton btn_next_category = findViewById(R.id.btn_next_category);
        text_region = findViewById(R.id.selected_region_text);
        text_category = findViewById(R.id.selected_category_text);
        text_place_name = findViewById(R.id.place_name);
        text_place_address = findViewById(R.id.place_address);
        text_phone = findViewById(R.id.phone);
        webView = findViewById(R.id.webView);
        mapView1 = findViewById(R.id.map_view);
        for(int i=0; i<selected_category.length; i++){
            if(!selected_category[i].equals(""))
                category_cnt++;
        }
        Log.d("TAGgggggg", "onCreate: "+ category_cnt);
        for(int i = 0; i < same_num.length; i++)
            same_num[i] = -1;

        text_region.setText(selected_region);
        for( ; category_index < 8 ; category_index++, index++){
            if(!selected_category[category_index].equals("")){
                text_category.setText(selected_category[category_index++]);
                Log.d("category_index", "onCreate: " + category_index + "       " + index);
                break;
            }
        }
        getRecommendData();



        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recommend();
            }
        });

        btn_next_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: 다음 카테고리 실행" + category_index + "next" + next);
                next = false;
                Log.d("TAG", "next" + next);
                for(int i = 0; i<5; i++){
                    RecommendActivity3.url[i] ="";
                    Log.d("TAG", " 진짜 이미지 URL : " + RecommendActivity3.url[i]);
                }


                for( ; category_index < 8 ; category_index++){
                    Log.d("For 문 안 인덱스", ""+category_index);
                    if(!selected_category[category_index].equals("")){
                        text_category.setText(selected_category[category_index++]);
                        Log.d("For 문 안 인덱스", ""+category_index);
                        Log.d("TAG", "category_index ..............8이 아니라 실행");
                        same_num_index = 0;
                        for(int i = 0; i < same_num.length; i++){
                            same_num[i] = -1;
                            Log.d("same_num 배열", "onClick: " + same_num[i]);
                        }
                        Log.d("same_num_index", "onClick: " + same_num_index);
                        next = true;
                        Log.d("TAG", "next :  "+ next);
                        getRecommendData();

                        break;
                    }
                }
//                if(category_index==7)
//                    category_index++;
                Log.d("TAG", "category_index .............." + category_index);
                if(category_index == 8){
                    Toast.makeText(RecommendActivity3.this,"더 이상 선택된 카테고리가 없습니다.",Toast.LENGTH_SHORT).show();
                }
                else{

                }

            }
        });

        //TODO 북마크 버튼 클릭했을 떄 이벤트 추가
        btn_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Queryset(pn , addr , phone , x , y , cate , imgURL);
                Queryset_2(pn ,addr);
            }
        });



    }

    void getRecommendData(){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        String token = "KakaoAK 5a789edd205baf79e1cb414deba14a1e"; //REST API 키
        String query = selected_region + " " + selected_category[index]; // 검색문
        int size = 5 ; // 정보 검색 수 (1~15 가능)

        for(int i = 0; i<recommend_data.length; i++){
            for(int j = 0; j<recommend_data[i].length; j++){
                recommend_data[i][j] = "";
            }
        }

        Log.d("query", "recommend: "+query + "index" + index + selected_category[index]);
        if(selected_category[index].equals("음식점")){
            query = selected_region;
            String category_group_code = "FD6"; //카테고리 음식점
            call = apiInterface.getSearchLocation2(token, query, category_group_code, size);
        }
        else if (selected_category[index].equals("카페")){
            String category_group_code = "CE7"; //카테고리 카페
            call = apiInterface.getSearchLocation2(token, query, category_group_code, size);
        }
        else if (selected_category[index].equals("관광 명소")){
            query = selected_region;
            Log.d("TAG", "index: "+index + "쿼리 : "+query);
            String category_group_code = "AT4"; //카테고리 관광명소
            call = apiInterface.getSearchLocation2(token, query, category_group_code, size);
        }
        else if (selected_category[index].equals("문화 생활")){
            Log.d("TAG", "index: "+index + "쿼리 : "+query);
            query = selected_region;
            String category_group_code = "CT1"; //카테고리 문화 시설
            call = apiInterface.getSearchLocation2(token, query, category_group_code, size);
        }
        else if (selected_category[index].equals("숙소")){
            Log.d("TAG", "index: "+index + "쿼리 : "+query);
            query = selected_region;
            String category_group_code = "AD5"; //카테고리 문화 시설
            call = apiInterface.getSearchLocation2(token, query, category_group_code, size);
        }
        else{
            Log.d("TAG", "index: "+index + "쿼리 : "+query);
            if(selected_category[index].equals("놀거리"))
                query = selected_region + "피시방";
            else if(selected_category[index].equals("쇼핑"))
                query = selected_region + "백화점";
            Log.d("TAG", "index: "+index + "쿼리 : "+query);
            call = apiInterface.getSearchLocation(token, query, size);
        }
        //숙박, 쇼핑 대형마트 카테고리로 검색 한 번 해보고, 놀거리에 노래방, 당구장, 볼링장
        index++;
        call.enqueue(new Callback<CategoryResult>() {
            @Override
            public void onResponse(Call<CategoryResult> call, Response<CategoryResult> response) {
                if (response.isSuccessful()) {
                    CategoryResult result = response.body();
                    documents = result.getDocuments();



                    index1 = 0; // document를 처리하는 for문에서 사용하는 index1
                    for (Document document : documents) {
                        String placeName = document.getPlaceName();  //장소명 받아오기
                        String address = document.getAddressName(); //주소 받아오기
                        String phone = document.getPhone(); //번호 받아오기
                        String longitude = document.getX(); //경도 받아오기
                        String latitude = document.getY(); //위도 받아오기
                        String category_group_code = document.getCategoryGroupCode(); // 카테고리 그룹 코드
                        String category_group_name = document.getCategoryGroupName(); // 카테고리 그룹 이름
                        String categoryName = document.getCategoryName(); // 카테고리 이름
                        String placeUrl = document.getPlaceUrl(); // 장소 url

                        // ... 기타 document 정보 활용

                        recommend_data[index1][0] = placeName;
                        recommend_data[index1][1] = address;
                        recommend_data[index1][2] = phone;
                        recommend_data[index1][3] = longitude;
                        recommend_data[index1][4] = latitude;
                        recommend_data[index1][5] = placeUrl;



                        Log.d("TAG", "onResponse: " + recommend_data[index1][0] + "---" + recommend_data[index1][1] + "---" + recommend_data[index1][2] + "---" + category_group_code  + "---" + category_group_name + " 카테고리 네임" + categoryName + " 주소 : "+recommend_data[index1][5] + " 이미지 URL : " + url);

                        index1++;
                    }
                    for(int i = 0; i < recommend_data.length; i++){
                        Log.d("TAG", "onResponse: " +recommend_data[i][0]);
                    }
                    index1 = 0;
                    img_index = 0;
                    Log.d("TAG", "onResponse: 11111111111"+ index1);
                    final Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            // Your thread's logic goes here
                            if (index1 < 5 && next == true) {
                                webView = findViewById(R.id.webView);
                                Log.d("TAG", "run: " + recommend_data[index1][5]);
                                new Scraping(webView, recommend_data[index1][5]);
                                Log.d("TAG", "index1 : " + index1);

                                index1++;
                                handler.postDelayed(this, 3000); // 3초 후에 다음 document로 넘어감
                            }
                        }
                    };
                    // 최초 실행
                    handler.postDelayed(runnable, 3000);

                    for(int i = 0; i<5; i++){
                        Log.d("TAG", " 진짜 이미지 URL : " + RecommendActivity3.url[i]);
                    }
                    recommend();

                } else {
                    // 오류 처리
                    Log.d("오류", "onResponse: 오류");
                }
            }

            @Override
            public void onFailure(Call<CategoryResult> call, Throwable t) {
                // 실패 처리
                Log.d("실패", "onResponse: 실패");
            }
        });
    }

    void recommend(){
        for(int i = 0; i<5; i++){
            Log.d("TAG", " 진짜 이미지 URL : " + RecommendActivity3.url[i]);
        }
        Random random = new Random();
        int random_num;
        boolean same_check = false;
        while(true){
            Log.d("TAG", "recommend: whlie문 동작중" + same_num_index);
            if(same_num_index >= 5){
                //Toast.makeText(RecommendActivity3.this,"더 반복하려면 앱을 구독하시오.",Toast.LENGTH_SHORT).show();
                Toast.makeText(RecommendActivity3.this,"더 이상 추천할 수 있는 장소가 없습니다.",Toast.LENGTH_SHORT).show();
                break;
            }
            random_num = random.nextInt(5);

            for(int i=0; i<same_num.length;i++){
                if(random_num == same_num[i]){
                    same_check = true;
                    break;
                }
                else
                    same_check = false;
            }
            if(same_check == false){
                same_num[same_num_index++] = random_num;
                if (recommend_data[random_num][0] == null || recommend_data[random_num][0].isEmpty()) {
                    continue; // recommend_data[random_num][0] 값이 비어 있으면 다시 무작위 숫자 선택
                }
                //지도에 마커 찍기
                //중심점 변경
                mapView1.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(recommend_data[random_num][4]), Double.parseDouble(recommend_data[random_num][3])), true);
                // 줌 초기화
                // 줌 레벨 변경
                mapView1.setZoomLevel(1, true);
                // 줌 인
                mapView1.zoomIn(true);
                // 줌 아웃
                mapView1.zoomOut(true);
                MapPoint MARKER_POINT1 = MapPoint.mapPointWithGeoCoord(Double.parseDouble(recommend_data[random_num][4]), Double.parseDouble(recommend_data[random_num][3]));
                // 마커 아이콘 추가하는 함수
                MapPOIItem marker1 = new MapPOIItem();
                // 클릭 했을 때 나오는 호출 값
                marker1.setItemName("추천 장소");
                // 좌표를 입력받아 현 위치로 출력
                marker1.setMapPoint(MARKER_POINT1);
                //  (클릭 전)기본으로 제공하는 BluePin 마커 모양의 색.
                marker1.setMarkerType(MapPOIItem.MarkerType.BluePin);
                // (클릭 후) 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
                marker1.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
                mapView1.addPOIItem(marker1);
                //텍스트뷰에 정보 올리기
                for(int i = 0; i < same_num.length; i++){
                    Log.d("same_num 배열", "onClick: " + same_num[i]);
                }
                Log.d("same_num_index", "onClick: " + same_num_index);
                Log.d("TAG", "랜덤숫자 발생 " + random_num);
                text_place_name.setText(recommend_data[random_num][0]);
                text_place_address.setText(recommend_data[random_num][1]);
                text_phone.setText(recommend_data[random_num][2]);



                //TODO 코드 추가
                pn = recommend_data[random_num][0];
                addr = recommend_data[random_num][1];
                phone = recommend_data[random_num][2];
                x = recommend_data[random_num][3];
                y = recommend_data[random_num][4];
                imgURL = (RecommendActivity3.url[random_num]);
                Log.d("imgURL" , "값좀 보자" + imgURL );
                String currentCategory = text_category.getText().toString();
                cate = currentCategory.substring(currentCategory.indexOf(":") + 1).trim();


                break;

            }
        }
    }
    public void Queryset(String pn, String addr, String phone, String x, String y, String cate ,String URL) {

        String S_placeName = pn;
        String S_address = addr;
        String S_phone = phone;
        String S_latitude = x;
        String S_longitude = y;
        String S_category = cate;
        String S_URL = URL;

        Map<String, Object> data = new HashMap<>();
        data.put("placeName", S_placeName);
        data.put("addressName", S_address);
        data.put("phone", S_phone);
        data.put("x", S_latitude);
        data.put("y", S_longitude);
        data.put("categoryName", S_category);
        data.put("imgURL" , S_URL);

        CollectionReference crf = db.collection("bookmark_place");
        //TODO 예외처리 추가
        crf.whereEqualTo("addressName", S_address) // addressName이 같은 데이터 쿼리
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.isEmpty()) {
                            // addressName이 같은 문서가 없는 경우
                            crf.add(data)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(getApplicationContext(), "데이터 넣기 성공", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(Exception e) {
                                            Toast.makeText(getApplicationContext(), "데이터 넣기 실패", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            // addressName이 같은 문서가 이미 있는 경우
                            Toast.makeText(getApplicationContext(), "데이터 넣기 실패: 이미 같은 addressName이 존재합니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        // 쿼리 실행 실패 시
                        Toast.makeText(getApplicationContext(), "쿼리 실행 실패", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void Queryset_2(String pn , String addr){

        String S_placeName = pn;
        String S_address = addr;

        CollectionReference crf = db.collection("place_data");

        crf.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        boolean isAddressExists = false;

                        // 성공적으로 문서들을 가져온 경우
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            String documentId = documentSnapshot.getId();
                            String address = documentSnapshot.getString("addressName");
                            Log.d("주소값" , address);

                            if (address.equals(S_address)) {
                                // addressName이 이미 있는 경우
                                isAddressExists = true;
                                int countBookmark = documentSnapshot.getLong("count_bookmark").intValue() + 1;
                                // count_bookmark 값을 1 증가시키기 위해 업데이트
                                crf.document(documentId)
                                        .update("count_bookmark", countBookmark)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(getApplicationContext(), "count_bookmark 값 업데이트 성공", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(Exception e) {
                                                Toast.makeText(getApplicationContext(), "count_bookmark 값 업데이트 실패", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                break;
                            }
                        }

                        if (!isAddressExists) {
                            // addressName이 없는 경우
                            Map<String, Object> data = new HashMap<>();
                            data.put("placeName", S_placeName);
                            data.put("addressName", S_address);
                            data.put("count_bookmark", Integer.valueOf(1));
                            data.put("count_like" , Integer.valueOf(0));

                            crf.add(data)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(getApplicationContext(), "새로운 문서 추가 성공", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(Exception e) {
                                            Toast.makeText(getApplicationContext(), "새로운 문서 추가 실패", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        // 문서 가져오기 실패 시
                        Toast.makeText(getApplicationContext(), "문서 가져오기 실패: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }



}

