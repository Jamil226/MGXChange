package com.app.mgxchange.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.app.mgxchange.R;
import com.app.mgxchange.databinding.ActivityDetailedUserProfileBinding;
import com.app.mgxchange.sharedPrefs.UserSharedPrefManager;
import com.app.mgxchange.utils.ApiUrls;
import com.bumptech.glide.Glide;

public class DetailedUserProfile extends AppCompatActivity {
    String TAG = "DetailedUserProfile";
    ActivityDetailedUserProfileBinding mBinding;
    UserSharedPrefManager userSharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityDetailedUserProfileBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        userSharedPrefManager = new UserSharedPrefManager(getApplicationContext());
        String userID = userSharedPrefManager.getUser().getUserID();
        String firstName = userSharedPrefManager.getUser().getFirstName();
        String lastName = userSharedPrefManager.getUser().getLastName();
        String contact = userSharedPrefManager.getUser().getContact();
        String address = userSharedPrefManager.getUser().getAddress();
        String email = userSharedPrefManager.getUser().getEmail();
        String imagePath = userSharedPrefManager.getUser().getImagePath();

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