package com.nexters.sororok.asynctask;

import android.os.AsyncTask;

import com.nexters.sororok.model.GroupResponseModel;
import com.nexters.sororok.service.RetrofitService;

import java.io.IOException;

import retrofit2.Call;

public class CreateGroupTask extends AsyncTask<Object, Void, GroupResponseModel> {
    @Override
    protected GroupResponseModel doInBackground(Object... objects) {
        RetrofitService retrofitService = RetrofitService.retrofit.create(RetrofitService.class);

        Call<GroupResponseModel> groupCall = retrofitService.create(objects[0].toString(),
                objects[1].toString(),
                (int)objects[2],
                objects[3].toString()
        );

        try {
            return groupCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
