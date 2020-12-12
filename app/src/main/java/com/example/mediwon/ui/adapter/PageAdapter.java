package com.example.mediwon.ui.adapter;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.mediwon.ui.tab_layout.EffectInfoFragment;
import com.example.mediwon.ui.tab_layout.IdentificationInfoFragment;
import com.example.mediwon.ui.tab_layout.IngredientInfoFragment;
import com.example.mediwon.ui.tab_layout.MedicineInfoFragment;
import com.example.mediwon.ui.tab_layout.NotaBeneInfoFragment;
import com.example.mediwon.ui.tab_layout.SideEffectInfoFragment;
import com.example.mediwon.ui.tab_layout.UsageDirectionInfoFragment;

public class PageAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;
    private Intent intent;

    public PageAdapter(FragmentManager fragmentManager, int numOfTabs, Intent intent) {
        //super(fragmentManager);
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numOfTabs = numOfTabs;
        this.intent = intent;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment;

        // MedicineDetailPageActivity에서 전달 받은 데이터를 Bundle에 넣어 Fragment의 인자로 전달
        Bundle bundle = new Bundle();
        bundle.putString("name", intent.getExtras().getString("name"));
        bundle.putString("engName", intent.getExtras().getString("engName"));
        bundle.putString("image", intent.getExtras().getString("image"));
        bundle.putString("itemSeq", intent.getExtras().getString("itemSeq"));
        bundle.putString("enterprise", intent.getExtras().getString("enterprise"));
        bundle.putString("classNo", intent.getExtras().getString("classNo"));
        bundle.putString("className", intent.getExtras().getString("className"));
        bundle.putString("etcOtcName", intent.getExtras().getString("etcOtcName"));
        bundle.putString("ediCode", intent.getExtras().getString("ediCode"));

        switch (position) {
            case 0:
                fragment = new MedicineInfoFragment();  // 약품 정보
                fragment.setArguments(bundle);
                return fragment;
            case 1:
                fragment = new IdentificationInfoFragment();  // 식별 정보
                fragment.setArguments(bundle);
                return fragment;
            case 2:
                fragment = new EffectInfoFragment();  // 효능 정보
                fragment.setArguments(bundle);
                return fragment;
            case 3:
                fragment = new UsageDirectionInfoFragment();     // 용법 정보
                fragment.setArguments(bundle);
                return fragment;
            case 4:
                fragment = new IngredientInfoFragment();    // 성분 정보
                fragment.setArguments(bundle);
                return fragment;
            case 5:
                fragment = new SideEffectInfoFragment();  // 부작용 정보
                fragment.setArguments(bundle);
                return fragment;
            case 6:
                fragment = new NotaBeneInfoFragment();  // 주의사항 정보
                fragment.setArguments(bundle);
                return fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    /*
    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
    */
}
