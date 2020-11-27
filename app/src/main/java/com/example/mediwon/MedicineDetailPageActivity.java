package com.example.mediwon;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

public class MedicineDetailPageActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Intent intent;

    private String imageUrl;    // 큰 제품 이미지
    private ImageView imageView;    // 큰 제품 이미지
    private TextView nameTextView;  // 품목명
    private TextView engNameTextView;   // 제품영문명
    private TextView itemSeqTextView;   // 품목 일련번호
    private TextView enterpriseTextView;    // 업체명
    private TextView classTextView;     // 분류번호, 분류명
    private TextView etcOtcNameTextView;    // 전문/일반
    private TextView ediCodeTextView;   // 보험코드

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_detail_page);

        /*  App Bar */
        toolbar = findViewById(R.id.detailAppBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼

        nameTextView = findViewById(R.id.medicineName);
        engNameTextView = findViewById(R.id.medicineEngName);
        imageView = findViewById(R.id.medicineImage);
        itemSeqTextView = findViewById(R.id.itemSeq);
        enterpriseTextView = findViewById(R.id.enterprise);
        classTextView = findViewById(R.id.classNoName);
        etcOtcNameTextView = findViewById(R.id.itemEtcOtcName);
        ediCodeTextView = findViewById(R.id.ediCode);

        /*  adapter에서 인텐트로 넘겨준 인자 값 받아서 연결   */
        intent = getIntent();

        nameTextView.setText(intent.getExtras().getString("name"));
        engNameTextView.setText(intent.getExtras().getString("engName"));

        imageUrl = intent.getExtras().getString("image");
        Glide.with(this)
                .load(imageUrl)
                .into(imageView);

        itemSeqTextView.setText(intent.getExtras().getString("itemSeq"));
        enterpriseTextView.setText(intent.getExtras().getString("enterprise"));

        String classNo = intent.getExtras().getString("classNo");
        String className = intent.getExtras().getString("className");
        classTextView.setText(classNo + " (" + className + ")");

        etcOtcNameTextView.setText(intent.getExtras().getString("etcOtcName"));
        ediCodeTextView.setText(intent.getExtras().getString("ediCode"));

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
