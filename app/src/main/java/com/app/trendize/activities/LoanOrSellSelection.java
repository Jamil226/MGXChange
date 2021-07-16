package com.app.trendize.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.app.trendize.databinding.ActivityLoanOrSellSelectionBinding;

public class LoanOrSellSelection extends AppCompatActivity {

    ActivityLoanOrSellSelectionBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoanOrSellSelectionBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        mBinding.imgBtnBack.setOnClickListener(viewImgBtnBack -> {
            Intent i = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(i);
        });
        mBinding.btnGetLoan.setOnClickListener(viewBtnGetLoan -> {
                    Intent i = new Intent(getApplicationContext(), AddProductToGetLoan.class);
                    startActivity(i);
                }
        );

        mBinding.btnSellProduct.setOnClickListener(viewBtnSellProduct -> {
            Intent i = new Intent(getApplicationContext(), AddProductToSell.class);
            startActivity(i);
        });


    }
}