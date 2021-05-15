package com.app.mgxchange.activities;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.mgxchange.R;
import com.app.mgxchange.models.LoanProductModel;
import com.app.mgxchange.utils.ApiUrls;
import com.app.mgxchange.webServices.MyNetwork;
import com.karan.churi.PermissionManager.PermissionManager;
import com.mikelau.croperino.Croperino;
import com.mikelau.croperino.CroperinoConfig;
import com.mikelau.croperino.CroperinoFileUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AddProductToGetLoan extends AppCompatActivity {

    private ImageView back;
    private ImageView ivChoose, ivImage;
    private EditText loanProductAmount, loanProductYear, loanProductContact, loanProductDetails;
    private Spinner spinnerLoanProductType, spinnerLoanProductCondition;
    private Button submitRecord;
    private ProgressDialog progressDialog;
    private static final String TAG = "TAG";
    private PermissionManager permission;
    private String mainImageUri = null;
    private String email, password;
    String selectedType, selectedCondition;
    private LoanProductModel loanProductModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_to_get_loan);
        back = findViewById(R.id.img_btn_back_get_loan);
        try {
            permission = new PermissionManager() {
            };
            permission.checkAndRequestPermissions(this);
            //initialize views
            initViews();
            //set up progress dialogue
            setupProgressDialog();
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), Dashboard.class);
                    startActivity(i);
                }
            });
            ArrayAdapter<CharSequence> productConditionSpinnerAdapter = ArrayAdapter.createFromResource(this,
                    R.array.item_condition, android.R.layout.simple_spinner_item);
            productConditionSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerLoanProductCondition.setAdapter(productConditionSpinnerAdapter);
            ArrayAdapter<CharSequence> productTypeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                    R.array.item_types, android.R.layout.simple_spinner_item);
            productTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerLoanProductType.setAdapter(productTypeSpinnerAdapter);
            //Submitting Record
            spinnerLoanProductCondition.setOnItemSelectedListener(
                    new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view,
                                                   int position, long id)
                        {
                            Log.e(TAG, "onItemSelected: " + parent.getId());
                            selectedCondition = parent.getItemAtPosition(position).toString();
//                            Toast.makeText(AddProductToGetLoan.this,
//                                    "ID: " + parent.getItemIdAtPosition(position),
//                                    Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "onItemSelected: selected" + selectedCondition);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    }
            );
            spinnerLoanProductType.setOnItemSelectedListener(
                    new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view,
                                                   int position, long id)
                        {
                            Log.e(TAG, "onItemSelected: " + parent.getId());
                            selectedType = parent.getItemAtPosition(position).toString();
//                            Toast.makeText(AddProductToGetLoan.this,
//                                    "ID: " + parent.getItemIdAtPosition(position),
//                                    Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "onItemSelected: selected" + selectedType);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    }
            );
            submitRecord.setOnClickListener(v -> {
                String amount = loanProductAmount.getText().toString();
                String year = loanProductYear.getText().toString();
                String contact = loanProductContact.getText().toString();
                String details = loanProductDetails.getText().toString();
                String condition = String.valueOf(selectedCondition);
                String type = String.valueOf(selectedType);
                if (type.equals("Select Product Type")) {
                    Toast.makeText(AddProductToGetLoan.this,
                            "Select Product Type", Toast.LENGTH_SHORT).show();
                    spinnerLoanProductType.requestFocus();
                }
                else if (TextUtils.isEmpty(amount) || amount.equals(" ")) {
                    loanProductAmount.setError("Enter Loan Amount");
                    loanProductAmount.requestFocus();
                } else if (TextUtils.isEmpty(year) || year.equals(" ")) {
                    loanProductYear.setError("Enter Year");
                    loanProductYear.requestFocus();
                } else if (TextUtils.isEmpty(contact) || contact.equals(" ")) {
                    loanProductContact.setError("Enter Contact Number");
                    loanProductContact.requestFocus();
                }
                else if (TextUtils.isEmpty(details) || details.equals(" ")) {
                    loanProductDetails.setError("Enter Product Details");
                    loanProductDetails.requestFocus();
                }
                else if (type.equals("Select Product Condition")) {
                    Toast.makeText(AddProductToGetLoan.this,
                            "Select Product Condition", Toast.LENGTH_SHORT).show();
                    spinnerLoanProductType.requestFocus();
                }
                else {
                loanProductModel = new LoanProductModel();
                loanProductModel.setProductAmount(loanProductAmount.getText().toString().trim());
                loanProductModel.setProductYear(loanProductYear.getText().toString().trim());
                loanProductModel.setProductContact(loanProductContact.getText().toString().trim());
                loanProductModel.setProductDetails(loanProductDetails.getText().toString().trim());
                loanProductModel.setProductCondition(String.valueOf(selectedCondition));
                loanProductModel.setProductType(String.valueOf(selectedType));
                progressDialog.show();
                new MyTask().execute();
            }
            });
            new CroperinoConfig("IMG_" + System.currentTimeMillis() + ".jpg", "/MGXChange/Pictures", "/sdcard/MGXChange/Pictures");
            CroperinoFileUtil.verifyStoragePermissions(AddProductToGetLoan.this);
            CroperinoFileUtil.setupDirectory(AddProductToGetLoan.this);

            //image choose
            ivChoose.setOnClickListener(v -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != getPackageManager().PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == getPackageManager().PERMISSION_GRANTED) {
                            new SweetAlertDialog(AddProductToGetLoan.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Select Image or Capture")
                                    .setConfirmText("Gallery")
                                    .setConfirmClickListener( new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            sweetAlertDialog.dismissWithAnimation();
                                            Croperino.prepareGallery(AddProductToGetLoan.this);
                                        }
                                    })
                                    .setCancelText("Camera")
                                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            sweetAlertDialog.dismissWithAnimation();
                                            Croperino.prepareCamera(AddProductToGetLoan.this);
                                        }
                                    })
                                    .show();


                        }
                    } else {
                        new SweetAlertDialog(AddProductToGetLoan.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Select Image or Capture")
                                .setConfirmText("Gallery")
                                        .setConfirmClickListener(
                                        new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismissWithAnimation();
                                        Croperino.prepareGallery(AddProductToGetLoan.this);
                                    }
                                })
                                .setCancelText("Camera")
                                .setCancelClickListener( new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismissWithAnimation();
                                        Croperino.prepareCamera(AddProductToGetLoan.this);
                                    }
                                })
                                .show();
                    }
                }
            });

        } catch (Exception e) {
            Log.e(TAG, "onCreate: " + e.toString());
        }
    }

    private class MyTask extends AsyncTask<Void, Void, String> {
        SweetAlertDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e(TAG, "onPreExecute: ");
        }

        @Override
        protected String doInBackground(Void... uri) {
            try {
                Bitmap selectedImage = ((BitmapDrawable) ivImage.getDrawable()).getBitmap();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] b = baos.toByteArray();
                String base64EncodingString = Base64.encodeToString(b, Base64.DEFAULT);
                return base64EncodingString;
            } catch (Exception ex) {
                Log.e(TAG, "doInBackground: " + ex.toString());
                return null;
            }

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("product_amount", loanProductModel.getProductAmount());
                    jsonObject.put("product_year", loanProductModel.getProductYear());
                    jsonObject.put("product_contact", loanProductModel.getProductContact());
                    jsonObject.put("product_details", loanProductModel.getProductDetails());
                    jsonObject.put("product_type", loanProductModel.getProductType());
                    jsonObject.put("product_condition", loanProductModel.getProductCondition());
                    jsonObject.put("product_image", result);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                insertProductRecord(jsonObject);
            }
        }
    }

    private void insertProductRecord(JSONObject product) {
        try {
            JsonObjectRequest insertRequest = new JsonObjectRequest(Request.Method.POST,
                    ApiUrls.SubmitProduct,
                    product,
                    response -> {
                        progressDialog.dismiss();
                        // HelpingFunctions.stopLoading();
                        Log.e(TAG, "onResponse: Response: " + response.toString());
                        try {
                            Toast.makeText(this, "Response" + response.toString(),
                                    Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            // try {
                            new SweetAlertDialog(AddProductToGetLoan.this,
                                    SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Exception...")
                                    .setContentText(e.toString())
                                    .show();

                            Log.e(TAG, "onResponse: EXP: " + e.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e(TAG, "onErrorResponse: " + error.toString());
                            progressDialog.dismiss();
                            new SweetAlertDialog(AddProductToGetLoan.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Exception...")
                                    .setContentText("" + error.toString())
                                    .show();


                        }
                    });
            MyNetwork.getInstance(AddProductToGetLoan.this).addToRequestQueue(insertRequest);

        } catch (Exception ex) {
            Log.e(TAG, "insertProductRecord: " + ex.toString());
        }

    }

    //set up progress dialogue
    private void setupProgressDialog() {
        progressDialog = new ProgressDialog(AddProductToGetLoan.this);
        progressDialog.setTitle("Adding Product...");
        progressDialog.setMessage("Please wait while we are uploading your product");
        progressDialog.setCancelable(false);
    }

    //Initialize View
    private void initViews() {
        try {
            ivChoose = findViewById(R.id.iv_loan_product_choose);
            ivImage = findViewById(R.id.iv_loan_product_image);
            loanProductAmount = findViewById(R.id.et_loan_product_amount);
            loanProductYear = findViewById(R.id.et_loan_product_year);
            loanProductContact = findViewById(R.id.et_loan_product_phone_number);
            loanProductDetails = findViewById(R.id.et_loan_product_details);
            spinnerLoanProductType = findViewById(R.id.sp_loan_product_type);
            spinnerLoanProductCondition = findViewById(R.id.sp_loan_product_condition);
            submitRecord = findViewById(R.id.btn_loan_product_submit);
        } catch (Exception e) {
            Log.e(TAG, "initViews: " + e.toString());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CroperinoConfig.REQUEST_TAKE_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    /* Parameters of runCropImage = File,
                     Activity Context, Image is Scalable or Not,
                      Aspect Ratio X, Aspect Ratio Y, Button Bar Color, Background Color */
                    Croperino.runCropImage(CroperinoFileUtil.getTempFile(),
                            AddProductToGetLoan.this, true, 1,
                            1, R.color.gray, R.color.gray_variant);
                    mainImageUri = "Success";
                }
                break;
            case CroperinoConfig.REQUEST_PICK_FILE:
                if (resultCode == Activity.RESULT_OK) {
                    CroperinoFileUtil.newGalleryFile(data, AddProductToGetLoan.this);
                    Croperino.runCropImage(CroperinoFileUtil.getTempFile(),
                            AddProductToGetLoan.this, true,
                            0, 0, R.color.gray, R.color.gray_variant);
                    mainImageUri = "Success";
                }
                break;
            case CroperinoConfig.REQUEST_CROP_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    Uri i = Uri.fromFile(CroperinoFileUtil.getTempFile());
                    ivImage.setImageURI(i);
                    mainImageUri = "Success";
                }
                break;
            default:
                break;
        }
    }

    //show permission manager dialogue
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}