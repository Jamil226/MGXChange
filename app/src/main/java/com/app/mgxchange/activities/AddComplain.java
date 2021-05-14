package com.app.mgxchange.activities;

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

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.mgxchange.R;
import com.app.mgxchange.databinding.ActivityAddComplainBinding;
import com.app.mgxchange.models.ProductComplainModel;
import com.app.mgxchange.utils.ApiUrls;
import com.app.mgxchange.webServices.MyNetwork;

import org.json.JSONException;
import org.json.JSONObject;

public class AddComplain extends AppCompatActivity {
    private static final String TAG = "AddComplainActivity";
    private ActivityAddComplainBinding mBinding;
    private String selectedType;
    private ProgressDialog progressDialog;
    private ProductComplainModel model;
    String userID = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityAddComplainBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        ArrayAdapter<CharSequence> productTypeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.item_types, android.R.layout.simple_spinner_item);
        productTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBinding.spCustomerComplainProductType.setAdapter(productTypeSpinnerAdapter);
        mBinding.spCustomerComplainProductType.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long id) {
//                        Log.e(TAG, "onItemSelected: " + parent.getId());
                        selectedType = parent.getItemAtPosition(position).toString();
//                        Log.e(TAG, "onItemSelected: selected" + selectedType);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

        mBinding.btnCustomerComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitComplain();
            }
        });
        mBinding.imgBtnBack.setOnClickListener(view1 -> {
            Intent i = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(i);
        });
    }

    public void submitComplain() {
        String serialNumber = mBinding.etCustomerComplainSerialOrImei.getText().toString();
        String complainDate = mBinding.etCustomerComplainDate.getText().toString();
        String contactNumber = mBinding.etCustomerComplainContact.getText().toString();
        String productDetails = mBinding.etCustomerProductDetails.getText().toString();
        String complainMessage = mBinding.etCustomerComplainMessage.getText().toString();
        if (TextUtils.isEmpty(serialNumber) || serialNumber.equals(" ")) {
            mBinding.etCustomerComplainSerialOrImei.setError("Enter Serial Number");
            mBinding.etCustomerComplainSerialOrImei.requestFocus();
        } else if (TextUtils.isEmpty(complainDate) || complainDate.equals(" ")) {
            mBinding.etCustomerComplainDate.setError("Enter Complain Date");
            mBinding.etCustomerComplainDate.requestFocus();
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
            progressDialog = new ProgressDialog(AddComplain.this);
            progressDialog.setTitle("Submitting Complain...");
            progressDialog.setMessage("Please wait while we are updating your complain");
            progressDialog.setCancelable(false);
            model = new ProductComplainModel();
            model.setUserID(String.valueOf(userID));
            model.setSerialNo(mBinding.etCustomerComplainSerialOrImei.getText().toString().trim());
            model.setComplainDate(mBinding.etCustomerComplainDate.getText().toString().trim());
            model.setContact(mBinding.etCustomerComplainContact.getText().toString().trim());
            model.setProductDetails(mBinding.etCustomerProductDetails.getText().toString().trim());
            model.setProductType(String.valueOf(selectedType));
            model.setMessage(mBinding.etCustomerComplainMessage.getText().toString().trim());
            progressDialog.show();
            submitComplainData();
        }
    }

    public void submitComplainData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("user_id", model.getUserID());
            jsonObject.put("user_contact", model.getContact());
            jsonObject.put("product_type", model.getProductType());
            jsonObject.put("product_serial", model.getSerialNo());
            jsonObject.put("product_details", model.getProductDetails());
            jsonObject.put("complain_date", model.getComplainDate());
            jsonObject.put("complain_message", model.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                ApiUrls.SubmitComplain,
                jsonObject, response -> {
            progressDialog.dismiss();
            try {
                Log.d(TAG, "Response : " + response);

                String responseStatus = response.getString("status").trim();
                String message = response.getString("message").trim();
                boolean status = Boolean.parseBoolean(responseStatus);
                if (status) {
                    Toast.makeText(AddComplain.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), Dashboard.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(AddComplain.this, "Error", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            progressDialog.dismiss();
            Log.i(TAG, "onErrorResponse: SelectActivity  " + error);
            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                Toast.makeText(AddComplain.this, "Internet Connection Issue", Toast.LENGTH_SHORT).show();
            } else if (error instanceof AuthFailureError) {
                Toast.makeText(AddComplain.this, "Auth Failure Issue", Toast.LENGTH_SHORT).show();
                //TODO
            } else if (error instanceof ServerError) {
                Toast.makeText(AddComplain.this, "Server Not Responding ", Toast.LENGTH_SHORT).show();
                //TODO
            } else if (error instanceof NetworkError) {
                Toast.makeText(AddComplain.this, "Network Connection Issue", Toast.LENGTH_SHORT).show();
                //TODO
            }
        });

        MyNetwork.getInstance(AddComplain.this).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}