package com.app.mgxchange.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.app.mgxchange.R;


public class About extends Fragment {
//    private TextView phone, about, p1, p2, p3, contactInfo, about_address, telephone_title;
    public About() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_about, container, false);

//        phone = view.findViewById(R.id.maemes_phone);
//        about = view.findViewById(R.id.tv_about_maemes);
//        p1 = view.findViewById(R.id.tv_av_p_one);
//        p2 = view.findViewById(R.id.tv_av_p_two);
//        p3 = view.findViewById(R.id.tv_av_p_three);
//        contactInfo = view.findViewById(R.id.tv_about_contact_info);
//        about_address = view.findViewById(R.id.tv_about_address);
//        telephone_title = view.findViewById(R.id.tv_about_telephone_title);
//        phone.setOnClickListener(new View.OnClickListener() {
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
