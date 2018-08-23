package com.nexters.sororok.asynctask;

import android.os.AsyncTask;

import com.nexters.sororok.model.LoginResponseModel;
import com.nexters.sororok.model.LoginUserInfo;
import com.nexters.sororok.service.RetrofitService;

import java.io.IOException;
import retrofit2.Call;

public class LoginInfoTask extends AsyncTask<LoginUserInfo, Void, LoginResponseModel> {

    @Override
    protected LoginResponseModel doInBackground(LoginUserInfo... loginUserInfos) {
        RetrofitService retrofitService = RetrofitService.retrofit.create(RetrofitService.class);

        Call<LoginResponseModel> infoCall = retrofitService.signUp(loginUserInfos[0].getPhone(),
                loginUserInfos[0].getName(),
                loginUserInfos[0].getEmail(),
                loginUserInfos[0].getLoginType(),
                loginUserInfos[0].getLoginUid()
        );

        try {
            return infoCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
