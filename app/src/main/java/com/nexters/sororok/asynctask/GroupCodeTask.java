package com.nexters.sororok.asynctask;

import android.os.AsyncTask;

import com.nexters.sororok.model.DestroyGroupModel;
import com.nexters.sororok.service.RetrofitService;

import java.io.IOException;

import retrofit2.Call;

public class GroupCodeTask extends AsyncTask<Void, Void, String> {
    @Override
    protected String doInBackground(Void... voids) {
        RetrofitService retrofitService = RetrofitService.retrofit.create(RetrofitService.class);

        Call<String> codeCall = retrofitService.code();

        try {
            return codeCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
