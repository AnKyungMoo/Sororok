package com.nexters.sororok.asynctask;

import android.os.AsyncTask;

import com.nexters.sororok.model.GroupList;
import com.nexters.sororok.service.RetrofitService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class GroupListTask extends AsyncTask<Integer, Void, List<GroupList>> {
    @Override
    protected List<GroupList> doInBackground(Integer... integers) {
        RetrofitService retrofitService = RetrofitService.retrofit.create(RetrofitService.class);

        Call<List<GroupList>> listCall = retrofitService.list(integers[0]);

        try {
            return listCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
