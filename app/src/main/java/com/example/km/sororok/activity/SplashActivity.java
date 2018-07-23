package com.example.km.sororok.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.example.km.sororok.R;
import com.example.km.sororok.util.PermissionUtil;

/**
 * 스플래시 액티비티
 * 권한 확인 후 메인 액티비티로 이동함
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //권한 체크
        if(PermissionUtil.checkPermission(this, Manifest.permission.WRITE_CONTACTS)&&
                PermissionUtil.checkPermission(this, Manifest.permission.READ_CONTACTS)&&
                PermissionUtil.checkPermission(this,Manifest.permission.READ_PHONE_STATE)&&
                PermissionUtil.checkPermission(this,Manifest.permission.CAMERA)&&
                PermissionUtil.checkPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)&&
                PermissionUtil.checkPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)&&
                PermissionUtil.checkPermission(this,Manifest.permission.CALL_PHONE)){


            //권한 있으면 메인으로 이동
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }
            }, 2000);
        }


        //없으면 권한 요청
        else{
            PermissionUtil.requestExternalPermissions(this);

        }

    }


    //권한 요청 이후
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if(requestCode == PermissionUtil.REQUEST_PERMISSION){
            if(PermissionUtil.verifyPermission(grantResults)){

                //권한 획득 성공시 메인 이동
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
                        SplashActivity.this.startActivity(mainIntent);
                        SplashActivity.this.finish();
                    }
                }, 2000);
            } else {
                //권한 획득 실패시 앱 종료
                SplashActivity.this.finish();
            }

            } else {
                super.onRequestPermissionsResult(requestCode,permissions,grantResults);
            }

        }
    }
