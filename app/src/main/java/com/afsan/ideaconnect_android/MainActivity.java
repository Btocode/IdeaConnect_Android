package com.afsan.ideaconnect_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavView;
    String access,refresh;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavView = findViewById(R.id.bottom_nav_menu);




        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        String status = "";
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String name = sp.getString("name","");
        System.err.println("Printing from Main page "+name + "=<<");



//        if(b!=null){
//            status = (String) b.get("stat");
//        }
//        if (status == ""){
//            if(b!=null)
//            {
//                access =(String) b.get("access");
//                refresh =(String) b.get("refresh");
//            }
//            JWT jwt = new JWT(access);
//            Claim name = jwt.getClaim("username");
//            Claim id = jwt.getClaim("user_id");
//            String username = name.asString();
//            int userid = id.asInt();
//
//            sp = PreferenceManager.getDefaultSharedPreferences(this);
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putString("name", username);
////        editor.putInt("id", userid);
//            editor.commit();
//            Toast.makeText(MainActivity.this,"Saved into preference", Toast.LENGTH_LONG).show();
//
//        }

//        System.out.println("UserName : "+username);
//        System.out.println("UserId : "+userid);




        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,new Home());
        transaction.commit();

        bottomNavView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.home:
                    fragment = new Home();
                    break;
                case R.id.notification:
                    fragment = new Notification();
                    break;
                    case R.id.trending:
                    fragment = new trending();
                    break;
                case R.id.profile:
                    fragment = new Profile();
                    break;
                    case R.id.idea:
                    fragment = new NewIdea();
                    break;
            }
            if (fragment == null) return true;
            MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();

            return true;

        });

    }

}