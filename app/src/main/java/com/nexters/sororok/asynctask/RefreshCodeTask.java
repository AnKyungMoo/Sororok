package com.nexters.sororok.asynctask;

import android.os.AsyncTask;

import com.nexters.sororok.model.RefreshCodeModel;
import com.nexters.sororok.model.UpdateCodeModel;
import com.nexters.sororok.service.RetrofitService;

import java.io.IOException;

import retrofit2.Call;

public class RefreshCodeTask extends AsyncTask<UpdateCodeModel, Void, RefreshCodeModel> {
    @Override
    protected RefreshCodeModel doInBackground(UpdateCodeModel... updateCodeModels) {
        RetrofitService retrofitService = RetrofitService.retrofit.create(RetrofitService.class);

        Call<RefreshCodeModel> refreshCall = retrofitService.update(updateCodeModels[0]);

        try {
            return refreshCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
