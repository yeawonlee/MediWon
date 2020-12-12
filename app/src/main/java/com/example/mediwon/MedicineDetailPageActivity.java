package com.example.mediwon;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.mediwon.ui.adapter.PageAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MedicineDetailPageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PageAdapter pageAdapter;
    private TabItem tabMedicineInfo, tabIdentificationInfo, tabEffectInfo, tabUsageDirectionInfo, tabIngredientInfo, tabSideEffectInfo, tabNotaBeneInfo;

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
        tabMedicineInfo = findViewById(R.id.tab_medicineInfo);                  // 약품 정보
        tabIdentificationInfo = findViewById(R.id.tab_identificationInfo);      // 식별 정보
        tabEffectInfo = findViewById(R.id.tab_effectInfo);                      // 효능효과 정보
        tabUsageDirectionInfo = findViewById(R.id.tab_usageDirectionInfo);      // 용법 정보
        tabIngredientInfo = findViewById(R.id.tab_ingredientInfo);              // 성분 정보
        tabSideEffectInfo = findViewById(R.id.tab_sideEffectInfo);              // 부작용 정보
        tabNotaBeneInfo = findViewById(R.id.tab_notaBeneInfo);                  // 주의사항 정보

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

    }

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
