package com.example.mediwon.ui.tab_layout;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediwon.R;
import com.example.mediwon.ui.adapter.SideEffectRecyclerAdapter;
import com.example.mediwon.view_model.Medicine;
import com.example.mediwon.view_model.SideEffect;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SideEffectInfoFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<SideEffect> dataSet;
    private SideEffect sideEffect;

    private String medicineName;
    private String mainIngredients;     // 주성분
    private ArrayList<String> ingredientsName;  // 주성분 배열에 저장. 주성분들의 부작용 데이터를 파싱하기 위해 주성분 배열에 저장

    private String key = "lZqRHe1K6Pa5E1JupiOr%2BKKr8Kg6IF0jJjCCrzr9C3oyTdfjAs92SewVuwo0em58nVWDhZNMDlKAaohxk0Khtw%3D%3D";
    private String requestUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_side_effect_info, container, false);

        /*  RecyclerView    */
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // LinearLayoutManager 사용
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        /*  Bundle을 통해 전달 받은 의약품 낱알식별정보(DB) 서비스 데이터 연결  */
        if (getArguments() != null) {
            medicineName = getArguments().getString("name");
        }

        /*  AsyncTask    */
        // 의약품 제품 허가정보 서비스 - 제품 허가 목록조회 Operation에서 주성분 가져오기
        MedicineProductPermissionInfoService asyncTask = new MedicineProductPermissionInfoService();
        asyncTask.execute();


        return rootView;
    }

    /*  AsyncTask - 의약품 제품 허가정보 서비스 - 제품 허가 목록조회 Operation에서 주성분 가져오기   */
    public class MedicineProductPermissionInfoService extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            requestUrl = "http://apis.data.go.kr/1471057/MdcinPrductPrmisnInfoService/getMdcinPrductList?ServiceKey="
                    + key + "&item_name=" + medicineName;

            try {
                boolean isItemIngredientName = false;  // 주성분

                URL url = new URL(requestUrl);
                InputStream inputStream = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(inputStream, "UTF-8"));

                String tag;
                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {

                    tag = parser.getName();

                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            if (tag.equals("item")) {
                                ingredientsName = new ArrayList<>();
                            }
                            if (tag.equals("ITEM_INGR_NAME")) {
                                isItemIngredientName = true;
                            }
                            break;
                        case XmlPullParser.TEXT:
                            if (isItemIngredientName && parser.getText() != null) {
                                mainIngredients = parser.getText(); // 주성분 데이터 가져옴
                                //Log.v("sideEffect", mainIngredients);
                                StringTokenizer tokenizer = new StringTokenizer(mainIngredients, "/");

                                int i = 0;
                                while (tokenizer.hasMoreTokens()) {
                                    ingredientsName.add(tokenizer.nextToken()); // 주성분 배열에 저장
                                    //Log.v("sideEffect", ingredientsName.get(i));
                                    //i++;
                                }
                                isItemIngredientName = false;
                            }
                            break;

                        case XmlPullParser.END_TAG:
                            break;

                        case XmlPullParser.END_DOCUMENT:
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

            // 의약품 부작용 정보 서비스 파싱
            MedicineSideEffectInformationService asyncTask = new MedicineSideEffectInformationService(ingredientsName);
            asyncTask.execute();

        }
    }

    /*  AsyncTask - 의약품 부작용 정보 서비스 파싱   */
    public class MedicineSideEffectInformationService extends AsyncTask<String, Void, String> {

        private ArrayList<String> ingredientsName;

        public MedicineSideEffectInformationService(ArrayList<String> ingredientsName) {
            this.ingredientsName = ingredientsName;
        }

        @Override
        protected String doInBackground(String... strings) {

            // 주성분 배열에 저장의 크기만큼 요청. 각 성분의 부작용 정보를 파싱하기 위해
            //for (int i = 0; i < ingredientsName.size(); i++) {
                requestUrl = "http://apis.data.go.kr/1470000/MdcinSdefctInfoService/getMdcinSdefctInfoList?ServiceKey="
                        + key;
                //Log.v("sideEffect", ingredientsName.get(i));

                try {
                    boolean isCOL_001 = false;      // 품명
                    boolean isCOL_002 = false;      // 품명 (영문)
                    boolean isCOL_003 = false;      // 구성제제
                    boolean isCOL_004 = false;      // 기간
                    boolean isCOL_005 = false;      // 실마리정보 (국문)
                    boolean isCOL_006 = false;      // 실마리정보 (영문)
                    boolean isCOL_007 = false;      // 비고
                    boolean isCOL_008 = false;      // 실마리정보안내
                    boolean isCOL_009 = false;      // 지속적모니터링안내
                    boolean isCOL_010 = false;      // 허가사항변경안내

                    boolean isTotalCount = false;

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

                    while (eventType != XmlPullParser.END_DOCUMENT) {

                        tag = parser.getName(); // 태그 값 저장

                        switch (eventType) {
                            case XmlPullParser.START_DOCUMENT:  // 이벤트 문서의 시작
                                dataSet = new ArrayList<>();
                                break;

                            case XmlPullParser.START_TAG:   // 태그 시작
                                if(tag.equals("totalCount")){
                                    isTotalCount = true;
                                }

                                if (tag.equals("item")) {
                                    sideEffect = new SideEffect();
                                }
                                if (tag.equals("COL_001")) {    // 품명
                                    isCOL_001 = true;
                                }
                                if (tag.equals("COL_002")) {    // 품명 (영문)
                                    isCOL_002 = true;
                                }
                                if (tag.equals("COL_003")) {    // 구성제제
                                    isCOL_003 = true;
                                }
                                if (tag.equals("COL_004")) {    // 기간
                                    isCOL_004 = true;
                                }
                                if (tag.equals("COL_005")) {    // 실마리정보 (국문)
                                    isCOL_005 = true;
                                }
                                if (tag.equals("COL_006")) {    // 실마리정보 (영문)
                                    isCOL_006 = true;
                                }
                                if (tag.equals("COL_007")) {    // 비고
                                    isCOL_007 = true;
                                }
                                if (tag.equals("COL_008")) {    // 실마리정보안내
                                    isCOL_008 = true;
                                }
                                if (tag.equals("COL_009")) {    // 지속적모니터링안내
                                    isCOL_009 = true;
                                }
                                if (tag.equals("COL_010")) {    // 허가사항변경안내
                                    isCOL_010 = true;
                                }
                                break;

                            case XmlPullParser.TEXT:    // 태그 사이 텍스트
                                if (isCOL_001 && parser.getText() != null) {    // 품명
                                    sideEffect.setName(parser.getText());
                                    isCOL_001 = false;
                                }
                                else if (isCOL_002 && parser.getText() != null) {    // 품명 (영문)
                                    sideEffect.setName_eng(parser.getText());
                                    isCOL_002 = false;
                                }
                                else if (isCOL_003 && parser.getText() != null) {    // 구성제제
                                    sideEffect.setComponents(parser.getText());
                                    isCOL_003 = false;
                                }
                                else if (isCOL_004 && parser.getText() != null) {    // 기간
                                    sideEffect.setPeriod(parser.getText());
                                    isCOL_004 = false;
                                }
                                else if (isCOL_005 && parser.getText() != null) {    // 실마리정보 (국문)
                                    sideEffect.setSignal_kor(parser.getText());
                                    isCOL_005 = false;
                                }
                                else if (isCOL_006 && parser.getText() != null) {    // 실마리정보 (영문)
                                    sideEffect.setSignal_eng(parser.getText());
                                    isCOL_006 = false;
                                }
                                else if (isCOL_007 && parser.getText() != null) {    // 비고
                                    sideEffect.setElucidatoryNotes(parser.getText());
                                    isCOL_007 = false;
                                }
                                else if (isCOL_008 && parser.getText() != null) {    // 실마리정보안내
                                    sideEffect.setSignalGuide(parser.getText());
                                    isCOL_008 = false;
                                }
                                else if (isCOL_009 && parser.getText() != null) {    // 지속적모니터링안내
                                    sideEffect.setMonitoring(parser.getText());
                                    isCOL_009 = false;
                                }
                                else if (isCOL_010 && parser.getText() != null) {    // 허가사항변경안내
                                    sideEffect.setPermissionChangeGuide(parser.getText());
                                    isCOL_010 = false;
                                }

                                else if(isTotalCount) {
                                    Log.v("sideEffect", "totalCount : " + parser.getText());
                                    isTotalCount = false;
                                }
                                break;

                            case XmlPullParser.END_TAG: // 태그 끝
                                if (tag.equals("item")) {
                                    dataSet.add(sideEffect);
                                }
                                break;

                            case XmlPullParser.END_DOCUMENT:    // 문서의 끝
                                break;
                        }
                        eventType = parser.next();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            //}

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                // Adapter를 통해 데이터 연결
                adapter = new SideEffectRecyclerAdapter(dataSet);
                recyclerView.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
