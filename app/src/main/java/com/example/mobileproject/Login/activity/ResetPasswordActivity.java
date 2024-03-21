package com.example.mobileproject.Login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileproject.R;
import com.example.mobileproject.Login.repository.DocumentWrite;
import com.example.mobileproject.Login.repository.DocumentWriteInterface;
import com.example.mobileproject.Login.util.DialogUtil;
import com.example.mobileproject.baseactivity.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class ResetPasswordActivity extends BaseActivity {
    private FirebaseAuth firebaseAuth; //파이어베이스 인증
    private FirebaseFirestore firestore; //firestore 데이터베이스
    private DocumentWriteInterface documentWriter;

    private TextView tv_id;
    private EditText et_newPassword, et_newPasswordCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        //액션바 타이틀, 뒤로가기
        setupActionBar("비밀번호 찾기", true);

        tv_id = findViewById(R.id.tv_id);
        et_newPassword = findViewById(R.id.et_newPassword);
        et_newPasswordCheck = findViewById(R.id.et_newPasswordCheck);
        ImageButton btn_ok = findViewById(R.id.btn_ok);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        String uid = currentUser.getUid();

        // Intent에서 전달된 ID 값 받기
        Intent intent = getIntent();
        String foundId = intent.getStringExtra("id");
        tv_id.setText(foundId);

        documentWriter = new DocumentWrite(firestore);

        // 확인 버튼 클릭 시
        btn_ok.setOnClickListener(v -> {
            String newPassword = et_newPassword.getText().toString().trim();
            String newPasswordCheck = et_newPasswordCheck.getText().toString().trim();

            if (newPassword.isEmpty() || newPasswordCheck.isEmpty()) {
                Toast.makeText(ResetPasswordActivity.this, "새 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            } else if (!newPassword.equals(newPasswordCheck)) {
                Toast.makeText(ResetPasswordActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            } else {
                // Firebase Authentication 비밀번호 업데이트
                documentWriter.updateDocumentField("user", uid, "password", newPassword)
                        .addOnSuccessListener(aVoid -> {
                            DialogUtil.showAlertDialog(ResetPasswordActivity.this, "비밀번호 찾기", "비밀번호가 업데이트되었습니다.", (dialog, which)-> {
                                Intent ResetPasswordActivityIntent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                                startActivity(ResetPasswordActivityIntent);
                                finish(); // 현재 액티비티 종료
                                dialog.dismiss(); // 대화상자 닫기
                            });
                        })
                        .addOnFailureListener(e -> {
                            DialogUtil.showAlertDialog(ResetPasswordActivity.this, "비밀번호 찾기", "비밀번호 업데이트에 실패했습니다.", null);
                            Log.d("ResetPasswordActivity", "Error updating password: " + e.getMessage());
                        });
            }
        });
/*
        // 뒤로가기 버튼 클릭 시
        btn_back.setOnClickListener(v -> onBackPressed());*/
    }
}