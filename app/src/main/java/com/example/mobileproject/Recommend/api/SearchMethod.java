package com.example.mobileproject.Recommend.api;//package com.example.home.api;
//
//import android.util.Log;
//
//import com.example.home.api.ApiClient;
//import com.example.home.api.ApiInterface;
//import com.example.home.model.category_search.CategoryResult;
//import com.example.home.model.category_search.Document;
//
//import java.util.List;
//
//import retrofit2.Response;
//import retrofit2.Call;
//import retrofit2.Callback;
//
//public class SearchMethod {
//    List<Document> documents;
//    ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//    String token = "KakaoAK 6a02c0bc22de25317d876ff325e784ca"; //REST API 키
//    String query = "청양군 카페"; // 검색문
//    int size = 10; // 정보 검색 수 (1~45 가능)
//
//    Call<CategoryResult> call = apiInterface.getSearchLocation(token, query, size);
//                call.enqueue(new Callback<CategoryResult>() {
//        @Override
//        public void onResponse(Call<CategoryResult> call, Response<CategoryResult> response) {
//            if (response.isSuccessful()) {
//                CategoryResult result = response.body();
//                documents = result.getDocuments();
//                int index = 0;
//                for (Document document : documents) {
//                    String placeName = document.getPlaceName();
//                    String address = document.getAddressName();
//                    // ... 기타 document 정보 활용
//
//                    textViews[index].setText(placeName + " --- " + address);
//                    index++;
//                }
//
//            } else {
//                // 오류 처리
//                Log.d("오류", "onResponse: 오류");
//            }
//        }
//
//        @Override
//        public void onFailure(Call<CategoryResult> call, Throwable t) {
//            // 실패 처리
//            Log.d("실패", "onResponse: 실패");
//        }
//    });
//}
