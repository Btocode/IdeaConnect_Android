package com.afsan.ideaconnect_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afsan.ideaconnect_android.Model.ApiInterface;
import com.afsan.ideaconnect_android.Model.ProfileModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserProfile extends AppCompatActivity {

    TextView nameOfUser,name_prof,biodata,programmingView,languageView;
    String url = "https://backend.btocode.repl.co/api/token/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        int id = 0;
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        id = Integer.valueOf(b.get("id").toString());
        nameOfUser = findViewById(R.id.name_profile);
        name_prof =  findViewById(R.id.name_prof);
        biodata =  findViewById(R.id.biodata);
        programmingView =  findViewById(R.id.programmingView);
        languageView =  findViewById(R.id.languageView);




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)

                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface retrofitAPI = retrofit.create(ApiInterface.class);


        String profile_picture="",gender="",jobTitle="",programming="",languageKnown = "",linkedIn = "",resume= "",github = "",bio = "",first_name = "",last_name = "",email = "";
        int age = 0;
        ProfileModel modal = new ProfileModel(profile_picture,gender,jobTitle,programming,languageKnown,linkedIn,resume,github,bio,first_name,last_name,email,age
        );
        System.out.println("Printing from login");
        Call<ProfileModel> call = retrofitAPI.getProfile(id);
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                if (response.isSuccessful()){
                    ProfileModel data = response.body();
                    nameOfUser.setText(data.getFirst_name() + " " +data.getLast_name());
                    name_prof.setText(data.getJobTitle());
                    biodata.setText(data.getBio());
                    programmingView.setText(dataFormatter(data.getProgramming()));
                    languageView.setText(dataFormatter(data.getLanguageKnown()));
                }
                else{
                    Toast.makeText(getApplicationContext(),"This user does not have any additional information added", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                System.out.println("Failed"+t.getMessage());
            }
        });

        System.out.println(id);
    }
    private String dataFormatter(String programming) {
        String [] data = programming.split(" ");
        String temp = "";
        for (int i = 0; i<data.length; i++){
            temp += "@"+data[i] + " ";
        }
        return  temp;
    }
}