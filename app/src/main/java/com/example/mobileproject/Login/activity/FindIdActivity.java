package com.example.mobileproject.Login.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mobileproject.R;
import com.example.mobileproject.Login.repository.FindField;
import com.example.mobileproject.Login.repository.FindFieldInterface;
import com.example.mobileproject.Login.repository.FindFiledCallback;
import com.example.mobileproject.Login.util.DialogUtil;
import com.example.mobileproject.baseactivity.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class FindIdActivity extends BaseActivity {
    private FirebaseAuth firebaseAuth; //파이어베이스 인증
    private FirebaseFirestore firestore; //firestore 데이터베이스
    private EditText et_phoneNumber;
    private TextView tv_content;

    private final FindFieldInterface findFieldInterface = new FindField();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);

        //액션바 타이틀, 뒤로가기
        setupActionBar("아이디 찾기", true);

        tv_content = findViewById(R.id.tv_content);
        et_phoneNumber = findViewById(R.id.et_phoneNumber);
        ImageButton btn_findId = findViewById(R.id.btn_findId);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        //아이디 찾기 버튼 클릭 시
        btn_findId.setOnClickListener(v->{
            //Firestore에서 등록된 핸드폰번호를 확인하여 가입 여부 확인
            String strPhoneNumber = et_phoneNumber.getText().toString();
            if (strPhoneNumber.isEmpty()) {
                DialogUtil.showAlertDialog(FindIdActivity.this, "아이디 찾기", "휴대폰 번호를 입력해주세요!", null);
            } else {
                findFieldInterface.findUserByField("phoneNumber", strPhoneNumber, "id", new FindFiledCallback() {
                    @Override
                    public void onUserFound(String foundField) {
                        Intent intent = new Intent(FindIdActivity.this, FindIdResultActivity.class);
                        intent.putExtra("id", foundField);
                        startActivity(intent);
                    }
                    @Override
                    public void onUserNotFound(String errorMessage) {
                        DialogUtil.showAlertDialog(FindIdActivity.this, "아이디 찾기", "해당하는 휴대폰 번호가 없습니다.", null);
                        Log.d("FindIdActivity", "Failed to find ID: " + errorMessage);
                    }
                });
            }
        });
    }
}