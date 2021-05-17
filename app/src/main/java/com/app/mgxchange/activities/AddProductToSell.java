package com.app.mgxchange.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.mgxchange.R;
import com.app.mgxchange.databinding.ActivityAddProductToSellBinding;
import com.app.mgxchange.models.UserAddSellProductResponse;
import com.app.mgxchange.sharedPrefs.UserSharedPrefManager;
import com.app.mgxchange.utils.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductToSell extends AppCompatActivity {
    private static final String TAG = "AddProductToSell";
    UserSharedPrefManager userSharedPrefManager;
    private final int IMAGE_REQUEST = 21;
    ActivityAddProductToSellBinding mBinding;
    String selectedType, selectedCondition;
    String userID = "1";
    private Bitmap bitmapImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityAddProductToSellBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        userSharedPrefManager = new UserSharedPrefManager(getApplicationContext());
        userID = userSharedPrefManager.getUser().getUserID();
        ArrayAdapter<CharSequence> productConditionSpinnerAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.item_condition, android.R.layout.simple_spinner_item);
        productConditionSpinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBinding.spProductCondition.setAdapter(productConditionSpinnerAdapter);
        ArrayAdapter<CharSequence> productTypeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.item_types, android.R.layout.simple_spinner_item);
        productTypeSpinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBinding.spProductType.setAdapter(productTypeSpinnerAdapter);

        mBinding.spProductType.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.e(TAG, "onItemSelected: " + parent.getId());
                        selectedType = parent.getItemAtPosition(position).toString();
                        Log.e(TAG, "onItemSelected: selected" + selectedType);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                }
        );
        mBinding.spProductCondition.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.e(TAG, "onItemSelected: " + parent.getId());
                        selectedCondition = parent.getItemAtPosition(position).toString();
                        Log.e(TAG, "onItemSelected: selected" + selectedCondition);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                }
        );

        mBinding.imgBtnBack.setOnClickListener(viewImgBtnBack -> {
            Intent i = new Intent(getApplicationContext(), LoanOrSellSelection.class);
            startActivity(i);
        });

        mBinding.ivProductImageChoose1.setOnClickListener(viewUploadImage1 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, IMAGE_REQUEST);
        });

        mBinding.btnSubmitProduct.setOnClickListener(viewBtnSubmitProduct -> {
            String productName = String.valueOf(selectedType);
            String productYear = mBinding.etProductYear.getText().toString();
            String productSerial = mBinding.etProductSerial.getText().toString();
            String productCondition = String.valueOf(selectedCondition);
            String askedAmount = mBinding.etAskingAmount.getText().toString();
            String contactNumber = mBinding.etContactNumber.getText().toString();
            String productDetails = mBinding.etProductDetails.getText().toString();

            if (productName.equals("Select Product")) {
                Toast.makeText(AddProductToSell.this,
                        "Select Product Type", Toast.LENGTH_SHORT).show();
                mBinding.spProductType.requestFocus();
            } else if (TextUtils.isEmpty(productYear) || productYear.equals(" ")) {
                mBinding.etProductYear.setError("Enter Manufacturing Year");
                mBinding.etProductYear.requestFocus();
            } else if (TextUtils.isEmpty(productSerial) || productSerial.equals(" ")) {
                mBinding.etProductSerial.setError("Enter Product Serial / IMEI ");
                mBinding.etProductSerial.requestFocus();
            } else if (productCondition.equals("Select Condition")) {
                Toast.makeText(AddProductToSell.this,
                        "Select Product Condition", Toast.LENGTH_SHORT).show();
                mBinding.spProductCondition.requestFocus();
            } else if (TextUtils.isEmpty(askedAmount) || askedAmount.equals(" ")) {
                mBinding.etAskingAmount.setError("Enter Asking Amount");
                mBinding.etAskingAmount.requestFocus();
            } else if (TextUtils.isEmpty(contactNumber) || contactNumber.equals(" ")) {
                mBinding.etContactNumber.setError("Enter Contact Number");
                mBinding.etContactNumber.requestFocus();
            } else if (TextUtils.isEmpty(productDetails) || productDetails.equals(" ")) {
                mBinding.etProductDetails.setError("Enter Product Details");
                mBinding.etProductDetails.requestFocus();
            } else if (mBinding.ivProductImage1.getDrawable().getConstantState()
                    == getResources().getDrawable(R.drawable.image_default).getConstantState()) {
                Toast.makeText(AddProductToSell.this, "Select Product Image", Toast.LENGTH_SHORT).show();
            } else {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmapImage.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                byte[] imageInByte = byteArrayOutputStream.toByteArray();
                String encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);
                Log.d(TAG, "Image Encoded" + encodedImage);

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading Product Data");
                progressDialog.setMessage("Please Wait for a while...");
                progressDialog.show();
                progressDialog.setCancelable(false);
                Call<UserAddSellProductResponse> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .addSellProduct(userID, productName, productYear,
                                productSerial, productCondition, askedAmount,
                                contactNumber, productDetails, encodedImage);
                call.enqueue(new Callback<UserAddSellProductResponse>() {
                    @Override
                    public void onResponse(Call<UserAddSellProductResponse> call,
                                           Response<UserAddSellProductResponse> response) {
                        progressDialog.dismiss();
                        if (response.isSuccessful()) {
                            Toast.makeText(AddProductToSell.this,
                                    response.body().getProductMessage(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), Dashboard.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(AddProductToSell.this,
                                    response.body().getProductMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserAddSellProductResponse> call,
                                          Throwable t) {
                        progressDialog.dismiss();
                        Log.d(TAG, t.getMessage());
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri path1 = data.getData();
            try {
                bitmapImage = MediaStore.Images.Media.getBitmap(getContentResolver(), path1);
                mBinding.ivProductImage1.setImageBitmap(bitmapImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
