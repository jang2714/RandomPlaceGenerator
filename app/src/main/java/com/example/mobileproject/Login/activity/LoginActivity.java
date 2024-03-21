package com.example.mobileproject.Login.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mobileproject.Home.activity.MainActivity;
import com.example.mobileproject.Home.fragment.HomeFragment;
import com.example.mobileproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.mobileproject.Login.util.DialogUtil;

//로그인
public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth; //파이어베이스 인증
    private DatabaseReference databaseRef; //실시간 데이터베이스

    private EditText et_id, et_password; //회원가입 입력필드


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseRef = FirebaseDatabase.getInstance().getReference("user");

        et_id = findViewById(R.id.et_id);
        et_password = findViewById(R.id.et_password);
        ImageButton btn_login = findViewById(R.id.btn_login);
        Button btn_findId = findViewById(R.id.btn_findId);
        Button btn_findPassword = findViewById(R.id.btn_findPassword);
        Button btn_register = findViewById(R.id.btn_register);

        //로그인 버튼 클릭 시
        btn_login.setOnClickListener(v -> {
            String strId = et_id.getText().toString().trim();
            String strPw = et_password.getText().toString().trim();
            if (strId.isEmpty() || strPw.isEmpty()) {
                DialogUtil.showAlertDialog(LoginActivity.this, "로그인", "아이디 혹은 비밀번호를 입력해주세요!", null);
            } else {
                //friebase 로그인 시도
                firebaseAuth.signInWithEmailAndPassword(strId, strPw)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) { //로그인 성공 시
                                    Log.d("LoginActivity", "로그인 성공");
                                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish(); //로그인 완료 시 현재 액티비티 파괴
                                } else { //로그인 실패 시
                                    DialogUtil.showAlertDialog(LoginActivity.this, "로그인 실패", "아이디 혹은 비밀번호를 확인하여 다시 입력해주세요!", null);
                                    Log.d("LoginActivity", "로그인 실패: " + task.getException().getMessage());
                                }
                            }
                        });
            }
        });

        //회원가입 버튼 클릭 시
        btn_register.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                Log.e("LoginActivity", "회원가입 버튼 클릭 실패: " + e.toString());
            }
        });

        //아이디 찾기 버튼 클릭 시
        btn_findId.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, FindIdActivity.class);
            startActivity(intent);
        });

        //비밀번호 찾기 버튼 클릭 시
        btn_findPassword.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, InputIdActivity.class);
            startActivity(intent);
        });
    }
}