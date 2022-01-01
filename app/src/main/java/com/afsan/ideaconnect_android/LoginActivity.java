package com.afsan.ideaconnect_android;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afsan.ideaconnect_android.Model.ApiInterface;
import com.afsan.ideaconnect_android.Model.LoginModel;
import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText username, password;
    TextView forgotbtn, createbtn;
    String access = "";
    String refresh = "";
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.login_btn);
        username = findViewById(R.id.set_username);
        password = findViewById(R.id.set_password);
        forgotbtn = findViewById(R.id.forgot_btn);
        createbtn = findViewById(R.id.acc_create);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String name = sp.getString("name","");
        System.err.println("Printing from login page "+name + "=<<");
        if(name == ""){
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uname = username.getText().toString();
                    String pass = password.getText().toString();
                    if(!uname.isEmpty() && !pass.isEmpty()){
                        validateUserInfo(uname,pass);
                    }

                    else{
                        if(uname.isEmpty()){

                            username.setBackgroundColor(Color.RED);
                        }
                        else{
                            password.setBackgroundColor(Color.RED);
                        }


                    }

                }

            });
        }
        else{
            Intent home = new Intent(LoginActivity.this, MainActivity.class);
            home.putExtra("stat", "olduser");
            startActivity(home);
        }

    }

    public void validateUserInfo(String uid, String upass){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://3737-103-177-48-4.ngrok.io/api/")

                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface retrofitAPI = retrofit.create(ApiInterface.class);

        LoginModel modal = new LoginModel(uid, upass, access,refresh);
        System.out.println("Printing from login");
        Call<LoginModel> call = retrofitAPI.loginUser(modal);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                if(response.code() == 200){
                    Intent home = new Intent(LoginActivity.this, MainActivity.class);

                    JWT jwt = new JWT(response.body().getAccess());
                    Claim name = jwt.getClaim("username");
                    Claim id = jwt.getClaim("user_id");
                    String username = name.asString();
                    int userid = id.asInt();

                    sp = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("name", username);
                    editor.putInt("id", userid);
                    editor.commit();
                    startActivity(home);
                    Toast.makeText(LoginActivity.this,"Saved into preference", Toast.LENGTH_LONG).show();
//                    home.putExtra("access",  response.body().getAccess());
//                    home.putExtra("refresh", response.body().getRefresh());


                }
                else{
                    Toast.makeText(getApplicationContext(),"Incorrect Login Credential",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                System.out.println("Failed"+t.getMessage());
            }
        });

    }
}