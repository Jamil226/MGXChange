package com.app.mgxchange.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.app.mgxchange.databinding.ActivityLoanOrSellSelectionBinding;

public class LoanOrSellSelection extends AppCompatActivity {

    ActivityLoanOrSellSelectionBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoanOrSellSelectionBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        mBinding.imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(i);
            }
        });
        mBinding.btnGetLoan.setOnClickListener(view1 -> {
                    Intent i = new Intent(getApplicationContext(), AddProductToGetLoan.class);
                    startActivity(i);
                }
        );

        mBinding.btnSellProduct.setOnClickListener(view2 -> {
            Intent i = new Intent(getApplicationContext(), AddProductToSell.class);
            startActivity(i);
        });


    }
}