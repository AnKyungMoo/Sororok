package com.example.km.sororok.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.km.sororok.R;

/**
 * 멤버리스트 액티비티
 * 그룹을 누르면 이 액티비티에서 멤버 명단이 뜸
 * 아 개어렵겠다
 */
public class MemberListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
    }
}
