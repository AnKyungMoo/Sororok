package com.nexters.sororok.asynctask;

import android.os.AsyncTask;

import com.nexters.sororok.model.LoginRequestModel;
import com.nexters.sororok.model.LoginResponseModel;
import com.nexters.sororok.service.RetrofitService;

import java.io.IOException;

import retrofit2.Call;

public class LoginAsyncTask extends AsyncTask<LoginRequestModel, Void, LoginResponseModel> {

    @Override
    protected LoginResponseModel doInBackground(LoginRequestModel... loginRequestModels) {

        RetrofitService retrofitService = RetrofitService.retrofit.create(RetrofitService.class);

        Call<LoginResponseModel> loginCall = retrofitService.login(loginRequestModels[0]);

        try {
            return loginCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
