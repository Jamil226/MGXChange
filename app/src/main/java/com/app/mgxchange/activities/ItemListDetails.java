package com.app.mgxchange.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.app.mgxchange.databinding.ActivityItemListDetailsBinding;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class ItemListDetails extends AppCompatActivity {

    String TAG = "ItemListDetails";
    String productID, userID;
    String fullName, email, contact;
    String productName, productDetails, productYear, productUploadingDate, productPrice;
    private String imgOne, imgTwo, imgThree, imgFour, imgFive;
    private String productStatus;

    List<SlideModel> slideModels;
    private ActivityItemListDetailsBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityItemListDetailsBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        productID = getIntent().getStringExtra("product_id");
        userID = getIntent().getStringExtra("user_id");
        contact = getIntent().getStringExtra("contact");
        productName = getIntent().getStringExtra("product_name");
        productPrice = getIntent().getStringExtra("product_price");
        productDetails = getIntent().getStringExtra("product_details");
        productYear = getIntent().getStringExtra("product_year");
        productUploadingDate = getIntent().getStringExtra("uploading_date");
        imgOne = getIntent().getStringExtra("image_one");
        imgTwo = getIntent().getStringExtra("image_two");
        imgThree = getIntent().getStringExtra("image_three");
        imgFour = getIntent().getStringExtra("image_four");
        imgFive = getIntent().getStringExtra("image_five");
        productStatus = getIntent().getStringExtra("product_status");

        slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(imgOne, productName));
        slideModels.add(new SlideModel(imgTwo, productName));
        slideModels.add(new SlideModel(imgThree, productName));
        slideModels.add(new SlideModel(imgFour, productName));
        slideModels.add(new SlideModel(imgFive, productName));
        mBinding.ivProductImage.setImageList(slideModels, true);

        mBinding.tvProductName.setText(productName);
        mBinding.tvProductPrice.setText("$" + productPrice);
        mBinding.tvProductDetails.setText(productDetails);

//
//        Glide.with(getApplicationContext()).load(imgOne)
//                .placeholder(R.drawable.image_default)
//                .into(mBinding.ivProductImage);

        if (productStatus.equals("1")){
            mBinding.tvProductStatus.setText("Congratulations! Your Product Have Been Approved.");
        }
        else if(productStatus.equals("0")){
            mBinding.tvProductStatus.setText("Your Product Have Not Been Approved Yet. Please Wait.");
        }
        else if(productStatus.equals("3")){
            mBinding.tvProductStatus.setText("Sorry! Your Product Did Not Qualified & Removed By Our Team.");
        }
        else{
            mBinding.tvProductStatus.setText("Something Went Wrong. Contact Support.");
        }

        mBinding.imgBtnBack.setOnClickListener(viewImgBack -> {
            Intent i = new Intent(getApplicationContext(), ItemsList.class);
            startActivity(i);
        });

    }
}