package com.nexters.sororok.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.telecom.TelecomManager;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.util.Log;

import android.os.Handler;

/**
 * 콜브로드캐스트리시버
 * 전화 수신시 캐치하기 위한 리시버
// */
//public class CallBroadcastReceiver extends BroadcastReceiver{
//
//    public  static final String TAG = "PHONE STATE";
//    private  static String mLastState;
//
//    private final Handler mHandler = new Handler(Looper.getMainLooper());
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        Log.d(TAG,"onReceive()");
//
//        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
//        if(state.equals(mLastState)){
//            return;
//        } else {
//            mLastState = state;
//        }
//        if(TelephonyManager.EXTRA_STATE_RINGING.equals(state)){
//            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
//            final String phone_number = PhoneNumberUtils.formatNumber(incomingNumber);
//            Intent seviceIntent = new Intent(context, CallService.class);
//            seviceIntent.putExtra(CallService.EXTRA_CALL_NUMBER,phone_number);
//            context.startService(seviceIntent);
//        }
//    }
//}
