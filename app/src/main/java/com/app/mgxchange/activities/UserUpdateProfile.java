package com.app.mgxchange.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.app.mgxchange.databinding.ActivityUserUpdateProfileBinding;

public class UserUpdateProfile extends AppCompatActivity {
    private ActivityUserUpdateProfileBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityUserUpdateProfileBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

    }
}