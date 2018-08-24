package com.nexters.sororok.asynctask;

import android.os.AsyncTask;

import com.nexters.sororok.model.JoinRepositoryRequestModel;
import com.nexters.sororok.model.JoinRepositoryResponseModel;
import com.nexters.sororok.service.RetrofitService;

import java.io.IOException;

import retrofit2.Call;

public class JoinRepositoryTask extends AsyncTask<JoinRepositoryRequestModel, Void, JoinRepositoryResponseModel> {
    @Override
    protected JoinRepositoryResponseModel doInBackground(JoinRepositoryRequestModel... joinRepositoryRequestModels) {
        RetrofitService retrofitService = RetrofitService.retrofit.create(RetrofitService.class);

        Call<JoinRepositoryResponseModel> joinRepositoryResponseModelCall = retrofitService.repositoryJoin(joinRepositoryRequestModels[0]);

        try {
            return joinRepositoryResponseModelCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
