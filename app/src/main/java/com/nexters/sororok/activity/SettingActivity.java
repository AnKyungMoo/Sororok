package com.nexters.sororok.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nexters.sororok.R;

public class SettingActivity extends AppCompatActivity{

    private Button backBtn,removeInfoBtn;
    private Animation slideUpAnimation, slideDownAnimation;
    private RelativeLayout relativeLayout;
    private LinearLayout linearLayout;

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
                relativeLayout.startAnimation(slideUpAnimation);
                relativeLayout.setVisibility(View.VISIBLE);
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout.startAnimation(slideDownAnimation);
                relativeLayout.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void initComponent(){
        backBtn = findViewById(R.id.btn_back);
        removeInfoBtn = findViewById(R.id.btn_remove_info);
        relativeLayout = findViewById(R.id.layout_when_click_remove_info);
        linearLayout = findViewById(R.id.layout_slide_down);
        slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up_animation);
        slideDownAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down_animation);
    }
}
