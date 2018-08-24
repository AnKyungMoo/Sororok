package com.nexters.sororok.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.nexters.sororok.R;

public class SettingActivity extends AppCompatActivity{

    private Button backBtn,removeInfoBtn,updateBtn,qnaBtn,logoutBtn;
    private Switch noticeSwitch;
    private Animation slideUpAnimation, slideDownAnimation;
    private RelativeLayout layoutMain, layoutMove;
    private CustomDialog customDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initComponent();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        removeInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutMove.startAnimation(slideUpAnimation);
                layoutMain.setBackgroundColor(Color.argb(80,50,50,50));
                layoutMove.setVisibility(View.VISIBLE);
               // offBtn();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog = new CustomDialog(SettingActivity.this);
                customDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAni;
                customDialog.show();
                customDialog.setTitle("로그아웃 하시겠습니까?");

            }
        });
        layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(layoutMove.getVisibility()==View.VISIBLE){
                    layoutMove.startAnimation(slideDownAnimation);
                    layoutMain.setBackgroundColor(Color.rgb(255,255,255));
                    layoutMove.setVisibility(View.INVISIBLE);
                  //  onBtn();
                }
            }
        });

    }

    public void initComponent(){
        backBtn = findViewById(R.id.btn_back);
        removeInfoBtn = findViewById(R.id.btn_remove_info);
        layoutMove = findViewById(R.id.layout_animation);
        layoutMain = findViewById(R.id.layout_main);
        updateBtn = findViewById(R.id.btn_update);
        updateBtn = findViewById(R.id.btn_update);
        qnaBtn = findViewById(R.id.btn_develop);
        logoutBtn = findViewById(R.id.btn_logout);
        slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up_animation);
        slideDownAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down_animation);
    }

    public void onBtn(){
        removeInfoBtn.setEnabled(false);
        updateBtn.setEnabled(false);
        noticeSwitch.setEnabled(false);
        qnaBtn.setEnabled(false);
        logoutBtn.setEnabled(false);
    }

    public void offBtn(){
        removeInfoBtn.setEnabled(true);
        updateBtn.setEnabled(true);
        noticeSwitch.setEnabled(true);
        qnaBtn.setEnabled(true);
        logoutBtn.setEnabled(true);
    }
}

