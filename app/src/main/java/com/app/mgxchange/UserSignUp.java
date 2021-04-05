package com.app.mgxchange;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.app.mgxchange.utils.ApiUrls;

public class UserSignUp extends AppCompatActivity {
    EditText firstName, lastName, contact, email, password, confirmPassword;
    Button signUp;
    TextView signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
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

    public void initViewsSignUp(){
        firstName = findViewById(R.id.et_user_sign_up_first_name);
        lastName = findViewById(R.id.et_user_sign_up_last_name);
        contact = findViewById(R.id.et_user_sign_up_contact_number);
        email = findViewById(R.id.et_user_sign_up_email);
        password = findViewById(R.id.et_user_sign_up_password);
        confirmPassword = findViewById(R.id.et_user_sign_up_cp);
        signIn = findViewById(R.id.tv_user_sign_up_login);
        signUp = findViewById(R.id.btn_user_sign_up);

    }

    public void signUpValidation() {
        String fName = firstName.getText().toString();
        String lName = lastName.getText().toString();
        String phone = contact.getText().toString();
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
    public void userSignUp(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        final String userFirstName = this.firstName.getText().toString().trim();
        final String userLastName = this.lastName.getText().toString().trim();
        final String userPassword = this.password.getText().toString().trim();
        final String userContact = this.contact.getText().toString().trim();
//        final String userContact = this.contact.getText().toString().trim();
//        final String userContact = this.contact.getText().toString().trim();
        Uri.Builder builder = Uri.parse(ApiUrls.RegisterUser).buildUpon();
//        builder.appendQueryParameter("name", name);
//        builder.appendQueryParameter("email", email);
//        builder.appendQueryParameter("password", password);
//        builder.appendQueryParameter("postcode", postCode);
        String urlEncrypted = builder.build().toString();
    }

}