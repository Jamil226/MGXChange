package com.app.mgxchange.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.mgxchange.databinding.ActivityUserUpdateProfileBinding;
import com.app.mgxchange.models.UserProfileImageResponse;
import com.app.mgxchange.utils.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserUpdateProfile extends AppCompatActivity {
    private ActivityUserUpdateProfileBinding mBinding;
    private final int IMAGE_REQUEST = 21;
    String TAG = "UserUpdateProfile";
    String userID;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityUserUpdateProfileBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        userID = getIntent().getStringExtra("user_id");

        mBinding.ivUploadImage.setOnClickListener(viewUploadImage -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, IMAGE_REQUEST);
        });

        mBinding.btnUpdatePicture.setOnClickListener(viewUpdateImage -> uploadImage());

        mBinding.imgBtnBack.setOnClickListener(viewImgBtnBack -> {
            Intent i = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(i);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                mBinding.ivProfileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Updating Profile Image");
        progressDialog.setMessage("Please Wait for a while...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        Call<UserProfileImageResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .updateUserImage(userID, encodedImage);
        call.enqueue(new Callback<UserProfileImageResponse>() {
            @Override
            public void onResponse(Call<UserProfileImageResponse> call,
                                   Response<UserProfileImageResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    Toast.makeText(UserUpdateProfile.this,
                            response.body().getImageMessage(), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), Dashboard.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(UserUpdateProfile.this,
                            response.body().getImageMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserProfileImageResponse> call,
                                  Throwable t) {
                progressDialog.dismiss();
                Log.d(TAG, t.getMessage());
            }
        });
    }
}