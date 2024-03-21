package com.example.mobileproject.Home.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.mobileproject.R;
import com.example.mobileproject.baseactivity.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            System.loadLibrary("DaumMapEngineApi");
        } catch (UnsatisfiedLinkError e) {
            Log.e("MainActivity", "Cannot load DaumMapEngineApi library:", e);
        }

        // 키 해시를 구하고 로그에 출력
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }

        // BottomNavigationView 구현
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment); //내부적으로 FragmentManager를 관리
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        /*BottomNavigationView와 NavController를 연결
        각 메뉴 아이템 클릭에 따라 NavController가 적절한 네비게이션 액션을 수행
        이 때 네비게이션 액션은 nav_graph.xml에 정의된 대로 수행되며, 이를 통해 각 프래그먼트로의 이동이 가능*/
    }
    // MainActivity
//    @Override
//    public void onResume() {
//        super.onResume();
//        // 활동이 재개될 때 항상 홈 프래그먼트를 선택하도록 합니다.
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
//    }

}