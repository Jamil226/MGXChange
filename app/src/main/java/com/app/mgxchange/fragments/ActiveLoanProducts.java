package com.app.mgxchange.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.mgxchange.R;
import com.app.mgxchange.adapters.UserActiveLoanProductsAdapter;
import com.app.mgxchange.models.ActiveLoanProductListResponse;
import com.app.mgxchange.models.Products;
import com.app.mgxchange.sharedPrefs.UserSharedPrefManager;
import com.app.mgxchange.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ActiveLoanProducts extends Fragment {
    private static final String TAG = "ActiveLoanProductsFragment";
    View view;
    RecyclerView recyclerView;
    List<Products> productsList = new ArrayList<>();
    UserSharedPrefManager userSharedPrefManager;
    String userID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_active_loan_products, container, false);
        userSharedPrefManager = new UserSharedPrefManager(getContext());
        userID = userSharedPrefManager.getUser().getUserID();
        recyclerView = view.findViewById(R.id.rv_user_active_loan_products);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        final ProgressDialog progressDialog = new ProgressDialog(getContext());

//        productsList.add(new Products("1","1","1","1","1","1","1","1","1","1","1","1","1", "1", "1", "1", "1", "1", "1","1", "1"));
        progressDialog.setTitle("Fetching Products");
        progressDialog.setMessage("Please Wait for a while...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        Call<ActiveLoanProductListResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getLoanProductList(userID);
        call.enqueue(new Callback<ActiveLoanProductListResponse>() {
            @Override
            public void onResponse(Call<ActiveLoanProductListResponse> call,
                                   Response<ActiveLoanProductListResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    productsList = response.body().getProductsList();
                    recyclerView.setAdapter(new
                            UserActiveLoanProductsAdapter(getContext(), productsList));
                } else {
                    Toast.makeText(getContext(),
                            response.body().getStatusCode(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ActiveLoanProductListResponse> call,
                                  Throwable t) {
                progressDialog.dismiss();
                Log.d(TAG, t.getMessage());
            }
        });
        return view;
    }
}