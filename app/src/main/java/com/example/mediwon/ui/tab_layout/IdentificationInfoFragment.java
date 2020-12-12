package com.example.mediwon.ui.tab_layout;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.mediwon.R;
import com.example.mediwon.view_model.MedicineIdentification;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class IdentificationInfoFragment extends Fragment {

    private TextView nameTextView;                // 품목명
    private TextView engNameTextView;             // 제품영문명
    private TextView chartTextView;               // 성상
    private TextView printFrontTextView;          // 표시(앞)
    private TextView printBackTextView;           // 표시(뒤)
    private TextView color_class1TextView;        // 색깔(앞)
    private TextView color_class2TextView;        // 색깔(뒤)
    private TextView lineFrontTextView;           // 분할선(앞)
    private TextView lineBackTextView;            // 분할선(뒤)
    private TextView formCodeNameTextView;        // 제형코드이름
    private TextView drug_shapeTextView;          // 의약품 모양
    private TextView lengthLongTextView;          // 크기(장축)
    private TextView lengthShortTextView;         // 크기(단축)
    private TextView thickTextView;               // 크기(두께)
    private ImageView medicineImage;              // 큰 제품 이미지

    private MedicineIdentification medicine;
    private String medicineName;

    private String key = "lZqRHe1K6Pa5E1JupiOr%2BKKr8Kg6IF0jJjCCrzr9C3oyTdfjAs92SewVuwo0em58nVWDhZNMDlKAaohxk0Khtw%3D%3D";
    private String requestUrl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_identification_info, container, false);

        nameTextView = rootView.findViewById(R.id.medicineName);               // 품목명
        engNameTextView = rootView.findViewById(R.id.medicineEngName);         // 제품영문명
        chartTextView = rootView.findViewById(R.id.chart);                     // 성상
        printFrontTextView = rootView.findViewById(R.id.printFront);           // 표시(앞)
        printBackTextView = rootView.findViewById(R.id.printBack);             // 표시(뒤)
        color_class1TextView = rootView.findViewById(R.id.color_class1);       // 색깔(앞)
        color_class2TextView = rootView.findViewById(R.id.color_class2);       // 색깔(뒤)
        lineFrontTextView = rootView.findViewById(R.id.lineFront);             // 분할선(앞)
        lineBackTextView = rootView.findViewById(R.id.lineBack);               // 분할선(뒤)
        formCodeNameTextView = rootView.findViewById(R.id.formCodeName);       // 제형코드이름
        drug_shapeTextView = rootView.findViewById(R.id.drug_shape);           // 의약품 모양
        lengthLongTextView = rootView.findViewById(R.id.lengthLong);           // 크기(장축)
        lengthShortTextView = rootView.findViewById(R.id.lengthShort);         // 크기(단축)
        thickTextView = rootView.findViewById(R.id.thick);                     // 크기(두께)
        medicineImage = rootView.findViewById(R.id.medicineImage);             // 큰 제품 이미지

        /*  AsyncTask    */
        MedicineGrainIdentificationInfoService asyncTask = new MedicineGrainIdentificationInfoService();
        asyncTask.execute();

        /*  Bundle을 통해 전달 받은 의약품 이름  */
        if (getArguments() != null) {
            medicineName = getArguments().getString("name");

            nameTextView.setText(medicineName);
            engNameTextView.setText(getArguments().getString("engName"));
        }

        return rootView;
    }

    /*  AsyncTask    */
    public class MedicineGrainIdentificationInfoService extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            requestUrl = "http://apis.data.go.kr/1470000/MdcinGrnIdntfcInfoService/getMdcinGrnIdntfcInfoList?ServiceKey="
                    + key + "&item_name=" + medicineName;

            try {
                boolean isCHART = false;                  // 성상
                boolean isPRINT_FRONT = false;            // 표시(앞)
                boolean isPRINT_BACK = false;             // 표시(뒤)
                boolean isDRUG_SHAPE = false;             // 의약품 모양
                boolean isCOLOR_CLASS1 = false;           // 색깔(앞)
                boolean isCOLOR_CLASS2 = false;           // 색깔(뒤)
                boolean isLINE_FRONT = false;             // 분할선(앞)
                boolean isLINE_BACK = false;              // 분할선(뒤)
                boolean isLENG_LONG = false;              // 크기(장축)
                boolean isLENG_SHORT = false;             // 크기(단축)
                boolean isTHICK = false;                  // 크기(두께)
                boolean isFORM_CODE_NAME = false;         // 제형코드이름
                boolean isITEM_IMAGE = false;             // 큰 제품 이미지

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
                            Log.v("detail", "문서 시작");
                            break;

                        case XmlPullParser.START_TAG:
                            //Log.v("detail", "START_TAG");
                            if (tag.equals("item")) {
                                medicine = new MedicineIdentification();
                                Log.v("detail", "item tag start");
                            }
                            if (tag.equals("CHART")) {    // 성상
                                isCHART = true;
                            }
                            if (tag.equals("ITEM_IMAGE")) {    // 큰 제품 이미지
                                isITEM_IMAGE = true;
                            }
                            if (tag.equals("PRINT_FRONT")) {    // 표시(앞)
                                isPRINT_FRONT = true;
                            }
                            if (tag.equals("PRINT_BACK")) {    // 표시(뒤)
                                isPRINT_BACK = true;
                            }
                            if (tag.equals("DRUG_SHAPE")) {    // 의약품 모양
                                isDRUG_SHAPE = true;
                            }
                            if (tag.equals("COLOR_CLASS1")) {    // 색깔(앞)
                                isCOLOR_CLASS1 = true;
                            }
                            if (tag.equals("COLOR_CLASS2")) {    // 색깔(뒤)
                                isCOLOR_CLASS2 = true;
                            }
                            if (tag.equals("LINE_FRONT")) {    // 분할선(앞)
                                isLINE_FRONT = true;
                            }
                            if (tag.equals("LINE_BACK")) {    // 분할선(뒤)
                                isLINE_BACK = true;
                            }
                            if (tag.equals("LENG_LONG")) {    // 크기(장축)
                                isLENG_LONG = true;
                            }
                            if (tag.equals("LENG_SHORT")) {    // 크기(단축)
                                isLENG_SHORT = true;
                            }
                            if (tag.equals("THICK")) {    // 크기(두께)
                                isTHICK = true;
                            }
                            if (tag.equals("FORM_CODE_NAME")) {    // 제형코드이름
                                isFORM_CODE_NAME = true;
                            }
                            break;

                        case XmlPullParser.TEXT:    // 태그 사이 텍스트
                            if(parser.getText() != null) {
                                if (isCHART) {    // 성상
                                    medicine.setChart(parser.getText());
                                    isCHART = false;
                                } else if (isITEM_IMAGE) {    // 큰 제품 이미지
                                    medicine.setImageUrl(parser.getText());
                                    isITEM_IMAGE = false;
                                } else if (isPRINT_FRONT) {    // 표시(앞)
                                    medicine.setPrintFront(parser.getText());
                                    isPRINT_FRONT = false;
                                } else if (isPRINT_BACK) {    // 표시(뒤)
                                    medicine.setPrintBack(parser.getText());
                                    isPRINT_BACK = false;
                                } else if (isDRUG_SHAPE) {    // 의약품 모양
                                    medicine.setDrug_shape(parser.getText());
                                    isDRUG_SHAPE = false;
                                } else if (isCOLOR_CLASS1) {    // 색깔(앞)
                                    medicine.setColor_class1(parser.getText());
                                    isCOLOR_CLASS1 = false;
                                } else if (isCOLOR_CLASS2) {    // 색깔(뒤)
                                    medicine.setColor_class2(parser.getText());
                                    isCOLOR_CLASS2 = false;
                                } else if (isLINE_FRONT) {    // 분할선(앞)
                                    medicine.setLineFront(parser.getText());
                                    isLINE_FRONT = false;
                                } else if (isLINE_BACK) {    // 분할선(뒤)
                                    medicine.setLineBack(parser.getText());
                                    isLINE_BACK = false;
                                } else if (isLENG_LONG) {    // 크기(장축)
                                    medicine.setLengthLong(parser.getText());
                                    isLENG_LONG = false;
                                } else if (isLENG_SHORT) {    // 크기(단축)
                                    medicine.setLengthShort(parser.getText());
                                    isLENG_SHORT = false;
                                } else if (isTHICK) {    // 크기(두께)
                                    medicine.setThick(parser.getText());
                                    isTHICK = false;
                                } else if (isFORM_CODE_NAME) {    // 제형코드이름
                                    medicine.setFormCodeName(parser.getText());
                                    isFORM_CODE_NAME = false;
                                }
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
                chartTextView.setText(medicine.getChart());                     // 성상
                printFrontTextView.setText(medicine.getPrintFront());           // 표시(앞)
                printBackTextView.setText(medicine.getPrintBack());             // 표시(뒤)
                color_class1TextView.setText(medicine.getColor_class1());       // 색깔(앞)
                color_class2TextView.setText(medicine.getColor_class2());       // 색깔(뒤)
                lineFrontTextView.setText(medicine.getLineFront());             // 분할선(앞)
                lineBackTextView.setText(medicine.getLineBack());               // 분할선(뒤)
                formCodeNameTextView.setText(medicine.getFormCodeName());       // 제형코드이름
                drug_shapeTextView.setText(medicine.getDrug_shape());           // 의약품 모양
                lengthLongTextView.setText(medicine.getLengthLong());           // 크기(장축)
                lengthShortTextView.setText(medicine.getLengthShort());         // 크기(단축)
                thickTextView.setText(medicine.getThick());                     // 크기(두께)

                // // 큰 제품 이미지
                Glide.with(getActivity().getApplicationContext())
                        .load(medicine.getImageUrl())
                        .into(medicineImage);
                //Log.v("ingredient", medicine.getImageUrl());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
