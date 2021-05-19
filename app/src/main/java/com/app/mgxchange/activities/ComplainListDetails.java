package com.app.mgxchange.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.app.mgxchange.databinding.ActivityComplainListDetailsBinding;

public class ComplainListDetails extends AppCompatActivity {
    private ActivityComplainListDetailsBinding mBinding;
    private String productName, complainMessage, reference,
            contact, complainDate, serialNo, productDetail, complainStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityComplainListDetailsBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        reference = getIntent().getStringExtra("reference");
        serialNo = getIntent().getStringExtra("serial");
        complainMessage = getIntent().getStringExtra("message");
        productName = getIntent().getStringExtra("product_name");
        contact = getIntent().getStringExtra("contact");
        complainDate = getIntent().getStringExtra("complain_date");
        complainStatus = getIntent().getStringExtra("status");
        productDetail = getIntent().getStringExtra("product_details");
        if(complainStatus.equals("0")){
            mBinding.tvComplainStatus.setText("Pending");
        }
        else{
            mBinding.tvComplainStatus.setText("Resolved");
        }
        mBinding.tvComplainDate.setText(complainDate);
        mBinding.tvComplainDetails.setText(complainMessage);
        mBinding.tvComplainProductDetails.setText(productDetail);
        mBinding.tvComplainReference.setText(reference);
        mBinding.tvContact.setText(contact);
        mBinding.tvSerialNumber.setText(serialNo);
        mBinding.tvComplainProductName.setText(productName);

        mBinding.imgBtnBack.setOnClickListener(viewImgBtnBack -> {
            Intent i = new Intent(getApplicationContext(), ComplainList.class);
            startActivity(i);
        });
    }
}