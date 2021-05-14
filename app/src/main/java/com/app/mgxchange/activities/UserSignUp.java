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
import com.app.mgxchange.models.RegisterUserResponse;
import com.app.mgxchange.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSignUp extends AppCompatActivity {
    EditText firstName, lastName, contact, address, email, password, confirmPassword;
    Button signUp;
    TextView signIn;
    final String SIGN_UP_METHOD_CODE = "1";
    ProgressDialog progressDialog;
    String TAG = "UserSignUp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
        initViewsSignUp();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpValidation();
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        UserLogin.class);
                startActivity(intent);
            }
        });
    }

    public void initViewsSignUp() {
        firstName = findViewById(R.id.et_user_sign_up_first_name);
        lastName = findViewById(R.id.et_user_sign_up_last_name);
        contact = findViewById(R.id.et_user_sign_up_contact_number);
        address = findViewById(R.id.et_user_sign_up_address);
        email = findViewById(R.id.et_user_sign_up_email);
        password = findViewById(R.id.et_user_sign_up_password);
        confirmPassword = findViewById(R.id.et_user_sign_up_cp);
//        ivProfileImage = findViewById(R.id.iv_user_sign_up_profile_image);
        signIn = findViewById(R.id.tv_user_sign_up_login);
        signUp = findViewById(R.id.btn_user_sign_up);


    }

    public void signUpValidation() {
        String fName = firstName.getText().toString();
        String lName = lastName.getText().toString();
        String phone = contact.getText().toString();
        String userAddress = address.getText().toString();
        String mail = email.getText().toString();
        String pass = password.getText().toString();
        String cPass = confirmPassword.getText().toString();
        if (TextUtils.isEmpty(fName) || fName.equals(" ")) {
            firstName.setError("Enter First Name");
            firstName.requestFocus();
        } else if (TextUtils.isEmpty(lName) || lName.equals(" ")) {
            lastName.setError("Enter Last Name");
            lastName.requestFocus();
        } else if (TextUtils.isEmpty(phone) || phone.equals(" ")) {
            contact.setError("Enter Contact Number");
            contact.requestFocus();
        } else if (TextUtils.isEmpty(userAddress) || userAddress.equals(" ")) {
            address.setError("Enter Address");
            address.requestFocus();
        } else if (TextUtils.isEmpty(mail) || mail.equals(" ")) {
            email.setError("Enter Email Address");
            email.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("Invalid Email Address");
            email.requestFocus();
        } else if (TextUtils.isEmpty(pass) || pass.length() < 8) {
            password.setError("Please Input a Valid Password Longer" +
                    " Than 7 Characters");
            password.requestFocus();
        } else if (TextUtils.isEmpty(cPass) || cPass.equals(" ")) {
            password.setError("Enter Confirm Password");
            password.requestFocus();
        } else if (!pass.matches(cPass)) {
            password.setError("Passwords Not Matched");
            password.requestFocus();
        } else {
            userSignUp();
        }
    }

    public void userSignUp() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        final String userFirstName = firstName.getText().toString().trim();
        final String userLastName = lastName.getText().toString().trim();
        final String userPassword = password.getText().toString().trim();
        final String userContact = contact.getText().toString().trim();
        final String addressUser = address.getText().toString().trim();
        final String userEmail = email.getText().toString().trim();

        Call<RegisterUserResponse> call = RetrofitClient.getInstance()
                .getApi()
                .registerUser(userFirstName, userLastName, SIGN_UP_METHOD_CODE,
                        userContact, addressUser, userEmail, userPassword);
        call.enqueue(new Callback<RegisterUserResponse>() {
            @Override
            public void onResponse(Call<RegisterUserResponse> call,
                                   Response<RegisterUserResponse> response) {
                progressDialog.dismiss();
                RegisterUserResponse registerUserResponse = response.body();
                if (response.isSuccessful())
                {
                    if (registerUserResponse.getUserStatus().equals("400")) {
                        Toast.makeText(UserSignUp.this,
                                registerUserResponse.getUserMessage(),
                                Toast.LENGTH_SHORT).show();
                    } else if (registerUserResponse.getUserStatus().equals("200")) {
                        Toast.makeText(UserSignUp.this,
                                registerUserResponse.getUserMessage(),
                                Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), UserLogin.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(UserSignUp.this,
                                registerUserResponse.getUserMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(UserSignUp.this,
                            registerUserResponse.getUserMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure (Call < RegisterUserResponse > call, Throwable t){
                progressDialog.dismiss();
                Log.d(TAG, t.toString());
            }
        });
    }
}