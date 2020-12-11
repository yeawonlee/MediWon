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

        private StringBuffer buffer;

        @Override
        protected String doInBackground(String... strings) {

            requestUrl = "http://apis.data.go.kr/1471057/MdcinPrductPrmisnInfoService/getMdcinPrductItem?ServiceKey="
                    //+ key + "&item_name=종근당염산에페드린정";// + medicineName;
                    + key + "&item_name=" + medicineName;

            try {
                // 효능효과
                boolean isEE_DOC_DATA = false;    // EE_DOC_DATA 태그
                boolean isDOC = false;  // DOC 태그
                boolean isSECTION = false;  // SECTION 태그
                boolean isARTICLE = false;  // ARTICLE 태그
                boolean isPARAGRAPH = false;  // PARAGRAPH 태그

                URL url = new URL(requestUrl); // 문자열로 된 request url을 URL 객체로 생성
                InputStream inputStream = url.openStream(); // url 위치로 입력스트림 연결

                /*
                * XmlPullParser.START_TAG, XmlPullParser.END_TAG : getName()
                * XmlPullParser.TEXT : getText()
                * XmlPullParser.TEXT 이벤트 - 태그의 텍스트 영역에 문자가 존재하지 않을 때 null값을 반환
                * 태그 안에 속성이 존재할 때는 XmlPullParser.START_TAG 이벤트가 발생했을 때 속성 값 추출
                */

                // XmlPullParser 객체 생성
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                // url에서 받은 InputStream 객체를 집어넣어 데이터를 가져오기. Xml 데이터와 인코딩 방식을 입력
                parser.setInput(new InputStreamReader(inputStream, "UTF-8"));

                String tag;
                int eventType = parser.getEventType();  // getEventType()를 통해 파서의 현재 이벤트 상태 확인
                String docTitleAttribute = null, docTypeAttribute = null, sectionTitleAttribute, articleTitleAttribute;

                while(eventType != XmlPullParser.END_DOCUMENT){

                    tag = parser.getName(); // 태그 값 저장

                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:  // 이벤트 문서의 시작
                            Log.v("detail", "문서 시작");
                            break;

                        case XmlPullParser.START_TAG:   // 태그 시작
                            //Log.v("detail", "START_TAG");
                            if(tag.equals("item")){
                                medicine = new Medicine();
                                Log.v("detail", "item tag start");
                            }
                            if(tag.equals("EE_DOC_DATA")) {
                                Log.v("detail", "효능효과");
                                Log.v("detail", "EE_DOC_DATA");
                                isEE_DOC_DATA = true;
                            }
                            if(tag.equals("DOC")) {
                                Log.v("detail", "DOC");
                                docTitleAttribute = parser.getAttributeValue(null, "title");
                                docTypeAttribute = parser.getAttributeValue(null, "type");
                                Log.v("detail", "doc title = " + docTitleAttribute);
                                Log.v("detail", "doc type = " + docTypeAttribute);
                                isDOC = true;
                                if(docTitleAttribute.equals("용법용량") || docTitleAttribute.equals("사용상주의사항")) {
                                    isDOC = false;
                                    break;
                                }
                            }
                            if(tag.equals("SECTION")) {
                                Log.v("detail", "SECTION");
                                sectionTitleAttribute = parser.getAttributeValue(null, "title");
                                Log.v("detail", "section title = " + sectionTitleAttribute);
                                isSECTION = true;
                            }
                            if(tag.equals("ARTICLE")) {
                                Log.v("detail", "ARTICLE");
                                articleTitleAttribute = parser.getAttributeValue(null, "title");
                                Log.v("detail", "article title = " + articleTitleAttribute);
                                isARTICLE = true;
                            }
                            if(tag.equals("PARAGRAPH")) {
                                //Log.v("detail", "PARAGRAPH");
                                isPARAGRAPH = true;
                            }
                            break;

                        case XmlPullParser.TEXT:    // 태그 사이 텍스트
                            /*
                            if(isEE_DOC_DATA) { //&& parser.getText() != null) {
                                //String effectDocData = parser.getText().replace(System.getProperty("line.separator"), "<br>");
                                Log.v("detail", "효능효과");
                                Log.v("detail", "EE_DOC_DATA");
                                //StringBuffer effectDocData = new StringBuffer();
                                //effectDocData.append(parser.getText());
                                //if (effectDoc != null) {		//여기도 한경닷컴특성상 제목 \n 공백 \n 제목 이런식으로돼있어서 추가
                                    //StringBuffer buffer = new StringBuffer();
                                    //buffer.append(effectDoc);
                                    //Log.v("detail", "효능효과 : " + buffer);
                                    //medicine.setEffectDocData(buffer);
                                    //Log.v("detail", "효능효과 : " + parser.getText());
                                    //Log.v("detail", "효능효과 : " + medicine.getEffectDocData());
                                //}
                            }
                            if(isDOC) { //&& parser.getText() != null) {
                                String doc = parser.getText();
                                //isDOC = false;
                                Log.v("detail", "DOC : " + doc);
                            }
                            if(isSECTION) { //&& parser.getText() != null) {
                                String section = parser.getText();
                                //isSECTION = false;
                                Log.v("detail", "SECTION : " + section);
                            }
                            if(isARTICLE) { //&& parser.getText() != null) {
                                String article = parser.getText();
                                //isARTICLE = false;
                                Log.v("detail", "ARTICLE : " + article);
                            }
                            */
                            if(isPARAGRAPH && isDOC) { //&& parser.getText() != null) {
                                String paragraph = parser.getText();
                                isPARAGRAPH = false;
                                Log.v("detail", "PARAGRAPH) " + paragraph);
                            }
                            break;

                        case XmlPullParser.END_TAG: // 태그 끝
                            /*
                            if(tag.equals("item")){
                                break;
                            }
                            */
                            if(tag.equals("EE_DOC_DATA")) {
                                isEE_DOC_DATA = false;
                                break;
                            }
                            if(tag.equals("DOC")) {
                                isDOC = false;
                            }
                            if(tag.equals("SECTION")) {
                                isSECTION = false;
                            }
                            if(tag.equals("ARTICLE")) {
                                isARTICLE = false;
                            }
                            if(tag.equals("PARAGRAPH")) {
                                //Log.v("detail", "END_TAG");
                                isPARAGRAPH = false;
                            }
                            break;

                        case XmlPullParser.END_DOCUMENT:    // 문서의 끝
                            Log.v("detail", "문서 끝");
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
/*
            try {
                effectDocDataTextView.setText(medicine.getEffectDocData());
            } catch (Exception e) {
                e.printStackTrace();
            }
*/
        }
    }
}
