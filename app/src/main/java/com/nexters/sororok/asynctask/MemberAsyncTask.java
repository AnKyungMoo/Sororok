package com.nexters.sororok.asynctask;

import android.os.AsyncTask;

import com.nexters.sororok.model.LoginResponseModel;
import com.nexters.sororok.model.LoginUserInfo;
import com.nexters.sororok.model.Member;
import com.nexters.sororok.model.MemberResponseModel;
import com.nexters.sororok.service.RetrofitService;

import java.io.IOException;

import retrofit2.Call;

public class MemberAsyncTask extends AsyncTask<Integer,Void,MemberResponseModel> {
    @Override
    protected MemberResponseModel doInBackground(Integer... integers) {
        RetrofitService retrofitService = RetrofitService.retrofit.create(RetrofitService.class);

        Call<MemberResponseModel> infoCall = retrofitService.listup(integers[0]);

        try {
            return infoCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
