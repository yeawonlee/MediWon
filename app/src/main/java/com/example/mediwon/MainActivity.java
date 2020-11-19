package com.example.mediwon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.go_to_home);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.go_to_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.go_to_home:
                        return true;
                    case R.id.go_to_searchMedicinePage:
                        startActivity(new Intent(context, SearchMedicineActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.go_to_searchIngredientsPage:
                        startActivity(new Intent(context, SearchIngredientsActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.go_to_reviewPage:
                        startActivity(new Intent(context, ReviewActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    /*
                    case R.id.go_to_rankingPage:
                        intent =  new Intent(context, RankingActivity.class);
                        startActivity(intent);
                        break;*/
                }

                return true;
            }
        });

    }


    ///// Navigation Drawer

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ // 왼쪽 상단 버튼 눌렀을 때
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        item.setChecked(true);
        drawerLayout.closeDrawers();

        int id = item.getItemId();

        if(id == R.id.edit) {       // My 페이지 - 개인 정보 수정 페이지

            Intent intent =  new Intent(context, EditPersonalDataActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.bookmark) {      // My 페이지 - 즐겨찾기 페이지

            Intent intent =  new Intent(context, BookmarkActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.my_setting) {    // My 페이지 - My 설정 페이지

            Intent intent =  new Intent(context, SavingCustomDataActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.my_review) {     // My 페이지 - 내가 작성한 후기

            Intent intent =  new Intent(context, ManageMyReviewActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.logout) {        // 로그아웃
            logout();
        }
        else if(id == R.id.withdrawal){     // 회원 탈퇴
            withdraw();
            finish();
        }

        return true;
    }

    // 로그아웃
    private void logout() {

    }

    // 회원 탈퇴
    private void withdraw() {

    }

    ///// 옵션 메뉴
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

}
