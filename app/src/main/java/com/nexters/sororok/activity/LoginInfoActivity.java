package com.nexters.sororok.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.nexters.sororok.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginInfoActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText phoneEditText;
    EditText emailEditText;
    ImageButton configButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_info);
        initializeView();

        Intent infoIntent = getIntent();

        String type = infoIntent.getStringExtra("loginType");

        switch (type) {
            case "google":
                break;
            case "naver":
                loginFromNaver(infoIntent);
                break;
            case "kakao":
                break;
            default:
                break;
        }

        setPhoneNumber();

        configButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginInfoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initializeView() {
        nameEditText = findViewById(R.id.edit_login_name);
        phoneEditText = findViewById(R.id.edit_login_phone);
        emailEditText = findViewById(R.id.edit_login_email);
        configButton = findViewById(R.id.login_config_button);
    }

    private void loginFromNaver(Intent info) {
        try {
            JSONObject naverJson = new JSONObject(info.getStringExtra("naverToken"));

            JSONObject naverResponseJson = naverJson.getJSONObject("response");

            String name = naverResponseJson.getString("name");

            String email = naverResponseJson.getString("email");

            nameEditText.setText(name);

            emailEditText.setText(email);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    private void setPhoneNumber() {
        // 기기 번호 가져와서 EditText에 적용
        String phoneNumber = null;
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        assert telephonyManager != null;

        /*TODO: SuppressLint를 제거하고 permission check로 바꾸자*/
        phoneNumber = telephonyManager.getLine1Number();
        phoneNumber = phoneNumber.replace("+82", "0");

        phoneEditText.setText(phoneNumber);
    }
}
