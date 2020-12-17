package com.example.mediwon.ui.tab_layout;


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
import com.example.mediwon.view_model.Medicine;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class IngredientInfoFragment extends Fragment {

    /*  의약품 낱알식별정보(DB) 서비스 데이터 연결 */
    private TextView nameTextView;      // 품목명
    private TextView engNameTextView;   // 제품영문명

    /*  의약품 제품 허가정보 서비스 - 제품 허가 상세정보조회 데이터 연결 */
    private TextView materialNameTextView;   // 원료성분

    private Medicine medicine;
    private String medicineName;

    private String key = "lZqRHe1K6Pa5E1JupiOr%2BKKr8Kg6IF0jJjCCrzr9C3oyTdfjAs92SewVuwo0em58nVWDhZNMDlKAaohxk0Khtw%3D%3D";
    private String requestUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)  inflater.inflate(R.layout.fragment_ingredient_info, container, false);

        nameTextView = rootView.findViewById(R.id.medicineName);
        engNameTextView = rootView.findViewById(R.id.medicineEngName);
        materialNameTextView = rootView.findViewById(R.id.materialName);

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

        private StringBuffer materialNameBuffer; // 원료성분 데이터 담을 버퍼

        @Override
        protected String doInBackground(String... strings) {

            requestUrl = "http://apis.data.go.kr/1471057/MdcinPrductPrmisnInfoService/getMdcinPrductItem?ServiceKey="
                    + key + "&item_name=" + medicineName;
            //Log.v("ingredient", medicineName);

            try {
                boolean isMATERIAL_NAME = false;   // 원료성분

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
                            break;

                        case XmlPullParser.START_TAG:   // 태그 시작
                            if (tag.equals("item")) {
                                materialNameBuffer = new StringBuffer();
                                //medicine = new Medicine();
                            }
                            if (tag.equals("MATERIAL_NAME")) {    // 원료성분
                                Log.v("ingredient", "MATERIAL_NAME");
                                isMATERIAL_NAME = true;
                            }
                            break;

                        case XmlPullParser.TEXT:    // 태그 사이 텍스트
                            if (isMATERIAL_NAME && parser.getText() != null) {
                                /*
                                StringBuffer buffer = new StringBuffer();
                                buffer.append(parser.getText());
                                while (true) {
                                    //buffer.replace(buffer.indexOf("총량"), buffer.indexOf("|")+1, "\n\n");
                                    buffer.substring(buffer.indexOf("|"));
                                    Log.v("ingredient", buffer.toString());
                                    buffer.replace(buffer.indexOf("|"), buffer.indexOf("|")+1, "\n\n");
                                    materialNameBuffer.append(buffer);
                                    //Log.v("ingredient", parser.getText());
                                    //medicine.setMaterialName(parser.getText());
                                    if(buffer.lastIndexOf("|") == 0) {
                                        break;
                                    }
                                }
                                */
                                String text = parser.getText();
                                //Log.v("ingredient", "text\n" + text);
                                //StringTokenizer tokenizer = new StringTokenizer(text, "총량", true);
                                String[] ingredientList = text.split("총량");

                                int index = 0;
                                for (String ingredient : ingredientList) {
                                    if(index != 0) {
                                        ingredientList[index] = "총량" + ingredient;
                                        Log.v("ingredient", "ingredientList[" + index + "]" + ingredientList[index]);
                                    }
                                    index++;
                                }

                                for (int i = 1; i <= ingredientList.length; i++) {
                                    //Log.v("ingredient", "ingredientList\n" + ingredient);
                                    StringTokenizer tokenizer2 = new StringTokenizer(ingredientList[i], "|;");

                                    while (tokenizer2.hasMoreTokens()) {
                                        materialNameBuffer.append(tokenizer2.nextToken());
                                        //Log.v("ingredient", "materialNameBuffer : " + materialNameBuffer);
                                        materialNameBuffer.append("\n");
                                    }
                                    materialNameBuffer.append("\n");
                                }
                                isMATERIAL_NAME = false;
                            }
                            break;

                        case XmlPullParser.END_TAG: // 태그 끝
                            break;

                        case XmlPullParser.END_DOCUMENT:    // 문서의 끝
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
                //materialNameTextView.setText(medicine.getMaterialName());
                materialNameTextView.setText(materialNameBuffer);
                //Log.v("ingredient", "material name : " + materialNameTextView.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
