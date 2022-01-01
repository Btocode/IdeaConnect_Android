package com.afsan.ideaconnect_android;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afsan.ideaconnect_android.Model.ApiInterface;
import com.afsan.ideaconnect_android.Model.LoginModel;
import com.afsan.ideaconnect_android.Model.SignupModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
TextView firstname,lastname,username,email,password1,password2;
Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firstname = findViewById(R.id.first_name);
        lastname = findViewById(R.id.last_name);
        username = findViewById(R.id.give_username);
        email = findViewById(R.id.set_email);
        password1 = findViewById(R.id.give_pass);
        password2 = findViewById(R.id.re_pass);
        submit = (Button) findViewById(R.id.submit_btn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = firstname.getText().toString();
                String lname = lastname.getText().toString();
                String email_t = email.getText().toString();
                String uname = username.getText().toString();
                String pass1 = password1.getText().toString();
                String pass2 = password2.getText().toString();
                System.out.println("pass1 = " + pass1 + "\npass2 = " + pass2);
                if ((!fname.isEmpty()) && (!lname.isEmpty()) && (!uname.isEmpty()) && (!email_t.isEmpty()) && (!pass1.isEmpty()) && (!pass2.isEmpty()) ){
                    if (pass1.equals(pass2)){
                        RegistrationRequestHandler(fname,lname,email_t,uname,pass2);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Password Must be same ",Toast.LENGTH_LONG).show();
                    }


                }
                else {
                    Toast.makeText(getApplicationContext(),"None of the fields are allowed to be empty",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void RegistrationRequestHandler(String first_name,String last_name,String user_name,String e_mail,String password_t) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://3737-103-177-48-4.ngrok.io/api/token/")

                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface retrofitAPI = retrofit.create(ApiInterface.class);

        SignupModel modal = new SignupModel(first_name, last_name, e_mail,user_name,password_t);
        System.out.println("Printing Model -> "+modal.getEmail());
        Call<SignupModel> call = retrofitAPI.registerUser(modal);
        call.enqueue(new Callback<SignupModel>() {
            @Override
            public void onResponse(Call<SignupModel> call, Response<SignupModel> response) {
                if(response.code() == 201){
                    Toast.makeText(getApplicationContext(),"Account Created Succesfully",Toast.LENGTH_LONG).show();
                    Intent login = new Intent(SignUpActivity.this,LoginActivity.class);
                    startActivity(login);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please fill the form in correct format",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<SignupModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}