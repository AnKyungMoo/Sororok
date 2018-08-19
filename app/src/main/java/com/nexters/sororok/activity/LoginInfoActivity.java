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
import com.nexters.sororok.asynctask.LoginInfoTask;
import com.nexters.sororok.model.LoginResponseModel;
import com.nexters.sororok.model.LoginUserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoginInfoActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText phoneEditText;
    EditText emailEditText;
    ImageButton configButton;

    String loginType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_info);
        initializeView();

        Intent infoIntent = getIntent();

        String type = infoIntent.getStringExtra("loginType");

        /*
        * UID 가져오는데 네이버는 Token으로 가져오기 때문에 네이버에서 토큰 가져오는걸
        * 로그인할 때 미리 풀어서 정보만 가져오는걸로 바꾸자
        * */

        switch (type) {
            case "google":
                loginType = "0";
                loginFromGoogle(infoIntent);
                break;
            case "naver":
                loginType = "2";
                loginFromNaver(infoIntent);
                break;
            case "kakao":
                loginType = "1";
                loginFromKakao(infoIntent);
                break;
            default:
                break;
        }

        setPhoneNumber();

        configButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLoginInfoToServer();

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

    private void loginFromGoogle(Intent info) {
        String name = info.getStringExtra("googleName");
        String email = info.getStringExtra("googleEmail");

        nameEditText.setText(name);
        emailEditText.setText(email);
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

    private void loginFromKakao(Intent info) {
        String name = info.getStringExtra("kakaoName");
        nameEditText.setText(name);
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

    private void sendLoginInfoToServer() {
        /* TODO: UID 받아와서 적용하자 */
        LoginUserInfo loginUserInfo = new LoginUserInfo(phoneEditText.getText().toString(),
                nameEditText.getText().toString(),
                emailEditText.getText().toString(),
                loginType,
                "test"
        );

        LoginInfoTask loginInfoTask = new LoginInfoTask();

        loginInfoTask.execute(loginUserInfo);

        try {
            LoginResponseModel responseModel = loginInfoTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
