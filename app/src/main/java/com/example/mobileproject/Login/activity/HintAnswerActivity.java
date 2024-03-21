package com.example.mobileproject.Login.activity;


import android.content.Intent;
import android.os.Bundle;
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

public class HintAnswerActivity extends BaseActivity {
    private FirebaseAuth firebaseAuth; //파이어베이스 인증
    private FirebaseFirestore firestore; //firestore 데이터베이스
    private final FindFieldInterface findFieldInterface = new FindField();

    private EditText et_hintAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint_answer);

        //액션바 타이틀, 뒤로가기
        setupActionBar("비밀번호 찾기", true);

        et_hintAnswer = findViewById(R.id.et_hintAnswer);
        ImageButton btn_ok = findViewById(R.id.btn_ok);
        TextView tv_id = findViewById(R.id.tv_id);
        TextView tv_hint = findViewById(R.id.tv_hint);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        // InputIdActivity에서 전달된 ID값 받기
        Intent intent = getIntent();
        String foundId = intent.getStringExtra("id");
        String foundPwHint = intent.getStringExtra("pwHint");

        tv_id.setText(foundId);
        tv_hint.setText(foundPwHint);
/*
        // 뒤로가기 버튼 클릭 시 - 이전 화면인 InputIdActivity로 돌아감
        btn_back.setOnClickListener(v -> onBackPressed());*/

        // 확인 버튼 클릭 시
        btn_ok.setOnClickListener(v -> {
            String hintAnswer = et_hintAnswer.getText().toString();
            findFieldInterface.findUserByField("passwordHint", foundPwHint, "passwordHintAnswer", new FindFiledCallback() {
                @Override
                public void onUserFound(String foundField) {
                    if (foundField.equals(hintAnswer)) {
                        Intent resetPasswordIntent = new Intent(HintAnswerActivity.this, ResetPasswordActivity.class);
                        resetPasswordIntent.putExtra("id", foundId);
                        startActivity(resetPasswordIntent);
                        finish(); // 현재 액티비티 종료
                    }
                }
                @Override
                public void onUserNotFound(String errorMessage) {
                    // 힌트 답변이 일치하지 않는 경우
                    //TODO 안 뜸. 고치기
                    DialogUtil.showAlertDialog(HintAnswerActivity.this, "비밀번호 찾기", "힌트 답변이 일치하지 않습니다!", null);
                }
            });



        });
    }
}