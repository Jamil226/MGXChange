package com.app.mgxchange.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.app.mgxchange.R;


public class PrivacyPolicy extends Fragment {

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_privacy_policy,
                container, false);
        if (Build.VERSION.SDK_INT >= 21) {
            getActivity().getWindow().setNavigationBarColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryDark));
        }

        return view;
    }
}