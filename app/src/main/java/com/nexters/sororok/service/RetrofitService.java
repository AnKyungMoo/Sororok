package com.nexters.sororok.service;

import com.nexters.sororok.model.GroupResponseModel;
import com.nexters.sororok.model.LoginRequestModel;
import com.nexters.sororok.model.LoginResponseModel;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface RetrofitService {

    String BASE_URL = "http://45.63.120.140:40005/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitService.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @POST("member/login")
    Call<LoginResponseModel> login(@Body LoginRequestModel body);

    // 이미지 없이 필수 요건들만 들어간 경우
    @Multipart
    @PUT("member/join")
    Call<LoginResponseModel> signUp(@Part("phone") String phone,
                                    @Part("name") String name,
                                    @Part("email") String email,
                                    @Part("loginType") String loginType,
                                    @Part("loginUid") String loginUid
    );

    // 이미지가 파일인 경우
    @Multipart
    @PUT("member/join")
    Call<LoginResponseModel> signUp(@Part("phone") String phone,
                                    @Part("name") String name,
                                    @Part("email") String email,
                                    @Part("loginType") String loginType,
                                    @Part("loginUid") String loginUid,
                                    @Part MultipartBody.Part image
    );

    // 이미지가 URL인 경우
    @Multipart
    @PUT("member/join")
    Call<LoginResponseModel> signUp(@Part("phone") String phone,
                                    @Part("name") String name,
                                    @Part("email") String email,
                                    @Part("loginType") String loginType,
                                    @Part("loginUid") String loginUid,
                                    @Part("imageUrl") String imageUrl
    );

    // 필수 요소로 저장소 생성
    @PUT("repository/create")
    Call<GroupResponseModel> create(@Part("name") String name,
                                    @Part("code") String code,
                                    @Part("memberId") int memberId,
                                    @Part("extraInfo") String extraInfo
    );
}
