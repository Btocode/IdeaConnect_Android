package com.afsan.ideaconnect_android;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afsan.ideaconnect_android.Model.ApiInterface;
import com.afsan.ideaconnect_android.Model.CreatePostModel;
import com.afsan.ideaconnect_android.Model.SignupModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewIdea extends Fragment {
TextView name , jobtitle;
EditText title, ideadesc, tags;
Button post;

    public NewIdea() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_idea, container, false);
        name = view.findViewById(R.id.name_create);
        title = view.findViewById(R.id.title_create);
        ideadesc = view.findViewById(R.id.ideadesc_create);
        tags = view.findViewById(R.id.tags_create);
        post = view.findViewById(R.id.btnpost_create);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String uid = sp.getString("fullname","");
        String userId = sp.getString("name","");

        if (uid != ""){
            name.setText(uid);
        }
        else{
            name.setText(userId);
        }


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ideatitle = title.getText().toString();
                String ideadescr = ideadesc.getText().toString();
                String ideatags = tags.getText().toString();


                int userid = sp.getInt("id",0);
                if (!ideatitle.isEmpty() && !ideadescr.isEmpty() && !ideatags.isEmpty()){
//                    Toast.makeText(getContext(),"Success",Toast.LENGTH_LONG).show();
                    CreatePosthandler(userid,ideatitle,ideadescr,ideatags);
                }
                else {
                    Toast.makeText(getContext(),"No fields are allowed to be empty",Toast.LENGTH_LONG).show();
                }

            }
        });


        return view;
    }

    private void CreatePosthandler(int userid, String ideatitle, String ideadescr, String ideatags) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://backend.btocode.repl.co/api/token/")

                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface retrofitAPI = retrofit.create(ApiInterface.class);

        CreatePostModel modal = new CreatePostModel(ideatitle, ideadescr, ideatags,userid);
        System.out.println("Printing Model -> "+modal.getIdeaDesc());
        Call<CreatePostModel> call = retrofitAPI.createPost(modal);
        call.enqueue(new Callback<CreatePostModel>() {
            @Override
            public void onResponse(Call<CreatePostModel> call, Response<CreatePostModel> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(),"Idea Successfully Posted",Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(getContext(),"Please try again later",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CreatePostModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}