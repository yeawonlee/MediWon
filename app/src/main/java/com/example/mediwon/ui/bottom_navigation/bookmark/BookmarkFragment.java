package com.example.mediwon.ui.bottom_navigation.bookmark;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mediwon.R;

public class BookmarkFragment extends Fragment {

    private LinearLayout gotoMedicineBookmark;
    private LinearLayout gotoIngredientBookmark;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_bookmark, container, false);

        gotoMedicineBookmark = rootView.findViewById(R.id.gotoMedicineBookmark);
        gotoIngredientBookmark = rootView.findViewById(R.id.gotoIngredientBookmark);

        // 즐겨찾기에 추가한 약 정보를 조회하는 액티비티로 이동
        gotoMedicineBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MedicineBookmarkActivity.class);
                startActivity(intent);
            }
        });

        // 즐겨찾기에 추가한 성분 정보를 조회하는 액티비티로 이동
        gotoIngredientBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), IngredientBookmarkActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

}
