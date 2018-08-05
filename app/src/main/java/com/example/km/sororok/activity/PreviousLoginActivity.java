package com.example.km.sororok.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.km.sororok.R;

/**
 *
 * 로그인 액티비티
 * 메인 액티비티에서 전화번호 정보가 서버에 없으면 로그인 액티비티로 이동함
 */
public class PreviousLoginActivity extends AppCompatActivity {

    EditText phoneEditText;
    ImageButton xButton;
    Button loginButton;

    // 권한 체크하는거로 바꾸자
    @SuppressLint({"MissingPermission", "HardwareIds"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_previous);

        initializeViewField();

        // 기기 번호 가져와서 EditText에 적용
        String phoneNumber = null;
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        assert telephonyManager != null;

        phoneNumber = telephonyManager.getLine1Number();
        phoneNumber = phoneNumber.replace("+82", "0");

        phoneEditText.setText(phoneNumber);

        // EditText에 Focus가 들어가면 x버튼이 사라짐
        phoneEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean isFocus) {
                if (isFocus)
                {
                    xButton.setVisibility(View.GONE);
                }
                else
                {
                    xButton.setVisibility(View.VISIBLE);
                }
            }
        });

        // x 버튼 클릭하면 EditText를 비우고 Focus를 EditText에 맞춤
        xButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneEditText.setText("");
                phoneEditText.setFocusableInTouchMode(true);
                phoneEditText.requestFocus();
            }
        });

        // 로그인 버튼 클릭
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*TODO: 로그인 정보를 다음 Activity로 넘기기*/
                Intent mainIntent = new Intent(PreviousLoginActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }

    private void initializeViewField() {
        phoneEditText = (EditText) findViewById(R.id.etMainPhone);
        // 010-1234-5678의 형태를 유지하게 해줌
        phoneEditText.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        xButton = (ImageButton) findViewById(R.id.xButton);
        loginButton = (Button) findViewById(R.id.loginButton);
    }
}
