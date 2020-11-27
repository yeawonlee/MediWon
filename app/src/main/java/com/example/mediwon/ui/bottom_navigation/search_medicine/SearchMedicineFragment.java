package com.example.mediwon.ui.bottom_navigation.search_medicine;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediwon.R;
import com.example.mediwon.ui.adapter.MyAdapter;
import com.example.mediwon.ui.bottom_navigation.home.HomeFragment;
import com.example.mediwon.view_model.Medicine;
import com.google.android.material.tabs.TabLayout;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class SearchMedicineFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Medicine> dataSet;
    private Medicine medicine;

    private String key = "lZqRHe1K6Pa5E1JupiOr%2BKKr8Kg6IF0jJjCCrzr9C3oyTdfjAs92SewVuwo0em58nVWDhZNMDlKAaohxk0Khtw%3D%3D";
    private String requestUrl;
    //private int pageNo = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search_medicine, container, false);

        /*  TabLayout    */
        tabLayout = rootView.findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(this);

        /*  RecyclerView    */
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // LinearLayoutManager 사용
        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        /*  AsyncTask    */
        MedicineGrainIdentificationInfoService asyncTask = new MedicineGrainIdentificationInfoService();
        asyncTask.execute();

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // top_app_bar.xml 등록
        inflater.inflate(R.menu.top_app_bar, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("제품명 / 증상 / 효능을 입력하세요");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ((MyAdapter)adapter).getFilter().filter(query);
                Log.v("data", "query : " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((MyAdapter)adapter).getFilter().filter(newText);
                Log.v("data", "newText : " + newText);
                return false;
            }
        });

    }

    /*  TabLayout    */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {  // tab의 상태가 선택되지 않음에서 선택 상태로 변경될 때

        int position = tab.getPosition();

        if(position == 0) {

        }
        else {

        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {    // tab의 상태가 선택 상태에서 선택되지 않음으로 변경될 때


    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {    // 이미 선택된 상태의 tab이 사용자에 의해 다시 선택될 때


    }

    /*  AsyncTask    */
    // AsyncTask<doInBackground, onProgressUpdate, onPostExecute의 매개변수 자료형>
    public class MedicineGrainIdentificationInfoService extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            // numOfRows : 한 페이지에 받아올 최대 개수
            // numOfRows에 따라 페이지의 개수(pageNo; 페이지 번호)가 달라짐
            requestUrl = "http://apis.data.go.kr/1470000/MdcinGrnIdntfcInfoService/getMdcinGrnIdntfcInfoList?ServiceKey="
                    + key + "&numOfRows=10";

            try {
                boolean isImage = false;
                boolean isName = false;
                boolean isEnterprise = false;
                boolean isTotalCount = false;
                boolean isPageNo = false;
                boolean isNumOfRows = false;

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
                            /*
                            pageNo++;
                            if(pageNo <= 5) {
                                requestUrl = "http://apis.data.go.kr/1470000/MdcinGrnIdntfcInfoService/getMdcinGrnIdntfcInfoList?ServiceKey="
                                        + key + "&pageNo=" + pageNo;
                            }*/
                            break;
                        case XmlPullParser.END_TAG: // 끝 태그 읽음
                            if(tag.equals("item")) {
                                dataSet.add(medicine);
                            }
                            break;
                        case XmlPullParser.START_TAG:   // 시작 태그를 읽으면 실행
                            if(tag.equals("item")){
                                medicine = new Medicine();
                            }
                            if (tag.equals("ITEM_IMAGE"))
                                isImage = true;
                            if (tag.equals("ITEM_NAME"))
                                isName = true;
                            if (tag.equals("ENTP_NAME"))
                                isEnterprise = true;

                            if(tag.equals("totalCount")){
                                isTotalCount = true;
                            }
                            if(tag.equals("pageNo")) {
                                isPageNo = true;
                            }
                            if(tag.equals("numOfRows")) {
                                isNumOfRows = true;
                            }
                            break;
                        case XmlPullParser.TEXT:    // 텍스트 내용 읽음
                            if(isImage){
                                medicine.setImageUrl(parser.getText());
                                isImage = false;
                            }
                            else if(isName) {
                                medicine.setName(parser.getText());
                                isName = false;
                                Log.v("data", "제품명 : " + parser.getText());
                            }
                            else if(isEnterprise) {
                                medicine.setEnterprise(parser.getText());
                                isEnterprise = false;
                            }

                            else if(isTotalCount) {
                                Log.v("data", "totalCount : " + parser.getText());
                                isTotalCount = false;
                            }
                            else if(isPageNo) {
                                Log.v("data", "pageNo : " + parser.getText());
                                isPageNo = false;
                            }
                            else if(isNumOfRows) {
                                Log.v("data", "numOfRows : " + parser.getText());
                                isNumOfRows = false;
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
            //adapter = new MyAdapter(getActivity().getApplicationContext(), dataSet);
            adapter = new MyAdapter(dataSet);
            recyclerView.setAdapter(adapter);
        }
    }

}
