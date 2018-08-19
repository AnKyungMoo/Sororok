package com.nexters.sororok.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.concurrent.ExecutionException;

public class LoginInfoActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText phoneEditText;
    EditText emailEditText;
    ImageButton configButton;

    String loginType;
    String name;
    String email;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_info);
        initializeView();

        Intent infoIntent = getIntent();

        /* loginType
         * google = "0"
         * kakao = "1"
         * naver = "2"
         * */
        loginType = infoIntent.getStringExtra("loginType");
        id = infoIntent.getStringExtra("loginUid");
        name = infoIntent.getStringExtra("name");
        email = infoIntent.getStringExtra("email");

        Log.d("aaa", loginType);
        Log.d("bbb", id);
        Log.d("ccc", name);
        Log.d("asdf", email);

        nameEditText.setText(name);
        emailEditText.setText(email);

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
        LoginUserInfo loginUserInfo = new LoginUserInfo(phoneEditText.getText().toString(),
                nameEditText.getText().toString(),
                emailEditText.getText().toString(),
                loginType,
                id
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
