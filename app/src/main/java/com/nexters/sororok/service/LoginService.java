package com.nexters.sororok.service;

import com.nexters.sororok.model.LoginRequestModel;
import com.nexters.sororok.model.LoginResponseModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    String BASE_URL = "http://45.63.120.140:40005/";

    Retrofit loginRetrofit = new Retrofit.Builder()
            .baseUrl(LoginService.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @POST("member/login")
    Call<LoginResponseModel> login(@Body LoginRequestModel body);
}
