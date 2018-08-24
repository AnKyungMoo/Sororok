package com.nexters.sororok.asynctask;

import android.os.AsyncTask;

import com.nexters.sororok.model.MemberInfo;
import com.nexters.sororok.service.RetrofitService;

import java.io.IOException;

import retrofit2.Call;

public class MemberInfoTask extends AsyncTask<Integer, Void, MemberInfo> {
    @Override
    protected MemberInfo doInBackground(Integer... integers) {
        RetrofitService retrofitService = RetrofitService.retrofit.create(RetrofitService.class);

        Call<MemberInfo> memberInfoCall = retrofitService.memberInfo(integers[0]);

        try {
            return memberInfoCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
