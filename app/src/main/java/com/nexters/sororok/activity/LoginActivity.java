package com.nexters.sororok.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nexters.sororok.R;

public class LoginActivity extends AppCompatActivity{

    Button kakaoBtn,googleBtn,naverBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        naverBtn = findViewById(R.id.btn_naver_login);
        googleBtn = findViewById(R.id.btn_google_login);
        kakaoBtn = findViewById(R.id.btn_kakao_login);

        kakaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
