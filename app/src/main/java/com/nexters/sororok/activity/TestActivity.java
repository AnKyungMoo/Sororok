package com.nexters.sororok.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nexters.sororok.R;

public class TestActivity extends AppCompatActivity
      implements AppBarLayout.OnOffsetChangedListener {

    private boolean mIsTheTitleVisible = false;
    private boolean mIsThePersonDetailsContainerVisible = true;

    private static final int ALPHA_ANIMATIONS_DURATION = 200;
    private static final float PERCENTAGE_TO_HIDE_PERSON_DETAILS = 0.3f;
    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;

    private TextView tvTitleToolBar;
    private AppBarLayout appBarLayout;
    private Button settingBtn, historyBtn;
    //private LinearLayout linearLayoutPersonDetails;
    private TextView line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_main);

        findViewById();

        appBarLayout.addOnOffsetChangedListener(this);

        startAlphaAnimation(tvTitleToolBar, 0, View.INVISIBLE);
    }

    private void findViewById() {
        tvTitleToolBar = (TextView) findViewById(R.id.tvTitleToolBar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        line = findViewById(R.id.linearLayout);
        settingBtn = findViewById(R.id.btn_setting_2);
        historyBtn = findViewById(R.id.btn_history_2);
      //  linearLayoutPersonDetails = (LinearLayout) findViewById(R.id.linearLayout);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        handleAlphaOnPersonDetails(percentage);
        handleToolBarTitleVisibility(percentage);
    }

    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    private void handleAlphaOnPersonDetails(float percentage) {

        if (percentage >= PERCENTAGE_TO_HIDE_PERSON_DETAILS) {
            if (mIsThePersonDetailsContainerVisible) {
                startAlphaAnimation(line, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "ttt",Toast.LENGTH_SHORT).show();
                settingBtn.setVisibility(View.VISIBLE);
                historyBtn.setVisibility(View.VISIBLE);
            //    startAlphaAnimation(linearLayoutPersonDetails, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsThePersonDetailsContainerVisible = false;
            }
        } else {
            if (!mIsThePersonDetailsContainerVisible) {
                startAlphaAnimation(line, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                settingBtn.setVisibility(View.INVISIBLE);
                historyBtn.setVisibility(View.INVISIBLE);
            //    startAlphaAnimation(linearLayoutPersonDetails, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsThePersonDetailsContainerVisible = true;
            }
        }
    }

    private void handleToolBarTitleVisibility(float percentage) {

        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(tvTitleToolBar, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(tvTitleToolBar, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }
}
