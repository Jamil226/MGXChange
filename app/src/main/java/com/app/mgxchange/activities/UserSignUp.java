package com.app.mgxchange.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.mgxchange.R;
import com.app.mgxchange.utils.ApiUrls;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class UserSignUp extends AppCompatActivity {
    EditText firstName, lastName, contact, address, email, password, confirmPassword;
    Button signUp;
    TextView signIn;
    final String SIGN_UP_METHOD_CODE = "1";
    ProgressDialog progressDialog;
//    final int CODE_GALLERY_REQUEST = 999;
//    Bitmap bitmap;
//    CircularImageView ivProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
        initViewsSignUp();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpValidation();
            }
        });

//        ivProfileImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ActivityCompat.requestPermissions(
//                        UserSignUp.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        CODE_GALLERY_REQUEST
//                );
//
//            }
//        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        UserLogin.class);
                startActivity(intent);
            }
        });
    }

    public void initViewsSignUp() {
        firstName = findViewById(R.id.et_user_sign_up_first_name);
        lastName = findViewById(R.id.et_user_sign_up_last_name);
        contact = findViewById(R.id.et_user_sign_up_contact_number);
        address = findViewById(R.id.et_user_sign_up_address);
        email = findViewById(R.id.et_user_sign_up_email);
        password = findViewById(R.id.et_user_sign_up_password);
        confirmPassword = findViewById(R.id.et_user_sign_up_cp);
//        ivProfileImage = findViewById(R.id.iv_user_sign_up_profile_image);
        signIn = findViewById(R.id.tv_user_sign_up_login);
        signUp = findViewById(R.id.btn_user_sign_up);


    }

    public void signUpValidation() {
        String fName = firstName.getText().toString();
        String lName = lastName.getText().toString();
        String phone = contact.getText().toString();
        String userAddress = address.getText().toString();
        String mail = email.getText().toString();
        String pass = password.getText().toString();
        String cPass = confirmPassword.getText().toString();
        if (TextUtils.isEmpty(fName) || fName.equals(" ")) {
            firstName.setError("Enter First Name");
            firstName.requestFocus();
        } else if (TextUtils.isEmpty(lName) || lName.equals(" ")) {
            lastName.setError("Enter Last Name");
            lastName.requestFocus();
        } else if (TextUtils.isEmpty(phone) || phone.equals(" ")) {
            contact.setError("Enter Contact Number");
            contact.requestFocus();
        } else if (TextUtils.isEmpty(userAddress) || userAddress.equals(" ")) {
            address.setError("Enter Address");
            address.requestFocus();
        } else if (TextUtils.isEmpty(mail) || mail.equals(" ")) {
            email.setError("Enter Email Address");
            email.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("Invalid Email Address");
            email.requestFocus();
        } else if (TextUtils.isEmpty(pass) || pass.length() < 8) {
            password.setError("Please Input a Valid Password Longer" +
                    " Than 7 Characters");
            password.requestFocus();
        } else if (TextUtils.isEmpty(cPass) || cPass.equals(" ")) {
            password.setError("Enter Confirm Password");
            password.requestFocus();
        } else if (!pass.matches(cPass)) {
            password.setError("Passwords Not Matched");
            password.requestFocus();
        } else {
            userSignUp();
        }
    }

    public void userSignUp() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        final String userFirstName = firstName.getText().toString().trim();
        final String userLastName = lastName.getText().toString().trim();
        final String userPassword = password.getText().toString().trim();
        final String userContact = contact.getText().toString().trim();
        final String addressUser = address.getText().toString().trim();
        final String userEmail = email.getText().toString().trim();

        Uri.Builder builder = Uri.parse(ApiUrls.RegisterUser).buildUpon();
        builder.appendQueryParameter("firstname", userFirstName);
        builder.appendQueryParameter("lastname", userLastName);
        builder.appendQueryParameter("method", SIGN_UP_METHOD_CODE);
        builder.appendQueryParameter("contact", userContact);
        builder.appendQueryParameter("address", addressUser);
        builder.appendQueryParameter("email", userEmail);
        builder.appendQueryParameter("password", userPassword);

//        final String imageData = imageToString(bitmap);
//        builder.appendQueryParameter("imagepath", imageData);
        String urlEncrypted = builder.build().toString();

        String url = ApiUrls.RegisterUser;
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                urlEncrypted, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    Log.d("ApiResponse", response);
                    JSONObject json = new JSONObject(response);
                    String loginStatus = json.getString("success").trim();
                    String id = json.getString("userid").trim();
                    String emailAddress = json.getString("email").trim();
                    String contact = json.getString("contact").trim();
                    String firstName = json.getString("firstname").trim();
                    String lastName = json.getString("lastname").trim();
                    String loginMessage = json.getString("message").trim();
                    if (loginStatus.equals("1")) {
                        Toast.makeText(getApplicationContext(),
                                "Login Success",
                                Toast.LENGTH_LONG).show();
                        SharedPreferences preferences = getSharedPreferences("userData", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("user_id", id);
                        editor.putString("email", emailAddress);
                        editor.putString("firstName", firstName);
                        editor.putString("lastName", lastName);
                        editor.putString("contact", contact);
                        editor.apply();
                        finish();
                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Email or Password Incorrect",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toasty.error(getApplicationContext(),
                                "Error:" + error.toString(),
                                Toast.LENGTH_LONG, true).show();
                        Log.d("ApiError", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("firstname", userFirstName);
                params.put("lastname", userLastName);
                params.put("method", SIGN_UP_METHOD_CODE);
                params.put("contact", userContact);
                params.put("address", addressUser);
                params.put("email", userEmail);
                params.put("password", userPassword);
//                String imageData = imageToString(bitmap);
//                params.put("imagepath", imageData);
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(UserSignUp.this);
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);
    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        if (requestCode == CODE_GALLERY_REQUEST) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(Intent.createChooser(intent, "Select an Image"),
//                        CODE_GALLERY_REQUEST);
//            } else {
//                Toasty.error(getApplicationContext(),
//                        "You Don't Have Permission to Access Gallery",
//                        Toast.LENGTH_LONG, true).show();
//            }
//            return;
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == CODE_GALLERY_REQUEST && resultCode == RESULT_OK && data != null) {
//            Uri filePath = data.getData();
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(filePath);
//                bitmap = BitmapFactory.decodeStream(inputStream);
//                ivProfileImage.setImageBitmap(bitmap);
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }


//    private String imageToString(Bitmap bitmap) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
//        byte[] imageBytes = outputStream.toByteArray();
//        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
//
//    }
}