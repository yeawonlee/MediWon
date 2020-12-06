package com.example.mediwon.ui.bottom_navigation.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mediwon.R;
import com.example.mediwon.ui.adapter.SearchMedicineAdapter;
import com.example.mediwon.view_model.Medicine;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Medicine> dataSet;
    private Medicine medicine;

    private String key = "lZqRHe1K6Pa5E1JupiOr%2BKKr8Kg6IF0jJjCCrzr9C3oyTdfjAs92SewVuwo0em58nVWDhZNMDlKAaohxk0Khtw%3D%3D";
    private String requestUrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

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

    /*  AsyncTask    */
    // AsyncTask<doInBackground, onProgressUpdate, onPostExecute의 매개변수 자료형>
    public class MedicineGrainIdentificationInfoService extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            requestUrl = "http://apis.data.go.kr/1470000/MdcinGrnIdntfcInfoService/getMdcinGrnIdntfcInfoList?ServiceKey="
                    + key;

            try {
                boolean isImage = false;
                boolean isName = false;
                boolean isEnterprise = false;

                URL url = new URL(requestUrl);
                InputStream inputStream = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(inputStream, "UTF-8"));

                String tag;
                int eventType = parser.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            dataSet = new ArrayList<>();
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if(parser.getName().equals("item") && medicine != null) {
                                dataSet.add(medicine);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if(parser.getName().equals("item")){
                                medicine = new Medicine();
                            }
                            if (parser.getName().equals("ITEM_IMAGE")) isImage = true;
                            if (parser.getName().equals("ITEM_NAME")) isName = true;
                            if (parser.getName().equals("ENTP_NAME")) isEnterprise = true;
                            break;
                        case XmlPullParser.TEXT:
                            if(isImage){
                                medicine.setImageUrl(parser.getText());
                                isImage = false;
                            } else if(isName) {
                                medicine.setName(parser.getText());
                                isName = false;
                            } else if (isEnterprise) {
                                medicine.setEnterprise(parser.getText());
                                isEnterprise = false;
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
            //adapter = new SearchMedicineAdapter(getActivity().getApplicationContext(), dataSet);
            adapter = new SearchMedicineAdapter(dataSet);
            recyclerView.setAdapter(adapter);
        }
    }

}
