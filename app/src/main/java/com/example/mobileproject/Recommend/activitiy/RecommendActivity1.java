package com.example.mobileproject.Recommend.activitiy;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.example.mobileproject.R;
import com.example.mobileproject.Recommend.fragment.BusanFragment;
import com.example.mobileproject.Recommend.fragment.ChungbukFragment;
import com.example.mobileproject.Recommend.fragment.ChungnamFragment;
import com.example.mobileproject.Recommend.fragment.DaejeonFragment;
import com.example.mobileproject.Recommend.fragment.DagueFragment;
import com.example.mobileproject.Recommend.fragment.GangwonFragment;
import com.example.mobileproject.Recommend.fragment.GwangjuFragment;
import com.example.mobileproject.Recommend.fragment.GyeongbukFragment;
import com.example.mobileproject.Recommend.fragment.GyeonggiFragment;
import com.example.mobileproject.Recommend.fragment.GyeongnamFragment;
import com.example.mobileproject.Recommend.fragment.IncheonFragment;
import com.example.mobileproject.Recommend.fragment.JejuFragment;
import com.example.mobileproject.Recommend.fragment.JeonbukFragment;
import com.example.mobileproject.Recommend.fragment.JeonnamFragment;
import com.example.mobileproject.Recommend.fragment.SejongFragment;
import com.example.mobileproject.Recommend.fragment.SeoulFragment;
import com.example.mobileproject.Recommend.fragment.UlsanFragment;
import com.example.mobileproject.baseactivity.RecommendBaseActivity;


public class RecommendActivity1 extends RecommendBaseActivity {

    private FragmentManager fragmentManager;
    private SeoulFragment fragmentSeoul;
    private BusanFragment fragmentBusan;
    private ChungbukFragment fragmentChungbuk;
    private ChungnamFragment fragmentChungnam;
    private DaejeonFragment fragmentDaejeon;
    private DagueFragment fragmentDague;
    private GangwonFragment fragmentGangwon;
    private GwangjuFragment fragmentGwangju;
    private GyeongbukFragment fragmentGyeongbuk;
    private GyeonggiFragment fragmentGyeonggi;
    private GyeongnamFragment fragmentGyeongnam;
    private IncheonFragment fragmentIncheon;
    private JejuFragment fragmentJeju;
    private JeonbukFragment fragmentJeonbuk;
    private JeonnamFragment fragmentJeonnam;
    private SejongFragment fragmentSejong;
    private UlsanFragment fragmentUlsan;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend1);

        //액션바 타이틀, 뒤로가기
        setupActionBar("지역 선택", true);

        fragmentManager = getSupportFragmentManager();
        fragmentSeoul = new SeoulFragment();
        fragmentBusan = new BusanFragment();
        fragmentChungbuk = new ChungbukFragment();
        fragmentChungnam = new ChungnamFragment();
        fragmentDaejeon = new DaejeonFragment();
        fragmentDague = new DagueFragment();
        fragmentGangwon = new GangwonFragment();
        fragmentGwangju = new GwangjuFragment();
        fragmentGyeongbuk = new GyeongbukFragment();
        fragmentGyeonggi = new GyeonggiFragment();
        fragmentGyeongnam = new GyeongnamFragment();
        fragmentIncheon = new IncheonFragment();
        fragmentJeju = new JejuFragment();
        fragmentJeonbuk = new JeonbukFragment();
        fragmentJeonnam = new JeonnamFragment();
        fragmentSejong = new SejongFragment();
        fragmentUlsan = new UlsanFragment();

        RecommendActivity3.selected_region = "서울시 ";
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.linearLayout, fragmentSeoul).commit();
    }

    public void clickHandler(View view){
        transaction = fragmentManager.beginTransaction();
        switch (view.getId()){
            case R.id.btn_seoul:
                Log.d("TAG", "clickHandler: seoul");
                RecommendActivity3.selected_region = "서울시 ";
                transaction.replace(R.id.linearLayout, fragmentSeoul).commit();
                break;
            case R.id.btn_busan:
                Log.d("TAG", "clickHandler: busan");
                RecommendActivity3.selected_region = "부산시 ";
                transaction.replace(R.id.linearLayout, fragmentBusan).commit();
                break;
            case R.id.btn_incheon:
                Log.d("TAG", "clickHandler: incheon");
                RecommendActivity3.selected_region = "인천시 ";
                transaction.replace(R.id.linearLayout, fragmentIncheon).commit();
                break;
            case R.id.btn_daegu:
                Log.d("TAG", "clickHandler: daegu");
                RecommendActivity3.selected_region = "대구시 ";
                transaction.replace(R.id.linearLayout, fragmentDague).commit();
                break;
            case R.id.btn_chungbuk:
                Log.d("TAG", "clickHandler: chungbuk");
                RecommendActivity3.selected_region = "충청북도 ";
                transaction.replace(R.id.linearLayout, fragmentChungbuk).commit();
                break;
            case R.id.btn_chungnam:
                Log.d("TAG", "clickHandler: chungnam");
                RecommendActivity3.selected_region = "충청남도 ";
                transaction.replace(R.id.linearLayout, fragmentChungnam).commit();
                break;
            case R.id.btn_daejeon:
                Log.d("TAG", "clickHandler: daejeon");
                RecommendActivity3.selected_region = "대전시 ";
                transaction.replace(R.id.linearLayout, fragmentDaejeon).commit();
                break;
            case R.id.btn_gangwon:
                Log.d("TAG", "clickHandler: gangwon");
                RecommendActivity3.selected_region = "강원도 ";
                transaction.replace(R.id.linearLayout, fragmentGangwon).commit();
                break;
            case R.id.btn_gwangju:
                Log.d("TAG", "clickHandler: gwangju");
                RecommendActivity3.selected_region = "광주시 ";
                transaction.replace(R.id.linearLayout, fragmentGwangju).commit();
                break;
            case R.id.btn_gyeongbuk:
                Log.d("TAG", "clickHandler: gyeongbuk");
                RecommendActivity3.selected_region = "경상북도 ";
                transaction.replace(R.id.linearLayout, fragmentGyeongbuk).commit();
                break;
            case R.id.btn_gyeonggi:
                Log.d("TAG", "clickHandler: gyeonggi");
                RecommendActivity3.selected_region = "경기도 ";
                transaction.replace(R.id.linearLayout, fragmentGyeonggi).commit();
                break;
            case R.id.btn_gyeongnam:
                Log.d("TAG", "clickHandler: gyeongnam");
                RecommendActivity3.selected_region = "경상남도 ";
                transaction.replace(R.id.linearLayout, fragmentGyeongnam).commit();
                break;
            case R.id.btn_jeju:
                Log.d("TAG", "clickHandler: jeju");
                RecommendActivity3.selected_region = "제주도 ";
                transaction.replace(R.id.linearLayout, fragmentJeju).commit();
                break;
            case R.id.btn_jeonbuk:
                Log.d("TAG", "clickHandler: jeonbuk");
                RecommendActivity3.selected_region = "전라북도 ";
                transaction.replace(R.id.linearLayout, fragmentJeonbuk).commit();
                break;
            case R.id.btn_jeonnam:
                Log.d("TAG", "clickHandler: jeonnam");
                RecommendActivity3.selected_region = "전라남도 ";
                transaction.replace(R.id.linearLayout, fragmentJeonnam).commit();
                break;
            case R.id.btn_sejong:
                Log.d("TAG", "clickHandler: sejong");
                RecommendActivity3.selected_region = "세종시 ";
                transaction.replace(R.id.linearLayout, fragmentSejong).commit();
                break;
            case R.id.btn_ulsan:
                Log.d("TAG", "clickHandler: ulsan");
                RecommendActivity3.selected_region = "울산시 ";
                transaction.replace(R.id.linearLayout, fragmentUlsan).commit();
                break;
            default:
                break;
        }
    }
}