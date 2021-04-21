package com.app.mgxchange.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class UserLogin extends AppCompatActivity {
    Button signIn;
    EditText email, password;
    TextView forgotPassword, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        initViewsLogin();
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginValidation();
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        ForgetPassword.class);
                startActivity(intent);

            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),
                        UserSignUp.class);
                startActivity(intent);
            }
        });
    }

    public void loginValidation() {
        String mail = email.getText().toString();
        String pass = password.getText().toString();

        if (TextUtils.isEmpty(mail) || mail.equals(" ")) {
            email.setError("Enter Email Address");
            email.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("Invalid Email Address");
            email.requestFocus();
        } else if (TextUtils.isEmpty(pass) || pass.length() < 8) {
            password.setError("Please Input a Valid Password Longer" +
                    " Than 7 Characters");
            password.requestFocus();
        } else {
            loginUser();
        }
    }

    public void loginUser() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        Uri.Builder builder = Uri.parse(ApiUrls.LoginUser).buildUpon();
        builder.appendQueryParameter("email", email.getText().toString());
        builder.appendQueryParameter("password", password.getText().toString());
        String urlEncrypted = builder.build().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                urlEncrypted, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject json = new JSONObject(response);
                    String loginStatus = json.getString("success").trim();
                    String id = json.getString("userid").trim();
                    String username = json.getString("username").trim();
                    String emailAddress = json.getString("email").trim();
                    String contact = json.getString("contact").trim();
                    String firstName = json.getString("firstname").trim();
                    String lastName = json.getString("lastname").trim();
                    String address = json.getString("address").trim();
                    String imagePath = json.getString("imagepath").trim();
                    String loginMessage = json.getString("message").trim();
                    if (loginStatus.equals("1")) {
                        Toast.makeText(UserLogin.this,
                                "Login Success",
                                Toast.LENGTH_LONG).show();
                        SharedPreferences preferences = getSharedPreferences("userData", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("user_id", id);
                        editor.putString("email", emailAddress);
                        editor.putString("firstName", firstName);
                        editor.putString("lastName", lastName);
                        editor.putString("address", address);
                        editor.putString("contact", contact);
                        editor.putString("imagePath", imagePath);
                        editor.apply();
                        finish();
                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(UserLogin.this,
                                "Email or Password Incorrect",
                                Toast.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserLogin.this,
                        "Exception Found: " + error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("password", email.getText().toString());
                params.put("password", password.getText().toString());
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "text/xml");
                return headers;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(UserLogin.this);
        queue.add(stringRequest);
    }

    public void initViewsLogin() {
        email = findViewById(R.id.et_user_login_email);
        password = findViewById(R.id.et_user_login_password);
        forgotPassword = findViewById(R.id.tv_user_sign_in_fp);
        signUp = findViewById(R.id.tv_user_sign_in_ca);
        signIn = findViewById(R.id.btn_user_sing_in);

    }
}