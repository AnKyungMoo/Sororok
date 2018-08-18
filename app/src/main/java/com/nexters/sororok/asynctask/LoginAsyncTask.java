package com.nexters.sororok.asynctask;

import android.os.AsyncTask;

import com.nexters.sororok.model.LoginRequestModel;
import com.nexters.sororok.model.LoginResponseModel;
import com.nexters.sororok.service.LoginService;

import java.io.IOException;

import retrofit2.Call;

public class LoginAsyncTask extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {

        LoginService loginService = LoginService.loginRetrofit.create(LoginService.class);

        Call<LoginResponseModel> loginCall = loginService.login(new LoginRequestModel("1","899582853"));

        try {
            return loginCall.execute().body().getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
