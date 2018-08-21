package com.nexters.sororok.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nexters.sororok.R;
import com.nexters.sororok.asynctask.LoginInfoTask;
import com.nexters.sororok.model.LoginResponseModel;
import com.nexters.sororok.model.LoginUserInfo;
import com.nexters.sororok.service.LoginService;

import java.io.File;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInfoActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText phoneEditText;
    EditText emailEditText;
    ImageButton configButton;
    ImageView userImage;

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

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserGalleryActivity.class);
                startActivityForResult(intent,300);
            }
        });

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
        userImage = findViewById(R.id.login_user_image);
        // 가져온 이미지를 원래 이미지의 모양(원)으로 만듦
        userImage.setBackground(new ShapeDrawable(new OvalShape()));
        if(Build.VERSION.SDK_INT >= 21) {
            userImage.setClipToOutline(true);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch (requestCode){
                // 300은 갤러리에서 사진 선택하고 난 뒤의 경우
                case 300:
                    String photoPath = data.getExtras().getString("photo_path");
                    Glide.with(this).load(photoPath).into(userImage);
                    break;
            }
        }
    }
}
