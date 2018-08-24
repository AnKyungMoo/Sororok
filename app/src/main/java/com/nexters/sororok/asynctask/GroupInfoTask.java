package com.nexters.sororok.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.nexters.sororok.model.GroupInfoModel;
import com.nexters.sororok.service.RetrofitService;

import java.io.IOException;

import retrofit2.Call;

public class GroupInfoTask extends AsyncTask<Integer, Void, GroupInfoModel> {
    @Override
    protected GroupInfoModel doInBackground(Integer... integers) {
        RetrofitService retrofitService = RetrofitService.retrofit.create(RetrofitService.class);

        Call<GroupInfoModel> groupInfoModelCall = retrofitService.info(integers[0]);

        try {
            return groupInfoModelCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
