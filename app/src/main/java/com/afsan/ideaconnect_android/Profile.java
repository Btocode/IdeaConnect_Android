package com.afsan.ideaconnect_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afsan.ideaconnect_android.Model.ApiInterface;
import com.afsan.ideaconnect_android.Model.LoginModel;
import com.afsan.ideaconnect_android.Model.ProfileModel;
import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView nameOfUser,name_prof,biodata,programmingView,languageView;
    Button btn_edit;
    String url = "https://backend.btocode.repl.co/api/token/";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());

        int id = sp.getInt("id",0);

        String name = sp.getString("name","");
//        System.err.println("Printing from Profile page "+ id + " && name = " + name);
        nameOfUser = rootView.findViewById(R.id.name_profile);
        name_prof =  rootView.findViewById(R.id.name_prof);
        biodata =  rootView.findViewById(R.id.biodata);
        programmingView =  rootView.findViewById(R.id.programmingView);
        languageView =  rootView.findViewById(R.id.languageView);

        btn_edit = (Button) rootView.findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Editinfo.class);
                startActivity(intent);
            }
        });

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
                    Toast.makeText(getActivity(),"Please Add Additional information from EDIT", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                System.out.println("Failed"+t.getMessage());
            }
        });






        return rootView;
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