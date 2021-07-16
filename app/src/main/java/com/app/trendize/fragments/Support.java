package com.app.trendize.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.app.trendize.R;

public class Support extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_support, container, false);
//        supportCall = view.findViewById(R.id.maemes_support);
//        CallUsText = view.findViewById(R.id.call_us_at);
//        supportCall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                intent.setData(Uri.parse("tel:02084465998"));
//                startActivity(intent);
//            }
//        });
        return view;
    }

}
