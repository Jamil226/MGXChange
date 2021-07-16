package com.app.trendize.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.app.trendize.R;
import com.app.trendize.databinding.ActivityForgetPasswordBinding;

import es.dmoral.toasty.Toasty;

public class ForgetPassword extends AppCompatActivity {
    ActivityForgetPasswordBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
        mBinding.btnFpResetPassword.setOnClickListener(view1 -> resetPassword());
    }

    public void resetPassword() {
        String mail = mBinding.etFpEmail.getText().toString();
        String pass = mBinding.etFpPassword.getText().toString();
        String cPass = mBinding.etFpConfirmPassword.getText().toString();
        if (TextUtils.isEmpty(mail) || mail.equals(" ")) {
            mBinding.etFpEmail.setError("Enter Email Address");
            mBinding.etFpEmail.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            mBinding.etFpEmail.setError("Invalid Email Address");
            mBinding.etFpEmail.requestFocus();
        } else if (TextUtils.isEmpty(pass) || pass.length() < 8) {
            mBinding.etFpPassword.setError("Please Input a Valid Password Longer" +
                    " Than 7 Characters");
            mBinding.etFpPassword.requestFocus();
        } else if (TextUtils.isEmpty(cPass) || cPass.equals(" ")) {
            mBinding.etFpConfirmPassword.setError("Enter Confirm Password");
            mBinding.etFpConfirmPassword.requestFocus();
        } else if (!pass.matches(cPass)) {
            mBinding.etFpPassword.setError("Passwords Not Matched");
            mBinding.etFpPassword.requestFocus();
        } else {
            Toasty.success(getApplicationContext(), "Success",
                    Toasty.LENGTH_LONG, true).show();
            Intent intent = new Intent(getApplicationContext(),
                    Dashboard.class);
            startActivity(intent);
        }
    }

}