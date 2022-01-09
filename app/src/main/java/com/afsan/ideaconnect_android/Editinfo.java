package com.afsan.ideaconnect_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.afsan.ideaconnect_android.Model.ApiInterface;
import com.afsan.ideaconnect_android.Model.EditInfoModel;
import com.afsan.ideaconnect_android.Model.ProfileModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Editinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editinfo);
        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Male", "Female", "Others"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);


        Button submit = (Button) findViewById(R.id.ep_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText title = findViewById(R.id.ep_jtitle);
                EditText bio = findViewById(R.id.ep_bio);
                EditText age = findViewById(R.id.ep_age);
                EditText github = findViewById(R.id.ep_github);
                EditText linkedin = findViewById(R.id.ep_linkedin);
                EditText resume = findViewById(R.id.ep_resume);
                EditText programming = findViewById(R.id.ep_pl);
                EditText knownlanguage = findViewById(R.id.ep_lk);
                String gender = dropdown.getSelectedItem().toString();
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Editinfo.this);
                int user = sp.getInt("id",0);


                if((!title.getText().toString().isEmpty()) &&
                        (!bio.getText().toString().isEmpty()) &&
                        (!age.getText().toString().isEmpty()) &&
                        (!github.getText().toString().isEmpty()) &&
                        (!linkedin.getText().toString().isEmpty()) &&
                        (!resume.getText().toString().isEmpty()) &&
                        (!programming.getText().toString().isEmpty()) &&
                        (!knownlanguage.getText().toString().isEmpty()) && !gender.isEmpty()){
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://backend.btocode.repl.co/api/token/")

                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    ApiInterface retrofitAPI = retrofit.create(ApiInterface.class);


                    EditInfoModel modal = new EditInfoModel(user,title.getText().toString(),programming.getText().toString(),knownlanguage.getText().toString(),linkedin.getText().toString(),resume.getText().toString(),github.getText().toString(),bio.getText().toString(),gender,Integer.valueOf(age.getText().toString()));
                    System.out.println("Printing from EditInfo");
                    Call<EditInfoModel> call = retrofitAPI.updateProfileInfo(modal);
                    call.enqueue(new Callback<EditInfoModel>() {
                        @Override
                        public void onResponse(Call<EditInfoModel> call, Response<EditInfoModel> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Successfully Updated User Information",Toast.LENGTH_SHORT).show();

                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Your information already exist",Toast.LENGTH_SHORT).show();


                            }

                        }

                        @Override
                        public void onFailure(Call<EditInfoModel> call, Throwable t) {
                            System.out.println("Failed"+t.getMessage());
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"None of the fields are allowed to be empty",Toast.LENGTH_SHORT).show();

                }


            }
        });
    }
}