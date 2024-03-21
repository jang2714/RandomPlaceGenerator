package com.example.mobileproject.Recommend.activitiy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileproject.R;
import com.example.mobileproject.baseactivity.RecommendBaseActivity;


public class RecommendActivity2 extends RecommendBaseActivity {

    int cnt = 0;
    LinearLayout layout_course;
    ImageButton[] btn_category;
    Integer[] img_category = {
            R.drawable.ic_place_restaurant,
            R.drawable.ic_place_cafe,
            R.drawable.ic_place_walk,
            R.drawable.ic_place_culture,
            R.drawable.ic_place_shopping,
            R.drawable.ic_place_play,
            R.drawable.ic_place_tour,
            R.drawable.ic_place_stay};
    TextView text_selectedRegion;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend2);

        //액션바 타이틀, 뒤로가기
        setupActionBar("카테고리 설정", true);

        ImageButton btn_next = findViewById(R.id.btn_next);
        layout_course = findViewById(R.id.layout_course);
        ImageButton btn_reset = findViewById(R.id.btn_reset);

        //text_selectedRegion = findViewById(R.id.selected_region_text);
        //text_selectedRegion.setText(RecommendActivity3.selected_region);

        btn_category = new ImageButton[8];
        Integer[] btn_category_id = {R.id.btn_restaurant, R.id.btn_cafe, R.id.btn_walk, R.id.btn_culture, R.id.btn_shopping, R.id.btn_play, R.id.btn_tour, R.id.btn_stay};
        String[] category_text = {"음식점", "카페", "공원", "문화생활", "쇼핑", "놀거리", "관광명소", "숙소"};

        for(int i = 0; i<8; i++){
            btn_category[i] = findViewById(btn_category_id[i]);
            final int index = i;
            Log.d("TAG", "test : " + index);
            //각 카테고리 별 버튼 클릭 시 코스의 카테고리 화면에 출력
            btn_category[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createTextView(img_category[index]);
                    btn_category[index].setClickable(false);
                    RecommendActivity3.selected_category[index] = category_text[index];
                    Log.d(""+index, "지역 " + RecommendActivity3.selected_region + " 카테고리 " + RecommendActivity3.selected_category[index]);
                }
            });
        }

        //초기화 버튼 구현
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cnt = 0;
                layout_course.removeAllViews();
                for(int i = 0; i<8; i++){
                    btn_category[i].setClickable(true);
                    RecommendActivity3.selected_category[i] = "";
                    Log.d(""+i, "onClick: " + RecommendActivity3.selected_category[i]);
                }
            }
        });


        //다음으로 넘어가는 버튼 구현
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cnt==0){
                    Toast.makeText(RecommendActivity2.this,"카테고리를 하나 이상 선택하세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), RecommendActivity3.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void createTextView(Integer img){
        ImageView imageViewNm = new ImageView(getApplicationContext());
        imageViewNm.setImageResource(img);
        cnt++;
        imageViewNm.setScaleType(ImageView.ScaleType.FIT_CENTER);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.leftMargin = 30;
        param.width = 100;
        param.height = 100;
        imageViewNm.setLayoutParams(param);
        layout_course.addView(imageViewNm);
    }


}
