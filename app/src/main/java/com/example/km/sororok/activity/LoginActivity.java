package com.example.km.sororok.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.km.sororok.R;

/**
 *
 * 로그인 액티비티
 * 메인 액티비티에서 전화번호 정보가 서버에 없으면 로그인 액티비티로 이동함
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
