package com.nexters.sororok.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

/**
 * 콜서비스
 * 전화수신시 백그라운드에서 오버레이로 발신자 정보를 표시하는 역할
 */
public class CallService extends Service {

    public  static final String EXTRA_CALL_NUMBER = "call_number";
    protected View rootView;

    TextView tv_call_number;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
