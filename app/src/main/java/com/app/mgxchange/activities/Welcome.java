package com.app.mgxchange.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.app.mgxchange.R;
import com.app.mgxchange.sharedPrefs.UserSharedPrefManager;

public class Welcome extends AppCompatActivity {
    Button login, signUp;
    UserSharedPrefManager userSharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        login = findViewById(R.id.btn_welcome_login);
        signUp = findViewById(R.id.btn_welcome_register);
        userSharedPrefManager = new UserSharedPrefManager(getApplicationContext());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), UserLogin.class);
                startActivity(i);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), UserSignUp.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(userSharedPrefManager.isLoggedIn(getApplicationContext())){
            Intent i = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(i);
        }
    }
}