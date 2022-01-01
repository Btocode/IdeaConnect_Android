package com.afsan.ideaconnect_android.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiInterface {
    @GET("ideas/")
    Call<List<API>> getModels();
    @POST("token/")
    Call<LoginModel> loginUser(@Body LoginModel loginmodel);
}
