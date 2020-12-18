package com.example.mediwon.ui.bottom_navigation.search_side_effect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mediwon.R;

public class SideEffectDetailPageActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private TextView nameTextView;                           // 품명
    private TextView nameEngTextView;                        // 품명 (영문)
    private TextView componentsTextView;                     // 구성제제
    private TextView periodTextView;                         // 기간
    private TextView signalKorTextView;                      // 실마리정보 (국문)
    private TextView signalEngTextView;                      // 실마리정보 (영문)
    private TextView elucidatoryNotesTextView;               // 비고
    private TextView signalGuideTextView;                    // 실마리정보안내
    private TextView monitoringTextView;                     // 지속적모니터링안내
    private TextView permissionChangeGuideTextView;          // 허가사항변경안내

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_effect_detail_page);

        /*  App Bar */
        toolbar = findViewById(R.id.detailAppBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼

        nameTextView = findViewById(R.id.name);
        nameEngTextView = findViewById(R.id.name_eng);
        componentsTextView = findViewById(R.id.components);
        periodTextView = findViewById(R.id.period);
        signalKorTextView = findViewById(R.id.signal_kor);
        signalEngTextView = findViewById(R.id.signal_eng);
        elucidatoryNotesTextView = findViewById(R.id.elucidatoryNotes);
        signalGuideTextView = findViewById(R.id.signalGuide);
        monitoringTextView = findViewById(R.id.monitoring);
        permissionChangeGuideTextView = findViewById(R.id.permissionChangeGuide);

        /*  SearchSideEffectFragment Adapter에서 데이터 받아옴 */
        intent = getIntent();

        nameTextView.setText(intent.getExtras().getString(("name")));                                       // 품명
        nameEngTextView.setText(intent.getExtras().getString(("name_eng")));                                // 품명 (영문)
        componentsTextView.setText(intent.getExtras().getString(("components")));                           // 구성제제
        periodTextView.setText(intent.getExtras().getString(("period")));                                   // 기간
        signalKorTextView.setText(intent.getExtras().getString(("signal_kor")));                            // 실마리정보 (국문)
        signalEngTextView.setText(intent.getExtras().getString(("signal_eng")));                            // 실마리정보 (영문)
        elucidatoryNotesTextView.setText(intent.getExtras().getString(("elucidatoryNotes")));               // 비고
        signalGuideTextView.setText(intent.getExtras().getString(("signalGuide")));                         // 실마리정보안내
        monitoringTextView.setText(intent.getExtras().getString(("monitoring")));                           // 지속적모니터링안내
        permissionChangeGuideTextView.setText(intent.getExtras().getString(("permissionChangeGuide")));     // 허가사항변경안내

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
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
