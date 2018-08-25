package com.nexters.sororok.asynctask;

import android.os.AsyncTask;

import com.nexters.sororok.model.SearchResponseModel;
import com.nexters.sororok.service.RetrofitService;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;

public class SearchAsyncTask extends AsyncTask<Object, Void, ArrayList<SearchResponseModel>> {
    @Override
    protected ArrayList<SearchResponseModel> doInBackground(Object... objects) {
        RetrofitService retrofitService = RetrofitService.retrofit.create(RetrofitService.class);

        Call<ArrayList<SearchResponseModel>> searchResponseModelCall = retrofitService.repositorySearch(
                objects[0].toString(),
                (int)objects[1]
        );

        try {
            return searchResponseModelCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
