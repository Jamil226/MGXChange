package com.app.mgxchange.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.app.mgxchange.R;
import com.app.mgxchange.databinding.ActivityDetailedUserProfileBinding;
import com.app.mgxchange.utils.ApiUrls;
import com.bumptech.glide.Glide;

public class DetailedUserProfile extends AppCompatActivity {
    String TAG = "DetailedUserProfile";
    ActivityDetailedUserProfileBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityDetailedUserProfileBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        SharedPreferences prefPersonalUser = getSharedPreferences("userData", MODE_PRIVATE);
        String userID = prefPersonalUser.getString("user_id", null);
        String firstName = prefPersonalUser.getString("firstName", null);
        String lastName = prefPersonalUser.getString("lastName", null);
        String address = prefPersonalUser.getString("address", null);
        String contact = prefPersonalUser.getString("contact", null);
        String email = prefPersonalUser.getString("email", null);
        String imagePath = prefPersonalUser.getString("imagePath", null);
        Log.d(TAG, "User ID = " + userID);
        Glide.with(getApplicationContext())
                .load(ApiUrls.imgParentUrl + imagePath)
                .placeholder(R.drawable.image_default)
                .into(mBinding.ivUserImage);
        mBinding.tvUserFullName.setText(firstName +" "+lastName);
        mBinding.tvUserEmail.setText(email);
        mBinding.tvUserContact.setText(contact);
        mBinding.tvUserAddress.setText(address);

        mBinding.imgBtnBack.setOnClickListener(back -> {
            Intent i = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(i);
        });

        mBinding.btnUpdateUserProfile.setOnClickListener(updateProfileButton -> {
            Intent i = new Intent(getApplicationContext(), UserUpdateProfile.class);
            startActivity(i);
        });

    }
}