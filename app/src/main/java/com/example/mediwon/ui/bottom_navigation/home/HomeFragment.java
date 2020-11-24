package com.example.mediwon.ui.bottom_navigation.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediwon.R;
import com.example.mediwon.ui.adapter.MyAdapter;
import com.example.mediwon.view_model.Medicine;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Medicine> dataSet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        /*  RecyclerView    */
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // LinearLayoutManager 사용
        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        // Adapter를 통해 데이터 연결
        dataSet = new ArrayList<>();
        adapter = new MyAdapter(dataSet);
        recyclerView.setAdapter(adapter);

        dataSet.add(new Medicine(R.drawable.ic_contraceptive_pills, "대웅라베프라졸정20밀리그램", "대웅바이오(주)"));
        dataSet.add(new Medicine(R.drawable.ic_contraceptive_pills, "에스린정", "(주)휴비스트제약"));
        dataSet.add(new Medicine(R.drawable.ic_contraceptive_pills, "게보린정", "삼진제약(주)"));
        dataSet.add(new Medicine(R.drawable.ic_contraceptive_pills, "대웅라베프라졸정20밀리그램", "대웅바이오(주)"));
        dataSet.add(new Medicine(R.drawable.ic_contraceptive_pills, "에스린정", "(주)휴비스트제약"));
        dataSet.add(new Medicine(R.drawable.ic_contraceptive_pills, "게보린정", "삼진제약(주)"));

        return rootView;
    }

}
