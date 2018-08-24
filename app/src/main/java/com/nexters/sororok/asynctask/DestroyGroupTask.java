package com.nexters.sororok.asynctask;

import android.os.AsyncTask;

import com.nexters.sororok.model.DestroyGroupModel;
import com.nexters.sororok.model.DestroyRequestModel;
import com.nexters.sororok.service.RetrofitService;

import java.io.IOException;

import retrofit2.Call;

public class DestroyGroupTask extends AsyncTask<DestroyRequestModel, Void, DestroyGroupModel> {
    @Override
    protected DestroyGroupModel doInBackground(DestroyRequestModel... destroyRequestModels) {
        RetrofitService retrofitService = RetrofitService.retrofit.create(RetrofitService.class);

        Call<DestroyGroupModel> destroyCall = retrofitService.destroy(destroyRequestModels[0]);

        try {
            return destroyCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
