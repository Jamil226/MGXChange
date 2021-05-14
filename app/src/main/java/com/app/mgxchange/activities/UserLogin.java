package com.app.mgxchange.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.app.mgxchange.R;
import com.app.mgxchange.models.LoginUserResponse;
import com.app.mgxchange.sharedPrefs.UserSharedPrefManager;
import com.app.mgxchange.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserLogin extends AppCompatActivity {
    Button signIn;
    EditText email, password;
    TextView forgotPassword, signUp;
    private static final String TAG = "RiderLogin";
    UserSharedPrefManager userSharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
        userSharedPrefManager = new UserSharedPrefManager(getApplicationContext());

        initViewsLogin();
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginValidation();
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        ForgetPassword.class);
                startActivity(intent);

            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        UserSignUp.class);
                startActivity(intent);
            }
        });
    }

    public void loginValidation() {
        String mail = email.getText().toString();
        String pass = password.getText().toString();

        if (TextUtils.isEmpty(mail) || mail.equals(" ")) {
            email.setError("Enter Email Address");
            email.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("Invalid Email Address");
            email.requestFocus();
        } else if (TextUtils.isEmpty(pass) || pass.length() < 8) {
            password.setError("Please Input a Valid Password Longer" +
                    " Than 7 Characters");
            password.requestFocus();
        } else {
            loginUser();
        }
    }

    public void loginUser() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        String mail = email.getText().toString();
        String pass = password.getText().toString();

        Call<LoginUserResponse> call = RetrofitClient
                .getInstance()
                .getApi().loginUser(mail, pass);
        call.enqueue(new Callback<LoginUserResponse>() {
            @Override
            public void onResponse(Call<LoginUserResponse> call,
                                   Response<LoginUserResponse> response) {
                progressDialog.dismiss();
                LoginUserResponse loginUserResponse = response.body();
                if (response.isSuccessful()) {
                    if (loginUserResponse.getUserStatus().equals("200")) {
                        userSharedPrefManager.saveUser(loginUserResponse.getUser());
                        Toast.makeText(UserLogin.this,
                                loginUserResponse.getUserMessage(),
                                Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), Dashboard.class);
                        startActivity(i);
                        finish();
                    } else if (loginUserResponse.getUserStatus().equals("400")) {
                        Toast.makeText(UserLogin.this,
                                loginUserResponse.getUserMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UserLogin.this,
                            loginUserResponse.getUserMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginUserResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.d(TAG, t.toString());
            }
        });
    }

    public void initViewsLogin() {
        email = findViewById(R.id.et_user_login_email);
        password = findViewById(R.id.et_user_login_password);
        forgotPassword = findViewById(R.id.tv_user_sign_in_fp);
        signUp = findViewById(R.id.tv_user_sign_in_ca);
        signIn = findViewById(R.id.btn_user_sing_in);
    }

}