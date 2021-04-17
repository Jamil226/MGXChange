package com.app.mgxchange.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.app.mgxchange.R;
import com.app.mgxchange.activities.AddComplain;
import com.app.mgxchange.activities.ComplainList;
import com.app.mgxchange.activities.DetailedUserProfile;
import com.app.mgxchange.activities.ItemsList;
import com.app.mgxchange.activities.LoanOrSellSelection;
import com.app.mgxchange.activities.Welcome;

import es.dmoral.toasty.Toasty;

public class Home extends Fragment {
    View view;
    CardView loanOrSell, trackMyItems, userProfile, productComplain, productComplainList, userLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().setNavigationBarColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryDark));
        }
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initViews();

        loanOrSell.setOnClickListener(view -> {
            Intent i = new Intent(getContext(), LoanOrSellSelection.class);
            startActivity(i);
        });

        trackMyItems.setOnClickListener(view -> {
            Intent i = new Intent(getContext(), ItemsList.class);
            startActivity(i);
        });

        userProfile.setOnClickListener(view -> {
            Intent i = new Intent(getContext(), DetailedUserProfile.class);
            startActivity(i);
        });

        productComplain.setOnClickListener(view -> {
            Intent i = new Intent(getContext(), AddComplain.class);
            startActivity(i);
        });

        productComplainList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ComplainList.class);
                startActivity(i);
            }
        });

        userLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("email", "null");
                editor.putString("firstName", "null");
                editor.putString("lastName", "null");
                editor.putString("contact", "null");
                editor.putString("address", "null");
                editor.apply();
                Toasty.success(getContext(),
                        "Logout Success",
                        Toasty.LENGTH_LONG, true).show();
                getActivity().finish();
                Intent i = new Intent(getContext(), Welcome.class);
                startActivity(i);
            }
        });

        return  view;
    }
    private void initViews() {
        loanOrSell = view.findViewById(R.id.cv_fragment_home_loan_product);
        trackMyItems = view.findViewById(R.id.cv_fragment_home_track_items);
        userProfile = view.findViewById(R.id.cv_fragment_home_profile);
        productComplain = view.findViewById(R.id.cv_fragment_home_product_lost_complain);
        productComplainList = view.findViewById(R.id.cv_fragment_home_complain_list);
        userLogout = view.findViewById(R.id.cv_fragment_home_logout);
    }
}
