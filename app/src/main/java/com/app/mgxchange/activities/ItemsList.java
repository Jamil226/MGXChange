package com.app.mgxchange.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.mgxchange.databinding.ActivityItemsListBinding;

public class ItemsList extends AppCompatActivity {
    ImageView back;
    ActivityItemsListBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityItemsListBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        mBinding.imgBtnBack.setOnClickListener(view1 -> {
            Intent i = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(i);
        });

    }
}