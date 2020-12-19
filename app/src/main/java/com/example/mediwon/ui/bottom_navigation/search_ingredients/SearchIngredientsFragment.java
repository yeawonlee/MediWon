package com.example.mediwon.ui.bottom_navigation.search_ingredients;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediwon.R;
import com.example.mediwon.ui.adapter.SearchIngredientAdapter;
import com.example.mediwon.view_model.Ingredient;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SearchIngredientsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Ingredient> dataSet;
    private Ingredient ingredient;

    private String key = "lZqRHe1K6Pa5E1JupiOr%2BKKr8Kg6IF0jJjCCrzr9C3oyTdfjAs92SewVuwo0em58nVWDhZNMDlKAaohxk0Khtw%3D%3D";
    private String requestUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search_ingredients, container, false);

        /*  RecyclerView    */
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // LinearLayoutManager 사용
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        /*  AsyncTask    */
        getMajorComponentNameCodeListService asyncTask = new getMajorComponentNameCodeListService();
        asyncTask.execute();

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    // onCreateOptionsMenu : 액티비티가 시작될 때 호출되는 함수. 액티비티 Life Cycle 내 한 번만 호출
    // MenuItem 생성과 초기화
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.top_app_bar, menu); // top_app_bar.xml 등록
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("성분명을 입력하세요");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            // 검색 버튼을 눌렀을 때 호출
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 텍스트가 바뀔 때마다 호출
            @Override
            public boolean onQueryTextChange(String newText) {
                ((SearchIngredientAdapter)adapter).getFilter().filter(newText);
                //Log.v("data", "newText : " + newText);
                return true;
            }
        });

    }

    /*  AsyncTask    */
    // AsyncTask<doInBackground, onProgressUpdate, onPostExecute의 매개변수 자료형>
    public class getMajorComponentNameCodeListService extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            // numOfRows : 한 페이지에 받아올 최대 개수
            // numOfRows에 따라 페이지의 개수(pageNo; 페이지 번호)가 달라짐
            requestUrl = "http://apis.data.go.kr/B551182/msupCmpnMeftInfoService/getMajorCmpnNmCdList?ServiceKey="
                    + key + "&numOfRows=700&meftDivNo=1";

            try {
                boolean isDivNm = false;    // 분류명
                boolean isFomnTpNm = false; // 제형구분명
                boolean isGnlNm = false;   // 일반명
                boolean isGnlNmCd = false;  // 일반명코드
                boolean isInjcPthNm = false;  // 투여경로명
                boolean isIqtyTxt = false;    // 함량내용
                boolean isMeftDivNo = false;   // 약효분류번호
                boolean isUnit = false;  // 단위

                boolean isNumOfRows = false;
                boolean isPageNo = false;
                boolean isTotalCount = false;

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
                            dataSet = new ArrayList<>();
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG: // 끝 태그 읽음
                            if(tag.equals("item")) {
                                dataSet.add(ingredient);
                            }
                            break;
                        case XmlPullParser.START_TAG:   // 시작 태그를 읽으면 실행
                            if(tag.equals("item")){
                                ingredient = new Ingredient();
                            }

                            if (tag.equals("divNm"))
                                isDivNm = true;
                            if (tag.equals("fomnTpNm"))
                                isFomnTpNm = true;
                            if (tag.equals("gnlNm"))
                                isGnlNm = true;
                            if (tag.equals("gnlNmCd"))
                                isGnlNmCd = true;
                            if (tag.equals("injcPthNm"))
                                isInjcPthNm = true;
                            if (tag.equals("iqtyTxt"))
                                isIqtyTxt = true;
                            if (tag.equals("meftDivNo"))
                                isMeftDivNo = true;
                            if (tag.equals("unit"))
                                isUnit = true;

                            //////////////
                            if(tag.equals("numOfRows")) {
                                isNumOfRows = true;
                            }
                            if(tag.equals("pageNo")) {
                                isPageNo = true;
                            }
                            if(tag.equals("totalCount")){
                                isTotalCount = true;
                            }
                            break;
                        case XmlPullParser.TEXT:    // 텍스트 내용 읽음
                            if(isDivNm) {
                                ingredient.setDivNm(parser.getText());
                                isDivNm = false;
                            }
                            else if(isFomnTpNm) {
                                ingredient.setFomnTpNm(parser.getText());
                                isFomnTpNm = false;
                            }
                            else if(isGnlNm) {
                                ingredient.setGnlNm(parser.getText());
                                isGnlNm = false;
                                Log.v("data", "성분명 : " + parser.getText());
                            }
                            else if(isGnlNmCd){
                                ingredient.setGnlNmCd(parser.getText());
                                isGnlNmCd = false;
                            }
                            else if(isInjcPthNm) {
                                ingredient.setInjcPthNm(parser.getText());
                                isInjcPthNm = false;
                            }
                            else if(isIqtyTxt) {
                                ingredient.setIqtyTxt(parser.getText());
                                isIqtyTxt = false;
                            }
                            else if(isMeftDivNo) {
                                ingredient.setMeftDivNo(parser.getText());
                                isMeftDivNo = false;
                            }
                            else if(isUnit) {
                                ingredient.setUnit(parser.getText());
                                isUnit = false;
                            }


                            //////////////
                            else if(isNumOfRows) {
                                Log.v("data", "numOfRows : " + parser.getText());
                                isNumOfRows = false;
                            }
                            else if(isPageNo) {
                                Log.v("data", "pageNo : " + parser.getText());
                                isPageNo = false;
                            }
                            else if(isTotalCount) {
                                Log.v("data", "totalCount : " + parser.getText());
                                isTotalCount = false;
                            }
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

            // Adapter를 통해 데이터 연결
            adapter = new SearchIngredientAdapter(dataSet);
            recyclerView.setAdapter(adapter);
        }
    }

}
