package com.example.mediwon;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {   //implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;

    private Toolbar toolbar;

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

//    private DrawerLayout drawerLayout;
//    private ActionBarDrawerToggle actionBarDrawerToggle;
//    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        /*  App Bar */
        toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        /*  Bottom Navigation   */
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.fragment_layout);
/*
        /*  Navigation Drawer   * /
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.start, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);
*/
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }


/*  Navigation Drawer

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        actionBarDrawerToggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.edit:             // My 페이지 - 개인 정보 수정 페이지
                startActivity(new Intent(context, EditPersonalDataActivity.class));
                break;
            case R.id.bookmark:         // My 페이지 - 즐겨찾기 페이지
                startActivity(new Intent(context, BookmarkActivity.class));
                break;
            case R.id.my_setting:       // My 페이지 - My 설정 페이지
                startActivity(new Intent(context, SavingCustomDataActivity.class));
                break;
            case R.id.my_review:        // My 페이지 - 내가 작성한 후기
                startActivity(new Intent(context, ManageMyReviewActivity.class));
                break;
            case R.id.logout:           // 로그아웃
                logout();
                break;
            case R.id.withdrawal:       // 회원 탈퇴
                withdraw();
                finish();
                break;
        }

        return true;
    }

    // 로그아웃
    private void logout() {

    }

    // 회원 탈퇴
    private void withdraw() {

    }
*/

}
