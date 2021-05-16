package com.app.mgxchange.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.app.mgxchange.R;
import com.app.mgxchange.databinding.ActivityAddProductToSellBinding;
import com.app.mgxchange.sharedPrefs.UserSharedPrefManager;

public class AddProductToSell extends AppCompatActivity {
    private static final String TAG = "AddProductToSell";
    UserSharedPrefManager userSharedPrefManager;
    String userID;
    ActivityAddProductToSellBinding mBinding;
    String selectedType, selectedCondition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityAddProductToSellBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        userSharedPrefManager = new UserSharedPrefManager(getApplicationContext());
        userID = userSharedPrefManager.getUser().getUserID();
//        Toast.makeText(this, "ID " +userID, Toast.LENGTH_SHORT).show();

        //Spinner Adapters
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
                        selectedCondition = parent.getItemAtPosition(position).toString();
                        Log.e(TAG, "onItemSelected: selected" + selectedType);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) { }
                }
        );
        mBinding.spProductCondition.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Log.e(TAG, "onItemSelected: " + parent.getId());
                        selectedType = parent.getItemAtPosition(position).toString();
                        Log.e(TAG, "onItemSelected: selected" + selectedCondition);
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) { }
                }
        );
        onSubmitProduct();

    }
    private void onSubmitProduct(){
        productValidation();
        productSubmission();
    }

    private void productValidation(){

    }
    private void productSubmission(){

    }
}