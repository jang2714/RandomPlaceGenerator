package com.example.mobileproject.Login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.mobileproject.R;
import com.example.mobileproject.Login.repository.FindField;
import com.example.mobileproject.Login.repository.FindFieldInterface;
import com.example.mobileproject.Login.repository.FindFiledCallback;
import com.example.mobileproject.Login.util.DialogUtil;
import com.example.mobileproject.baseactivity.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class InputIdActivity extends BaseActivity {
    private FirebaseAuth firebaseAuth; //파이어베이스 인증
    private FirebaseFirestore firestore; //firestore 데이터베이스
    private final FindFieldInterface findFieldInterface = new FindField();

    private EditText et_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_id);

        //액션바 타이틀, 뒤로가기
        setupActionBar("비밀번호 찾기", true);

        et_id = findViewById(R.id.et_id);
        ImageButton btn_ok = findViewById(R.id.btn_ok);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        //확인 버튼 클릭 시
        btn_ok.setOnClickListener(v -> {
            String strId = et_id.getText().toString();
            if (strId.isEmpty()) {
                DialogUtil.showAlertDialog(InputIdActivity.this, "비밀번호 찾기", "아이디를 입력해주세요!", null);
            } else {
                findFieldInterface.findUserByField("id", strId, "passwordHint", new FindFiledCallback() {
                    @Override
                    public void onUserFound(String foundField) {
                        Intent intent = new Intent(InputIdActivity.this, HintAnswerActivity.class);
                        intent.putExtra("id", strId);
                        intent.putExtra("pwHint", foundField);
                        startActivity(intent);
                    }
                    @Override
                    public void onUserNotFound(String errorMessage) {
                        DialogUtil.showAlertDialog(InputIdActivity.this, "비밀번호 찾기", errorMessage, null);
                        Log.d("InputIdActivity", errorMessage);
                    }
                });
            }
        });
    }
}