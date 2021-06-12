package com.app.mgxchange.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import androidx.appcompat.app.AlertDialog;
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
    private final int IMAGE_REQUEST_TWO = 22;
    private final int IMAGE_REQUEST_THREE = 23;
    private final int IMAGE_REQUEST_FOUR = 24;
    private final int IMAGE_REQUEST_FIVE = 25;
    private final int IMAGE_REQUEST_SIX = 26;
    private final int IMAGE_REQUEST_SEVEN = 27;
    ActivityAddProductToSellBinding mBinding;
    String selectedType, selectedCondition;
    String userID;
    private Bitmap bitmapImage1, bitmapImage2, bitmapImage3,
            bitmapImage4, bitmapImage5, bitmapImage6;

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

        mBinding.tvProductTerms.setOnClickListener(viewTvProductTermsListener -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(AddProductToSell.this);
            builder.setTitle("Terms and Conditions");
            builder.setMessage("- By accepting our offer you agree that your valuable(s) will be evaluated based on the market value of your item. \n" +
                    "-  After item evaluation is accepted by client, MGXCHANGE will payout the agreed evaluated amount, you therefore give MGXCHANGE leverage over your valuable(s). \n" +
                    "- You acknowledge valuable(s) is own by you and not stolen, in a situation were stolen case arises, you will provide all additional information to the relevant authority to proof ownership of the valuable(s) \n" +
                    "- Valuable(s) sold to MGXCHANGE is liable to be reclaimed back within 24hours of sale, MGXCHANGE will require the full amount that was paid to customer plus 5% administrative fee \n" +
                    "- MGXCHANGE is not liable for any damage that may occur during this period of storage\n" +
                    "\n" +
                    "This is a formal and legal agreement, please select accept to proceed. \n" +
                    "\n" +
                    "I have read, understood and I agree with the terms and condition.");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    mBinding.chkProductTerms.setChecked(true);
                    Toast.makeText(AddProductToSell.this,
                            "Thanks for Accepting Product Terms and Conditions",
                            Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    mBinding.chkProductTerms.setChecked(false);
                    Toast.makeText(AddProductToSell.this,
                            "You are Not Agreed to our Terms and Conditions",
                            Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();

        });

        BitmapDrawable drawable2 = (BitmapDrawable) mBinding.ivProductImage2.getDrawable();
        bitmapImage2 = drawable2.getBitmap();

        BitmapDrawable drawable3 = (BitmapDrawable) mBinding.ivProductImage3.getDrawable();
        bitmapImage3 = drawable3.getBitmap();

        BitmapDrawable drawable4 = (BitmapDrawable) mBinding.ivProductImage4.getDrawable();
        bitmapImage4 = drawable4.getBitmap();

        BitmapDrawable drawable5 = (BitmapDrawable) mBinding.ivProductImage5.getDrawable();
        bitmapImage5 = drawable5.getBitmap();

        BitmapDrawable drawable6 = (BitmapDrawable) mBinding.ivProductImage6.getDrawable();
        bitmapImage6 = drawable6.getBitmap();


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


        mBinding.ivProductImageChoose2.setOnClickListener(viewUploadImage2 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, IMAGE_REQUEST_TWO);
        });
        mBinding.ivProductImageChoose3.setOnClickListener(viewUploadImage3 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, IMAGE_REQUEST_THREE);
        });

        mBinding.ivProductImageChoose4.setOnClickListener(viewUploadImage4 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, IMAGE_REQUEST_FOUR);
        });

        mBinding.ivProductImageChoose5.setOnClickListener(viewUploadImage5 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, IMAGE_REQUEST_FIVE);
        });
        mBinding.ivProductImageChoose6.setOnClickListener(viewUploadImage6 -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, IMAGE_REQUEST_SIX);
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
                    == getResources().getDrawable(R.drawable.image_default)
                    .getConstantState()) {
                Toast.makeText(AddProductToSell.this,
                        "Select Product Image One",
                        Toast.LENGTH_SHORT).show();
            } else if(!mBinding.chkProductTerms.isChecked()){
                Toast.makeText(AddProductToSell.this,
                        "Please accept Product Terms and Conditions",
                        Toast.LENGTH_SHORT).show();
            }
//            else if (mBinding.ivProductImage2.getDrawable().getConstantState()
//                    == getResources().getDrawable(R.drawable.image_default).getConstantState()) {
//                Toast.makeText(AddProductToSell.this, "Select Product Two", Toast.LENGTH_SHORT).show();
//            } else if (mBinding.ivProductImage3.getDrawable().getConstantState()
//                    == getResources().getDrawable(R.drawable.image_default).getConstantState()) {
//                Toast.makeText(AddProductToSell.this, "Select Product Image Three", Toast.LENGTH_SHORT).show();
//            } else if (mBinding.ivProductImage4.getDrawable().getConstantState()
//                    == getResources().getDrawable(R.drawable.image_default).getConstantState()) {
//                Toast.makeText(AddProductToSell.this, "Select Product Image Four", Toast.LENGTH_SHORT).show();
//            } else if (mBinding.ivProductImage5.getDrawable().getConstantState()
//                    == getResources().getDrawable(R.drawable.image_default).getConstantState()) {
//                Toast.makeText(AddProductToSell.this, "Select Product Image Five", Toast.LENGTH_SHORT).show();
//            } else if (mBinding.ivProductImage6.getDrawable().getConstantState()
//                    == getResources().getDrawable(R.drawable.image_default).getConstantState()) {
//                Toast.makeText(AddProductToSell.this, "Select Product Image Six", Toast.LENGTH_SHORT).show();
//            }
            else {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmapImage1.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
                byte[] image1InByte = byteArrayOutputStream.toByteArray();
                String encodedImage1 = Base64.encodeToString(image1InByte, Base64.DEFAULT);
                Log.d(TAG, "Image Encoded 1 :" + encodedImage1);

                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                bitmapImage2.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream2);
                byte[] image2InByte = byteArrayOutputStream2.toByteArray();
                String encodedImage2 = Base64.encodeToString(image2InByte, Base64.DEFAULT);
                Log.d(TAG, "Image Encoded 2 :" + encodedImage2);

                ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
                bitmapImage3.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream3);
                byte[] image3InByte = byteArrayOutputStream3.toByteArray();
                String encodedImage3 = Base64.encodeToString(image3InByte, Base64.DEFAULT);
                Log.d(TAG, "Image Encoded 3 :" + encodedImage3);

                ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
                bitmapImage4.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream4);
                byte[] image4InByte = byteArrayOutputStream4.toByteArray();
                String encodedImage4 = Base64.encodeToString(image4InByte, Base64.DEFAULT);
                Log.d(TAG, "Image Encoded 4 :" + encodedImage4);

                ByteArrayOutputStream byteArrayOutputStream5 = new ByteArrayOutputStream();
                bitmapImage5.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream5);
                byte[] image5InByte = byteArrayOutputStream5.toByteArray();
                String encodedImage5 = Base64.encodeToString(image5InByte, Base64.DEFAULT);
                Log.d(TAG, "Image Encoded 5 :" + encodedImage5);

                ByteArrayOutputStream byteArrayOutputStream6 = new ByteArrayOutputStream();
                bitmapImage6.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream6);
                byte[] image6InByte = byteArrayOutputStream6.toByteArray();
                String encodedImage6 = Base64.encodeToString(image6InByte, Base64.DEFAULT);
                Log.d(TAG, "Image Encoded 6 :" + encodedImage6);

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
                                contactNumber, productDetails, encodedImage1,
                                encodedImage2, encodedImage3, encodedImage4,
                                encodedImage5, encodedImage6);
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
        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path1 = data.getData();
            try {
                bitmapImage1 = MediaStore.Images.Media.getBitmap(getContentResolver(), path1);
                mBinding.ivProductImage1.setImageBitmap(bitmapImage1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == IMAGE_REQUEST_TWO && resultCode == RESULT_OK && data != null){
            Uri path2 = data.getData();
            try {
                bitmapImage2 = MediaStore.Images.Media.getBitmap(getContentResolver(), path2);
                mBinding.ivProductImage2.setImageBitmap(bitmapImage2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == IMAGE_REQUEST_THREE && resultCode == RESULT_OK && data != null){
            Uri path3 = data.getData();
            try {
                bitmapImage3 = MediaStore.Images.Media.getBitmap(getContentResolver(), path3);
                mBinding.ivProductImage3.setImageBitmap(bitmapImage3);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == IMAGE_REQUEST_FOUR && resultCode == RESULT_OK && data != null){
            Uri path4 = data.getData();
            try {
                bitmapImage4 = MediaStore.Images.Media.getBitmap(getContentResolver(), path4);
                mBinding.ivProductImage4.setImageBitmap(bitmapImage4);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == IMAGE_REQUEST_FIVE && resultCode == RESULT_OK && data != null){
            Uri path5 = data.getData();
            try {
                bitmapImage5 = MediaStore.Images.Media.getBitmap(getContentResolver(), path5);
                mBinding.ivProductImage5.setImageBitmap(bitmapImage5);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == IMAGE_REQUEST_SIX && resultCode == RESULT_OK && data != null){
            Uri path6 = data.getData();
            try {
                bitmapImage6 = MediaStore.Images.Media.getBitmap(getContentResolver(), path6);
                mBinding.ivProductImage6.setImageBitmap(bitmapImage6);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
