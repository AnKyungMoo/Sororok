package com.nexters.sororok.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.nexters.sororok.model.GroupResponseModel;
import com.nexters.sororok.service.RetrofitService;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class CreateGroupTask extends AsyncTask<Object, Void, GroupResponseModel> {
    @Override
    protected GroupResponseModel doInBackground(Object... objects) {
        RetrofitService retrofitService = RetrofitService.retrofit.create(RetrofitService.class);
        Call<GroupResponseModel> groupCall;

        if (objects[4] == null) {
            groupCall = retrofitService.create(objects[0].toString(),
                    objects[1].toString(),
                    (int) objects[2],
                    objects[3].toString()
            );
        }
        else {
            File file = new File(objects[4].toString());

            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);

            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("repositoryImage", file.getName(), requestFile);

            groupCall = retrofitService.create(objects[0].toString(),
                    objects[1].toString(),
                    (int) objects[2],
                    objects[3].toString(),
                    body
            );
        }

        try {
            return groupCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
