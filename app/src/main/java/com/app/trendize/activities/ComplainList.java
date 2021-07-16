package com.app.trendize.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.app.trendize.adapters.UserComplainsAdapter;
import com.app.trendize.databinding.ActivityComplainListBinding;
import com.app.trendize.models.ComplainListResponse;
import com.app.trendize.models.Complains;
import com.app.trendize.sharedPrefs.UserSharedPrefManager;
import com.app.trendize.utils.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComplainList extends AppCompatActivity {
    String TAG = "ComplainList";
    private ActivityComplainListBinding mBinding;
    List<Complains> complainsList;
    String userID;
    UserSharedPrefManager userSharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityComplainListBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        mBinding.recyclerViewComplainList.setHasFixedSize(true);
        mBinding.recyclerViewComplainList
                .setLayoutManager(new GridLayoutManager(this, 1));

        userSharedPrefManager = new UserSharedPrefManager(getApplicationContext());
        userID = userSharedPrefManager.getUser().getUserID();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Fetching Complains");
        progressDialog.setMessage("Please Wait for a While...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        Call<ComplainListResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getComplainList(userID);
        call.enqueue(new Callback<ComplainListResponse>() {
            @Override
            public void onResponse(Call<ComplainListResponse> call,
                                   Response<ComplainListResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    complainsList = response.body().getComplainsList();
                    Log.d(TAG, "Response"+ complainsList);
                    mBinding.recyclerViewComplainList.setAdapter(
                            new UserComplainsAdapter(getApplicationContext(), complainsList));
                } else {
                    Toast.makeText(ComplainList.this, "Error : " +
                            response.body().getStatusCode(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ComplainListResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.d(TAG, t.getMessage());
            }
        });

        //Back Button
        mBinding.imgBtnBackComplainList.setOnClickListener(view1 -> {
            Intent i = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(i);
        });
    }

}