package com.nexters.sororok.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nexters.sororok.R;
import com.nexters.sororok.asynctask.LoginInfoTask;
import com.nexters.sororok.model.LoginResponseModel;
import com.nexters.sororok.model.LoginUserInfo;
import com.nexters.sororok.service.RetrofitService;

import java.io.File;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInfoActivity extends AppCompatActivity {

    ImageButton backButton;
    EditText nameEditText;
    EditText phoneEditText;
    EditText emailEditText;
    TextView configButton;
    ImageView userImage;

    String TAG = LoginInfoActivity.class.getSimpleName();
    String loginType;
    String name;
    String email;
    String id;
    String photoPath = null;
    String imageUrl;

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
        imageUrl = infoIntent.getStringExtra("imageUrl");

        Log.d("aaa", loginType);
        Log.d("bbb", id);
        Log.d("ccc", name);
        Log.d("asdf", email);
        Log.d("imageUrl", imageUrl);

        if (!imageUrl.equals(""))
            Glide.with(this).load(imageUrl).into(userImage);

        nameEditText.setText(name);
        emailEditText.setText(email);

        setPhoneNumber();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginInfoActivity.this, LoginActivity.class);
                startActivity(intent);
                //overridePendingTransition(android.R.anim.fade_out, android.R.anim.slide_out_right);
                finish();
            }
        });

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

                if (photoPath != null)
                    sendImageFile();
                else if (!imageUrl.equals(""))
                    sendImageUrl();
                else
                    sendLoginInfoToServer();

                Intent intent = new Intent(LoginInfoActivity.this, TestActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initializeView() {
        backButton = findViewById(R.id.info_back_button);
        nameEditText = findViewById(R.id.edit_login_name);
        phoneEditText = findViewById(R.id.edit_login_phone);
        phoneEditText.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
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

    // 필수 정보만 보내기
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
            Log.d(TAG, "sendLoginInfoToServerSuccess");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    // imageUrl 포함 정보 보내기
    private void sendImageUrl() {
        RetrofitService retrofitService = RetrofitService.retrofit.create(RetrofitService.class);

        Call<LoginResponseModel> resultCall = retrofitService.signUp(phoneEditText.getText().toString(),
                nameEditText.getText().toString(),
                emailEditText.getText().toString(),
                loginType,
                id,
                imageUrl
        );

        resultCall.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {

                // Response Success or Fail
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponseSuccess");
                } else {
                    Log.d(TAG, "onResponseFail");
                }

                /**
                 * Update Views
                 */
                imageUrl = null;
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Log.d(TAG, "onFailure");
            }
        });
    }

    // 이미지 파일 포함 업로드
    private void sendImageFile() {
        //Create Upload Server Client
        RetrofitService retrofitService = RetrofitService.retrofit.create(RetrofitService.class);

        //File creating from selected URL
        File file = new File(photoPath);

        Log.d("경로", photoPath);
        Log.d("파일", file.getName());

        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("memberImage", file.getName(), requestFile);

        Call<LoginResponseModel> resultCall = retrofitService.signUp(phoneEditText.getText().toString(),
                nameEditText.getText().toString(),
                emailEditText.getText().toString(),
                loginType,
                id,
                body
        );


        resultCall.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {

                // Response Success or Fail
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponseSuccess");

                    SharedPreferences pref = getSharedPreferences("idPreference", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("id", response.body() != null ? response.body().getId() : "-1");
                    editor.apply();

                } else {
                    Log.d(TAG, "onResponseFail");
                }

                /**
                 * Update Views
                 */
                photoPath = null;
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                Log.d(TAG, "onFailure");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch (requestCode){
                // 300은 갤러리에서 사진 선택하고 난 뒤의 경우
                case 300:
                    photoPath = data.getExtras().getString("photo_path");
                    Glide.with(this).load(photoPath).into(userImage);
                    break;
            }
        }
    }
}
