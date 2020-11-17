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
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    //private ImageView profileImage;

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

        //profileImage = findViewById(R.id.profile_image);
        //Glide.with(this).load(R.drawable.ic_pill_medicine_medicines_medical_icon_131306).into(profileImage);
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
