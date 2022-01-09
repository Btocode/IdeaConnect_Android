package com.afsan.ideaconnect_android.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiInterface {


    @GET("ideas/")
    Call<List<API>> getModels();
    @POST("token/")
    Call<LoginModel> loginUser(@Body LoginModel loginmodel);
    @POST("create/")
    Call<SignupModel> registerUser(@Body SignupModel signupModel);
    @POST("ideas/")
    Call<CreatePostModel> createPost(@Body CreatePostModel createPostModel);
    @GET("profile/{id}")
    Call<ProfileModel> getProfile(@Path("id") int profileId);
    @POST("profiles/")
    Call<EditInfoModel> updateProfileInfo(@Body EditInfoModel editInfoModel);
}
