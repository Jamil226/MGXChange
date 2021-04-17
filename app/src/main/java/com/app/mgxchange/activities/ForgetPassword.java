package com.app.mgxchange.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.app.mgxchange.R;

public class ForgetPassword extends AppCompatActivity {
    EditText email, password, confirmPassword;
    Button resetPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
        }
        initViewsForgetPassword();
        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPasswordValidation();
            }
        });

    }

    public void initViewsForgetPassword(){
        email = findViewById(R.id.et_fp_email);
        password = findViewById(R.id.et_fp_password);
        confirmPassword = findViewById(R.id.et_fp_confirm_password);
        resetPass = findViewById(R.id.btn_fp_reset_password);
    }

    public void resetPasswordValidation() {
        String mail = email.getText().toString();
        String pass = password.getText().toString();
        String cPass = confirmPassword.getText().toString();
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
        } else if (TextUtils.isEmpty(cPass) || cPass.equals(" ")) {
            password.setError("Enter Confirm Password");
            password.requestFocus();
        } else if (!pass.matches(cPass)) {
            password.setError("Passwords Not Matched");
            password.requestFocus();
        } else {
            Intent intent = new Intent(getApplicationContext(),
                    Dashboard.class);
            startActivity(intent);
        }
    }

}