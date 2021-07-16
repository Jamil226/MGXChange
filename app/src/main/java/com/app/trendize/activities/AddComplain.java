package com.app.trendize.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.trendize.R;
import com.app.trendize.databinding.ActivityAddComplainBinding;
import com.app.trendize.models.ProductComplainModel;
import com.app.trendize.models.UserAddProductComplainResponse;
import com.app.trendize.sharedPrefs.UserSharedPrefManager;
import com.app.trendize.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddComplain extends AppCompatActivity {
    private static final String TAG = "AddComplainActivity";
    private ActivityAddComplainBinding mBinding;
    private String selectedType;
    private ProductComplainModel model;
    String userID;

    UserSharedPrefManager userSharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityAddComplainBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        userSharedPrefManager = new UserSharedPrefManager(getApplicationContext());
        String userID = userSharedPrefManager.getUser().getUserID();
        Log.d(TAG, "User ID = " + userID);
        ArrayAdapter<CharSequence> productTypeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.item_types, android.R.layout.simple_spinner_item);
        productTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBinding.spCustomerComplainProductType.setAdapter(productTypeSpinnerAdapter);
        mBinding.spCustomerComplainProductType.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long id) {
                        Log.e(TAG, "onItemSelected: " + parent.getId());
                        selectedType = parent.getItemAtPosition(position).toString();
                        Log.e(TAG, "onItemSelected: selected" + selectedType);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

        mBinding.btnCustomerComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = selectedType;
                String serialNumber = mBinding.etCustomerComplainSerialOrImei.getText().toString();
                String productYear = mBinding.etProductYear.getText().toString();
                String contactNumber = mBinding.etCustomerComplainContact.getText().toString();
                String productDetails = mBinding.etCustomerProductDetails.getText().toString();
                String complainMessage = mBinding.etCustomerComplainMessage.getText().toString();
                if (TextUtils.isEmpty(serialNumber) || serialNumber.equals(" ")) {
                    mBinding.etCustomerComplainSerialOrImei.setError("Enter Serial Number");
                    mBinding.etCustomerComplainSerialOrImei.requestFocus();
                } else if (TextUtils.isEmpty(productYear) || productYear.equals(" ")) {
                    mBinding.etProductYear.setError("Enter Product Year");
                    mBinding.etProductYear.requestFocus();
                } else if (TextUtils.isEmpty(contactNumber) || contactNumber.equals(" ")) {
                    mBinding.etCustomerComplainContact.setError("Enter Contact Number");
                    mBinding.etCustomerComplainContact.requestFocus();
                } else if (TextUtils.isEmpty(productDetails) || productDetails.equals(" ")) {
                    mBinding.etCustomerProductDetails.setError("Enter Product Details");
                    mBinding.etCustomerProductDetails.requestFocus();
                } else if (TextUtils.isEmpty(complainMessage) || complainMessage.equals(" ")) {
                    mBinding.etCustomerComplainMessage.setError("Enter Product Details");
                    mBinding.etCustomerComplainMessage.requestFocus();
                } else {
                    final ProgressDialog progressDialog = new ProgressDialog(AddComplain.this);
                    progressDialog.setTitle("Submitting Complain...");
                    progressDialog.setMessage("Please wait while we are updating your complain");
                    progressDialog.show();
                    progressDialog.setCancelable(false);

                    Call<UserAddProductComplainResponse> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .addProductComplain(userID, productName, serialNumber,
                                    productYear, contactNumber, productDetails, complainMessage);
                    call.enqueue(new Callback<UserAddProductComplainResponse>() {
                        @Override
                        public void onResponse(Call<UserAddProductComplainResponse> call,
                                               Response<UserAddProductComplainResponse> response) {
                            progressDialog.dismiss();
                            if (response.isSuccessful()) {
                                Toast.makeText(AddComplain.this,
                                        response.body().getComplainMessage(), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), Dashboard.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(AddComplain.this,
                                        response.body().getComplainMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserAddProductComplainResponse> call,
                                              Throwable t) {
                            progressDialog.dismiss();
                            Log.d(TAG, t.getMessage());
                        }
                    });
                }
            }
        });
        mBinding.imgBtnBack.setOnClickListener(viewImgBtnBack -> {
            Intent i = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(i);
        });
    }

}