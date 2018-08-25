package com.nexters.sororok.asynctask;

import android.os.AsyncTask;

import com.nexters.sororok.model.LoginResponseModel;
import com.nexters.sororok.model.LoginUserInfo;
import com.nexters.sororok.model.Member;
import com.nexters.sororok.model.MemberResponseModel;
import com.nexters.sororok.service.RetrofitService;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;

public class MemberAsyncTask extends AsyncTask<Integer, Void, ArrayList<MemberResponseModel>> {
    @Override
    protected ArrayList<MemberResponseModel> doInBackground(Integer... integers) {
        RetrofitService retrofitService = RetrofitService.retrofit.create(RetrofitService.class);

        Call<ArrayList<MemberResponseModel>> memberCall = retrofitService.listup(integers[0]);

        try {
            return memberCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
