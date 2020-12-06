package com.example.mediwon;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class IngredientDetailPageActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private TextView gnlNmTextView;     // 일반명
    private TextView divNmTextView;     // 분류명
    private TextView fomnTpNmTextView;  // 제형구분명
    private TextView gnlNmCdTextView;   // 일반명코드
    private TextView injcPthNmTextView; // 투여경로명
    private TextView iqtyTxtTextView;   // 함량내용
    private TextView meftDivNoTextView; // 약효분류번호
    private TextView unitTextView;      // 단위

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_detail_page);

        /*  App Bar */
        toolbar = findViewById(R.id.detailAppBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼

        gnlNmTextView = findViewById(R.id.gnlNm);
        divNmTextView = findViewById(R.id.divNm);
        fomnTpNmTextView = findViewById(R.id.fomnTpNm);
        gnlNmCdTextView = findViewById(R.id.gnlNmCd);
        injcPthNmTextView = findViewById(R.id.injcPthNm);
        iqtyTxtTextView = findViewById(R.id.iqtyTxt);
        meftDivNoTextView = findViewById(R.id.meftDivNo);
        unitTextView = findViewById(R.id.unit);

        /*  SearchIngredientsFragment Adapter에서 데이터 받아옴 */
        intent = getIntent();

        gnlNmTextView.setText(intent.getExtras().getString("gnlNm"));
        divNmTextView.setText(intent.getExtras().getString("divNm"));
        fomnTpNmTextView.setText(intent.getExtras().getString("fomnTpNm"));
        gnlNmCdTextView.setText(intent.getExtras().getString("gnlNmCd"));
        injcPthNmTextView.setText(intent.getExtras().getString("injcPthNm"));
        iqtyTxtTextView.setText(intent.getExtras().getString("iqtyTxt"));
        meftDivNoTextView.setText(intent.getExtras().getString("meftDivNo"));
        unitTextView.setText(intent.getExtras().getString("unit"));

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
