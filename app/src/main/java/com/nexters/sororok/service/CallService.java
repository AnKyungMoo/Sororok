package com.nexters.sororok.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.nexters.sororok.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 콜서비스
 * 전화수신시 백그라운드에서 오버레이로 발신자 정보를 표시하는 역할
 */
//public class CallService extends Service {
//
//    public  static final String EXTRA_CALL_NUMBER = "call_number";
//    protected View rootView;
//
//    @BindView(R.id.tv_call_number)
//    TextView tv_call_number;
//
//    String call_number;
//
//    WindowManager.LayoutParams params;
//    private WindowManager windowManager;
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
//        DisplayMetrics display = getApplicationContext().getResources().getDisplayMetrics();
//
//        int type = 0;
//        int width = (int)(display.widthPixels*0.9);
//
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//
//            type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
//
//        } else {
//
//            type = WindowManager.LayoutParams.TYPE_PHONE;
//
//        }
//
//        params = new WindowManager.LayoutParams(
//                width,
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                type,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
//                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
//                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
//                PixelFormat.TRANSLUCENT);
//
//        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        rootView = layoutInflater.inflate(R.layout.call_overlay, null);
//        ButterKnife.bind(this,rootView);
//
//
//    }
//
//    @Override public int onStartCommand(Intent intent, int flags, int startId) {
//        windowManager.addView(rootView, params);
//        setExtra(intent);
//
//        if (!TextUtils.isEmpty(call_number)) {
//            tv_call_number.setText(call_number);
//        }
//
//        return START_REDELIVER_INTENT;
//    }
//
//    private void setExtra(Intent intent) {
//        if (intent == null) {
//            removePopup();
//            return;
//        }
//
//        call_number = intent.getStringExtra(EXTRA_CALL_NUMBER);
//
//    }
//
//    @Override public void onDestroy() {
//
//        super.onDestroy();
//        removePopup();
//    }
//
//    @OnClick(R.id.btn_close)
//    public void removePopup() {
//        if (rootView != null && windowManager != null) windowManager.removeView(rootView);
//    }
//
//
//}
