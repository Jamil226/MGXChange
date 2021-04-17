package com.app.mgxchange.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.mgxchange.R;

public class LoanOrSellSelection extends AppCompatActivity {
    ImageView back;
    Button getLoan, sellProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_or_sell_selection);
        back = findViewById(R.id.img_btn_back_loan_or_sell);
        getLoan = findViewById(R.id.btn_get_loan);
        sellProduct = findViewById(R.id.btn_sell_product);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(i);
            }
        });
        getLoan.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), GetLoanUsingProduct.class);
            startActivity(i);
        });
        sellProduct.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(i);
        });



    }
}