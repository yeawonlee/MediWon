package com.example.mediwon.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mediwon.R;
import com.example.mediwon.ui.bottom_navigation.search_ingredients.IngredientDetailPageActivity;
import com.example.mediwon.ui.bottom_navigation.search_side_effect.SideEffectDetailPageActivity;
import com.example.mediwon.view_model.SideEffect;

import java.util.ArrayList;
import java.util.List;

public class SearchSideEffectAdapter extends RecyclerView.Adapter<SearchSideEffectAdapter.ViewHolder> implements Filterable {

    private List<SideEffect> dataSet;
    private List<SideEffect> dataSetAll;

    // 생성자에서 데이터 리스트를 전달받음
    public SearchSideEffectAdapter(List<SideEffect> dataSet) {
        this.dataSet = dataSet;
        dataSetAll = new ArrayList<>(dataSet);
    }

    // ViewHolder 클래스 : 화면에 표시될 아이템 View를 저장
    // 자식 View를 포함한 레이아웃 단위의 View를 하나의 ViewHolder로 생성
    public class ViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public TextView medicineNameTextView;

        public ViewHolder(View view) {

            super(view);
            medicineNameTextView = view.findViewById(R.id.medicineName);
        }

    }

    // onCreateViewHolder() : 새로운 아이템 View를 위한 ViewHolder 객체를 생성하여 리턴
    @Override
    public SearchSideEffectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Create a new view. ViewHolder에 넣어줄 View를 찾음
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.side_effect_list_item, parent, false);

        // ViewHolder를 선언하고 View를 넣어줌
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    // onBindViewHolder() : position에 해당하는 데이터를 각 ViewHolder의 아이템 View에 표시
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // Bind data
        holder.medicineNameTextView.setText(dataSet.get(position).getName() + " (" + dataSet.get(position).getName_eng() + ")");

        // 아이템 클릭 시 이벤트 처리
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, SideEffectDetailPageActivity.class);
                intent.putExtra("name", dataSet.get(position).getName());                                       // 품명
                intent.putExtra("name_eng", dataSet.get(position).getName_eng());                               // 품명 (영문)
                intent.putExtra("components", dataSet.get(position).getComponents());                           // 구성제제
                intent.putExtra("period", dataSet.get(position).getPeriod());                                   // 기간
                intent.putExtra("signal_kor", dataSet.get(position).getSignal_kor());                           // 실마리정보 (국문)
                intent.putExtra("signal_eng", dataSet.get(position).getSignal_eng());                           // 실마리정보 (영문)
                intent.putExtra("elucidatoryNotes", dataSet.get(position).getElucidatoryNotes());               // 비고
                intent.putExtra("signalGuide", dataSet.get(position).getSignalGuide());                         // 실마리정보안내
                intent.putExtra("monitoring", dataSet.get(position).getMonitoring());                           // 지속적모니터링안내
                intent.putExtra("permissionChangeGuide", dataSet.get(position).getPermissionChangeGuide());     // 허가사항변경안내
                context.startActivity(intent);
            }
        });
    }

    // Return the size of dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<SideEffect> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataSetAll);
            } else {
                String filterPattern  = constraint.toString().toLowerCase().trim();

                for(SideEffect data : dataSetAll) {
                    if(data.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(data);
                    }
                    if(data.getName_eng().toLowerCase().contains(filterPattern)) {
                        filteredList.add(data);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataSet.clear();
            dataSet.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}