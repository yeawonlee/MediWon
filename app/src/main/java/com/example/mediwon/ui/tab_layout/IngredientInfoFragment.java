package com.example.mediwon.ui.tab_layout;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediwon.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientInfoFragment extends Fragment {


    public IngredientInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredient_info, container, false);
    }

}