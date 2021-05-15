package com.app.mgxchange.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.mgxchange.databinding.ActivityAddProductToSellBinding;
import com.app.mgxchange.sharedPrefs.UserSharedPrefManager;

public class AddProductToSell extends AppCompatActivity {
    String TAG = "AddProductToSell";
    UserSharedPrefManager userSharedPrefManager;
    String userID;
    private ActivityAddProductToSellBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityAddProductToSellBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        userSharedPrefManager = new UserSharedPrefManager(getApplicationContext());
        userID = userSharedPrefManager.getUser().getUserID();
        Toast.makeText(this, "ID " +userID, Toast.LENGTH_SHORT).show();

    }
}