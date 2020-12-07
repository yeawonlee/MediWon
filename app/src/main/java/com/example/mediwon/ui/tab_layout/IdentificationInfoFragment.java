package com.example.mediwon.ui.tab_layout;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mediwon.R;

public class IdentificationInfoFragment extends Fragment {

    private String imageUrl;    // 큰 제품 이미지
    private ImageView imageView;    // 큰 제품 이미지
    private TextView nameTextView;  // 품목명
    private TextView engNameTextView;   // 제품영문명
    private TextView itemSeqTextView;   // 품목 일련번호
    private TextView enterpriseTextView;    // 업체명
    private TextView classTextView;     // 분류번호, 분류명
    private TextView etcOtcNameTextView;    // 전문/일반
    private TextView ediCodeTextView;   // 보험코드

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_identification_info, container, false);

        nameTextView = rootView.findViewById(R.id.medicineName);
        engNameTextView = rootView.findViewById(R.id.medicineEngName);
        imageView = rootView.findViewById(R.id.medicineImage);
        itemSeqTextView = rootView.findViewById(R.id.itemSeq);
        enterpriseTextView = rootView.findViewById(R.id.enterprise);
        classTextView = rootView.findViewById(R.id.classNoName);
        etcOtcNameTextView = rootView.findViewById(R.id.itemEtcOtcName);
        ediCodeTextView = rootView.findViewById(R.id.ediCode);

        /*  Bundle을 통해 전달 받은 데이터 연결  */
        if (getArguments() != null) {
            nameTextView.setText(getArguments().getString("name"));
            //Log.v("detail", "bundle : " + getArguments().getString("name"));
            //Log.v("detail", "nameTextView : " + nameTextView.getText().toString());
            engNameTextView.setText(getArguments().getString("engName"));

            imageUrl = getArguments().getString("image");
            Glide.with(this)
                    .load(imageUrl)
                    .into(imageView);

            itemSeqTextView.setText(getArguments().getString("itemSeq"));
            enterpriseTextView.setText(getArguments().getString("enterprise"));

            String classNo = getArguments().getString("classNo");
            String className = getArguments().getString("className");
            classTextView.setText(classNo + " (" + className + ")");

            etcOtcNameTextView.setText(getArguments().getString("etcOtcName"));
            ediCodeTextView.setText(getArguments().getString("ediCode"));
        }

        //Log.v("detail", nameTextView.getText().toString());
        return rootView;
    }
}
