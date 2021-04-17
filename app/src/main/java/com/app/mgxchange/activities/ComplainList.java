package com.app.mgxchange.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.mgxchange.adapters.ComplainListAdapter;
import com.app.mgxchange.databinding.ActivityComplainListBinding;
import com.app.mgxchange.models.ComplainListModel;
import com.app.mgxchange.utils.ApiUrls;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ComplainList extends AppCompatActivity {
    String TAG = "ComplainList";
    private ActivityComplainListBinding mBinding;
    ComplainListAdapter adapter;
    ArrayList<ComplainListModel> list = new ArrayList<>();
    String userID;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityComplainListBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        mBinding.recyclerViewComplainList.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new ComplainListAdapter(this, list);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        SharedPreferences prefPersonalUser = getSharedPreferences("userData", MODE_PRIVATE);
        userID = prefPersonalUser.getString("user_id", null);
        Toast.makeText(getApplicationContext(), userID, Toast.LENGTH_LONG).show();
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        url = ApiUrls.SeeComplainList + "?user_id=" + userID;
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                response -> {
                    progressDialog.dismiss();
                    try {
                        JSONObject object1 = new JSONObject(response);
                        JSONArray object2 = object1.getJSONArray("Data");
                        for (int i = 0; i <= object2.length(); i++) {
                            JSONObject json = object2.getJSONObject(i);
                            String user_id = json.getString("UserID");
                            String complain_id = json.getString("ComplainID");
                            String product_name = json.getString("ProductName");
                            String complain_message = json.getString("ComplainMessage");
                            String complain_reference = json.getString("Reference");
                            String complain_contact = json.getString("ComplainContact");
                            String complain_date = json.getString("ComplainDate");
                            String serial_number = json.getString("SerialNumber");
                            String product_details = json.getString("ProductDetails");
                            String complain_status = json.getString("ComplainStatus");
                            list.add(new ComplainListModel(Integer.parseInt(user_id), Integer.parseInt(complain_id),
                                    product_name, complain_message, complain_reference, complain_contact, complain_date,
                                    serial_number, product_details, complain_status));
                            if (adapter != null)
                                adapter.notifyDataSetChanged();
                        }
                        if (adapter != null)
                            adapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        Log.d(TAG, "Exception" + e);
                    }
                }, error -> {
            Log.d(TAG, "Error");

        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
        mBinding.recyclerViewComplainList.setAdapter(adapter);

        //Back Button
        mBinding.imgBtnBackComplainList.setOnClickListener(view1 -> {
            Intent i = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(i);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}