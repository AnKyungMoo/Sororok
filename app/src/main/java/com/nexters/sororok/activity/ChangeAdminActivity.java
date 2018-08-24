package com.nexters.sororok.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nexters.sororok.R;

public class ChangeAdminActivity extends AppCompatActivity {

    private Button updateBtn,backBtn;
    private CustomDialog customDialog;
    private ImageView ivChange;
    private int groupid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_admin);
        initComponent();

        ivChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangeAdminActivity.this,ChangeBossActivity.class);
                intent.putExtra("groupid",groupid);
                startActivity(intent);
                finish();
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog = new CustomDialog(ChangeAdminActivity.this);
                customDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAni;
                customDialog.show();
                customDialog.setTitle("그룹 관리자를 변경하시겠습니까?");
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void initComponent(){
        updateBtn = findViewById(R.id.btn_admin_update);
        backBtn = findViewById(R.id.btn_back);
        ivChange = findViewById(R.id.img_new_admin);
    }
}
