package com.app.mgxchange.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.app.mgxchange.R;
import com.app.mgxchange.databinding.ActivityItemsListBinding;
import com.app.mgxchange.fragments.ActiveLoanProducts;
import com.app.mgxchange.fragments.ActiveSellProducts;

public class ItemsList extends AppCompatActivity {
    ImageView back;
    ActivityItemsListBinding mBinding;
    Fragment fragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityItemsListBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        if (fragment == null) {
            fragment = new ActiveSellProducts();
            mBinding.tvSellProductsList.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorPrimary));
            mBinding.tvSellProductsList.setTextColor(Color.WHITE);
            mBinding.tvLoanProductsList.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.white));
            mBinding.tvLoanProductsList.setTextColor(getApplicationContext().getResources().getColor(R.color.colorPrimary));

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.flProducts, fragment);
            fragmentTransaction.commit();
        }
        mBinding.imgBtnBack.setOnClickListener(view1 -> {
            Intent i = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(i);
        });

        mBinding.tvLoanProductsList.setOnClickListener(view12 -> {
            mBinding.tvLoanProductsList.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorPrimary));
            mBinding.tvSellProductsList.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.white));
            mBinding.tvLoanProductsList.setTextColor(Color.WHITE);
            mBinding.tvSellProductsList.setTextColor(getApplicationContext().getResources().getColor(R.color.colorPrimary));
            fragment = new ActiveSellProducts();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flProducts, fragment);
            fragmentTransaction.commit();

        });
        mBinding.tvSellProductsList.setOnClickListener(view13 -> {
            mBinding.tvSellProductsList.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.colorPrimary));
            mBinding.tvSellProductsList.setTextColor(Color.WHITE);
            mBinding.tvLoanProductsList.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.white));
            mBinding.tvLoanProductsList.setTextColor(getApplicationContext().getResources().getColor(R.color.colorPrimary));

            fragment = new ActiveLoanProducts();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flProducts, fragment);
            fragmentTransaction.commit();
        });
    }
}