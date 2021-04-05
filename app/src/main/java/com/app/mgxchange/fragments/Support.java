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

public class Support extends Fragment {
//    TextView supportCall, CallUsText;

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
