package com.example.mediwon.ui.bottom_navigation.search_medicine_by_ingredients;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediwon.R;

public class SearchMedicineByIngredientsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search_medicine_by_ingredients, container, false);

        return rootView;
    }

}
