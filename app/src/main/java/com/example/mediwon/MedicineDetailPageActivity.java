package com.example.mediwon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.mediwon.ui.adapter.PageAdapter;
import com.example.mediwon.ui.tab_layout.MedicineInfoFragment;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MedicineDetailPageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PageAdapter pageAdapter;
    private TabItem tabMedicineInfo, tabIdentificationInfo, tabIngredientInfo, tabEffectInfo, tabSideEffectInfo;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_detail_page);

        // SearchMedicineFragment Adapter에서 전달 받은 intent 가져옴
        intent = getIntent();

        /*  App Bar */
        toolbar = findViewById(R.id.detailAppBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼

        /*  TabLayout, TabItem */
        tabLayout = findViewById(R.id.tabLayout);
        tabMedicineInfo = findViewById(R.id.tab_medicineInfo);
        tabIdentificationInfo = findViewById(R.id.tab_identificationInfo);
        tabIngredientInfo = findViewById(R.id.tab_ingredientInfo);
        tabEffectInfo = findViewById(R.id.tab_effectInfo);
        tabSideEffectInfo = findViewById(R.id.tab_sideEffectInfo);

        viewPager = findViewById(R.id.viewPager);
        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), intent);    // intent 넘겨줌
        viewPager.setAdapter(pageAdapter);
        // ViewPager에서 페이지의 상태가 변경될 때 페이지 변경 이벤트를 TabLayout에 전달하여 탭의 선택 상태를 동기화해주는 역할
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // 탭의 선택 상태가 변경될 때 호출되는 리스너
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                //pageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

/*
        /*  adapter에서 인텐트로 넘겨준 인자 값들을 MedicineInfoFragment에 전달  * /
        intent = getIntent();
        //newInstance(intent);

        Fragment fragment = new MedicineInfoFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.viewPager, fragment);
        //transaction.replace(R.id.fragment_layout, fragment);
        transaction.commit();

        Bundle bundle = new Bundle();
        bundle.putString("name", intent.getExtras().getString("name"));
        //Log.v("bundle", bundle.getString("name"));
        bundle.putString("engName", intent.getExtras().getString("engName"));
        bundle.putString("image", intent.getExtras().getString("image"));
        bundle.putString("itemSeq", intent.getExtras().getString("itemSeq"));
        bundle.putString("enterprise", intent.getExtras().getString("enterprise"));
        bundle.putString("classNo", intent.getExtras().getString("classNo"));
        bundle.putString("className", intent.getExtras().getString("className"));
        bundle.putString("etcOtcName", intent.getExtras().getString("etcOtcName"));
        bundle.putString("ediCode", intent.getExtras().getString("ediCode"));

        fragment.setArguments(bundle);  // 문제 X
        Log.v("detail", bundle.toString());
*/
    }

/*
    public static MedicineInfoFragment newInstance(Intent intent) {

        MedicineInfoFragment fragment = new MedicineInfoFragment();
        Bundle bundle = new Bundle();

        bundle.putString("name", intent.getExtras().getString("name"));
        //Log.v("bundle", bundle.getString("name"));
        bundle.putString("engName", intent.getExtras().getString("engName"));
        bundle.putString("image", intent.getExtras().getString("image"));
        bundle.putString("itemSeq", intent.getExtras().getString("itemSeq"));
        bundle.putString("enterprise", intent.getExtras().getString("enterprise"));
        bundle.putString("classNo", intent.getExtras().getString("classNo"));
        bundle.putString("className", intent.getExtras().getString("className"));
        bundle.putString("etcOtcName", intent.getExtras().getString("etcOtcName"));
        bundle.putString("ediCode", intent.getExtras().getString("ediCode"));

        fragment.setArguments(bundle);

        return fragment;
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.detail_page_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // app bar의 back 키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
