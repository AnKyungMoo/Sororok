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

import com.nexters.sororok.R;

public class SettingActivity extends AppCompatActivity{

    private Button backBtn,removeInfoBtn;
    private Animation slideUpAnimation, slideDownAnimation;
    private RelativeLayout layoutMain, relativeLayoutDown;
    //private LinearLayout linearLayout;

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
                relativeLayoutDown.startAnimation(slideUpAnimation);
                layoutMain.setBackgroundColor(Color.argb(80,50,50,50));
               // relativeLayoutUp.setBackgroundColor("#8C505050");
                relativeLayoutDown.setVisibility(View.VISIBLE);
            }
        });
        layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(), "눌림",Toast.LENGTH_LONG).show();
                relativeLayoutDown.startAnimation(slideDownAnimation);
                layoutMain.setBackgroundColor(Color.rgb(0,0,0));
                relativeLayoutDown.setVisibility(View.INVISIBLE);
            }
        });
       /* relativeLayoutUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayoutDown.startAnimation(slideDownAnimation);
                relativeLayoutDown.setVisibility(View.INVISIBLE);
            }
        });*/
    }

    public void initComponent(){
        backBtn = findViewById(R.id.btn_back);
        removeInfoBtn = findViewById(R.id.btn_remove_info);
      //  relativeLayoutUp = findViewById(R.id.layout_when_click_remove_info);
        relativeLayoutDown = findViewById(R.id.layout_animation);
        layoutMain = findViewById(R.id.layout_main);
       // linearLayout = findViewById(R.id.layout_slide_down);
        slideUpAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up_animation);
       // slideDownAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down_animation);
    }
}
