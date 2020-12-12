package com.example.mediwon.ui.tab_layout;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mediwon.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;

public class NotaBeneInfoFragment extends Fragment {

    /*  의약품 낱알식별정보(DB) 서비스 데이터 연결 */
    private TextView nameTextView;      // 품목명
    private TextView engNameTextView;   // 제품영문명

    /*  의약품 제품 허가정보 서비스 데이터 연결 */
    private TextView notaBeneDocDataTextView;   // 주의사항

    private String medicineName;

    private String key = "lZqRHe1K6Pa5E1JupiOr%2BKKr8Kg6IF0jJjCCrzr9C3oyTdfjAs92SewVuwo0em58nVWDhZNMDlKAaohxk0Khtw%3D%3D";
    private String requestUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_nota_bene_info, container, false);

        nameTextView = rootView.findViewById(R.id.medicineName);
        engNameTextView = rootView.findViewById(R.id.medicineEngName);
        notaBeneDocDataTextView = rootView.findViewById(R.id.notaBeneDocData);

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

        private StringBuffer notaBeneDocData; // 주의사항 문서 데이터 담을 버퍼
        private StringBuffer CDATA;

        @Override
        protected String doInBackground(String... strings) {

            requestUrl = "http://apis.data.go.kr/1471057/MdcinPrductPrmisnInfoService/getMdcinPrductItem?ServiceKey="
                    + key + "&item_name=" + medicineName;

            try {

                boolean isNB_DOC_DATA = false;    // NB_DOC_DATA 태그
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
                String docTitleAttribute, docTypeAttribute, sectionTitleAttribute, articleTitleAttribute;

                while (eventType != XmlPullParser.END_DOCUMENT) {

                    tag = parser.getName(); // 태그 값 저장

                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:  // 이벤트 문서의 시작
                            Log.v("detail", "문서 시작");
                            break;

                        case XmlPullParser.START_TAG:   // 태그 시작
                            //Log.v("detail", "START_TAG");
                            if (tag.equals("item")) {
                                notaBeneDocData = new StringBuffer();
                                Log.v("detail", "item tag start");
                            }
                            if (tag.equals("NB_DOC_DATA")) {    // 주의사항
                                Log.v("detail", "용법용량");
                                Log.v("detail", "NB_DOC_DATA");
                                isNB_DOC_DATA = true;
                            }
                            if (tag.equals("EE_DOC_DATA")) {    // 효능효과
                                isNB_DOC_DATA = false;
                            }
                            if (tag.equals("UD_DOC_DATA")) {    // 용법용량
                                isNB_DOC_DATA = false;
                            }
                            if (tag.equals("DOC") && isNB_DOC_DATA) {
                                Log.v("detail", "DOC");
                                try {
                                    docTitleAttribute = parser.getAttributeValue(null, "title");
                                    docTypeAttribute = parser.getAttributeValue(null, "type");
                                    Log.v("detail", "doc title = " + docTitleAttribute);
                                    Log.v("detail", "doc type = " + docTypeAttribute);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if (tag.equals("SECTION") && isNB_DOC_DATA) {
                                Log.v("detail", "SECTION");
                                try {
                                    sectionTitleAttribute = parser.getAttributeValue(null, "title");
                                    Log.v("detail", "section title = " + sectionTitleAttribute);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if (tag.equals("ARTICLE") && isNB_DOC_DATA) {
                                Log.v("detail", "ARTICLE");
                                try {
                                    articleTitleAttribute = parser.getAttributeValue(null, "title");
                                    Log.v("detail", "article title = " + articleTitleAttribute);
                                    if (!articleTitleAttribute.equals("")) {
                                        notaBeneDocData.append(articleTitleAttribute);
                                        notaBeneDocData.append("\n\n");
                                        Log.v("detail", "article title = null 아님!");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            try {
                                if (tag.equals("PARAGRAPH") && isNB_DOC_DATA) {
                                    Log.v("detail", "PARAGRAPH");
                                    CDATA = new StringBuffer();
                                    isPARAGRAPH = true;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        case XmlPullParser.TEXT:    // 태그 사이 텍스트
                            if (isPARAGRAPH && parser.getText() != null) {
                                notaBeneDocData.append("<p>");
                                notaBeneDocData.append(parser.getText());
                                notaBeneDocData.append("</p>");
                                /*
                                StringBuffer parseCDATA = new StringBuffer();
                                parseCDATA.append(parser.getText());
                                //CDATA.append(parseCDATA.substring(parseCDATA.indexOf("<") - 1));
                                Log.v("detail", "PARAGRAPH !! " + CDATA);
                                CDATA.append(parseCDATA.substring(parseCDATA.indexOf("<p>")));
                                Log.v("detail", "html tag start : " + CDATA);
                                CDATA.append(parseCDATA.substring(parseCDATA.indexOf("</p>")));
                                Log.v("detail", "html tag end : " + CDATA);
                                notaBeneDocData.append(CDATA);
                                notaBeneDocData.append("\n\n");
                                */

                                /*
                                StringBuffer parseCDATA = new StringBuffer();
                                parseCDATA.append(parser.getText());
                                StringTokenizer tokenizer = new StringTokenizer(parseCDATA.toString(), "<tbody>");

                                if(tokenizer != null) {
                                    notaBeneDocData.append("<p>");
                                    notaBeneDocData.append(parser.getText());
                                    notaBeneDocData.append("</p>");
                                } else {
                                    while (tokenizer.hasMoreTokens()) {
                                        notaBeneDocData.append("<table>");
                                        notaBeneDocData.append(parser.getText());
                                        notaBeneDocData.append("</table>");
                                    }
                                }
                               */
                                isPARAGRAPH = false;
                                Log.v("detail", "PARAGRAPH) " + notaBeneDocData);

                            }
                            break;

                        case XmlPullParser.END_TAG: // 태그 끝
                            if (tag.equals("PARAGRAPH")) {
                                isPARAGRAPH = false;
                                //여기서 작업?
                                /*
                                StringBuffer CDATA_ = new StringBuffer();
                                CDATA_.append(CDATA.substring(CDATA.indexOf("/>") + 2));
                                notaBeneDocData.append(CDATA_);
                                notaBeneDocData.append("\n\n");
                                */
                            }
                            if (tag.equals("NB_DOC_DATA")) {
                                isNB_DOC_DATA = false;
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

            try {
                notaBeneDocDataTextView.setText(Html.fromHtml(String.valueOf(notaBeneDocData)));
                //Log.v("detail", "notaBeneDocData : " + notaBeneDocDataTextView.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
