package com.example.mediwon.ui.tab_layout;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mediwon.R;
import com.example.mediwon.view_model.Medicine;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class EffectInfoFragment extends Fragment {

    /*  의약품 낱알식별정보(DB) 서비스 데이터 연결 */
    private TextView nameTextView;               // 품목명
    private TextView engNameTextView;            // 제품영문명

    /*  의약품 제품 허가정보 서비스 데이터 연결 */
    private TextView effectDocDataTextView;   // 효능효과

    private Medicine medicine;
    private String medicineName;

    private String key = "lZqRHe1K6Pa5E1JupiOr%2BKKr8Kg6IF0jJjCCrzr9C3oyTdfjAs92SewVuwo0em58nVWDhZNMDlKAaohxk0Khtw%3D%3D";
    private String requestUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_effect_info, container, false);

        nameTextView = rootView.findViewById(R.id.medicineName);
        engNameTextView = rootView.findViewById(R.id.medicineEngName);
        effectDocDataTextView = rootView.findViewById(R.id.effectDocData);

        /*  AsyncTask    */
        MedicineProductPermissionInfoService asyncTask = new MedicineProductPermissionInfoService();
        asyncTask.execute();

        /*  Bundle을 통해 전달 받은 의약품 낱알식별정보(DB) 서비스 데이터 연결  */
        if (getArguments() != null) {
            medicineName = getArguments().getString("name");

            nameTextView.setText(medicineName);
            engNameTextView.setText(getArguments().getString("engName"));
        }

        return rootView;
    }

    /*  AsyncTask    */
    public class MedicineProductPermissionInfoService extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            requestUrl = "http://apis.data.go.kr/1471057/MdcinPrductPrmisnInfoService/getMdcinPrductItem?ServiceKey="
                    + key + "&item_name=" + medicineName;

            try {
                boolean isEffectDocData = false;    // 효능효과

                URL url = new URL(requestUrl); // 문자열로 된 request url을 URL 객체로 생성
                InputStream inputStream = url.openStream(); // url 위치로 입력스트림 연결
                // XmlPullParser 객체 생성
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                // url에서 받은 InputStream 객체를 집어넣어 데이터를 가져오기. Xml 데이터와 인코딩 방식을 입력
                parser.setInput(new InputStreamReader(inputStream, "UTF-8"));

                String tag;
                int eventType = parser.getEventType();  // getEventType()를 통해 파서의 현재 이벤트 상태 확인

                while(eventType != XmlPullParser.END_DOCUMENT){

                    tag = parser.getName();

                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            //Log.v("detail", "START_DOCUMENT");
                            break;
                        case XmlPullParser.START_TAG:   // 시작 태그를 읽으면 실행
                            //Log.v("detail", "START_TAG");
                            if(tag.equals("item")){
                                medicine = new Medicine();
                                //Log.v("detail", "item tag start");
                            }
                            if (tag.equals("EE_DOC_DATA")) {
                                isEffectDocData = true;

                                // 수정 필요. <EE_DOC_DATA> 태그 안의 자식 태그로 접근해서 처리해야 함
                            }
                            break;
                        case XmlPullParser.TEXT:    // 텍스트 내용 읽음
                            if(isEffectDocData && parser.getText() != null) {
                                medicine.setEffectDocID(parser.getText());
                                isEffectDocData = false;
                                //Log.v("detail", "주성분 : " + medicine.getItemIngredientName());
                            }
                            break;
                        case XmlPullParser.END_TAG: // 끝 태그 읽음
                            if(tag.equals("item")) {
                                //Log.v("detail", "END_TAG");
                                break;
                            }

                        case XmlPullParser.END_DOCUMENT:
                            //Log.v("detail", "END_DOCUMENT");
                            break;
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                effectDocDataTextView.setText(medicine.getEffectDocData());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
