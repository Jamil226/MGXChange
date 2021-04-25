package com.app.mgxchange.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.app.mgxchange.databinding.ActivityAddProductForLoanBinding;

public class AddProductForLoan extends AppCompatActivity {

    private ActivityAddProductForLoanBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityAddProductForLoanBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);



    }
}