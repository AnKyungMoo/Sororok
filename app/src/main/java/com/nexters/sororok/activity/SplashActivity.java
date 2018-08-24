package com.nexters.sororok.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.nexters.sororok.R;
import com.nexters.sororok.util.PermissionUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 스플래시 액티비티
 * 권한 확인 후 메인 액티비티로 이동함
 */
public class SplashActivity extends AppCompatActivity {

    static String localId = "-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // id값이 로컬에 저장되어있다면 메인으로 바로 이동
        SharedPreferences idPreference = getSharedPreferences("idPreference", MODE_PRIVATE);
        localId = idPreference.getString("id", "-1");

        Log.d("AKMID", localId);

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.nexters.sororok", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //권한 체크
        if (PermissionUtil.checkPermission(this, Manifest.permission.WRITE_CONTACTS) &&
                PermissionUtil.checkPermission(this, Manifest.permission.READ_CONTACTS) &&
                PermissionUtil.checkPermission(this, Manifest.permission.READ_PHONE_STATE) &&
                PermissionUtil.checkPermission(this, Manifest.permission.CAMERA) &&
                PermissionUtil.checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) &&
                PermissionUtil.checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
                PermissionUtil.checkPermission(this, Manifest.permission.CALL_PHONE)) {


            //권한 있으면 메인으로 이동
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!localId.equals("-1")) {
                        Log.d("호출", "ㅁ");
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Intent mainIntent = new Intent(SplashActivity.this, TestActivity.class);
                        SplashActivity.this.startActivity(mainIntent);
                        SplashActivity.this.finish();
                    }
                }
            }, 2000);
        }


        //없으면 권한 요청
        else {
            PermissionUtil.requestExternalPermissions(this);

        }
    }


    //권한 요청 이후
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PermissionUtil.REQUEST_PERMISSION) {
            if (PermissionUtil.verifyPermission(grantResults)) {

                //권한 획득 성공시 메인 이동
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!localId.equals("-1")) {
                            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Intent mainIntent = new Intent(SplashActivity.this, TestActivity.class);
                            SplashActivity.this.startActivity(mainIntent);
                            SplashActivity.this.finish();
                        }
                    }
                }, 2000);
            } else {
                //권한 획득 실패시 앱 종료
                SplashActivity.this.finish();
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}
