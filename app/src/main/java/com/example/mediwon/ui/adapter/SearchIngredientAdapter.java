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

import com.example.mediwon.IngredientDetailPageActivity;
import com.example.mediwon.R;
import com.example.mediwon.view_model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class SearchIngredientAdapter extends RecyclerView.Adapter<SearchIngredientAdapter.ViewHolder> implements Filterable {

    private List<Ingredient> dataSet;
    private List<Ingredient> dataSetAll;

    // 생성자에서 데이터 리스트를 전달받음
    public SearchIngredientAdapter(List<Ingredient> dataSet) {
        this.dataSet = dataSet;
        dataSetAll = new ArrayList<>(dataSet);
    }

    // ViewHolder 클래스 : 화면에 표시될 아이템 View를 저장
    // 자식 View를 포함한 레이아웃 단위의 View를 하나의 ViewHolder로 생성
    public class ViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public TextView ingredientNameTextView;

        public ViewHolder(View view) {

            super(view);
            ingredientNameTextView = view.findViewById(R.id.ingredientName);
        }

    }

    // onCreateViewHolder() : 새로운 아이템 View를 위한 ViewHolder 객체를 생성하여 리턴
    @Override
    public SearchIngredientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Create a new view. ViewHolder에 넣어줄 View를 찾음
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item, parent, false);

        // ViewHolder를 선언하고 View를 넣어줌
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    // onBindViewHolder() : position에 해당하는 데이터를 각 ViewHolder의 아이템 View에 표시
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // Bind data
        holder.ingredientNameTextView.setText(dataSet.get(position).getGnlNm());
        // 아이템 클릭 시 이벤트 처리
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                //Log.v("adapter", "position : " + position);
                Intent intent = new Intent(context, IngredientDetailPageActivity.class);
                intent.putExtra("divNm", dataSet.get(position).getDivNm());     // 분류명
                intent.putExtra("fomnTpNm", dataSet.get(position).getFomnTpNm());    // 제형구분명
                intent.putExtra("gnlNm", dataSet.get(position).getGnlNm());      // 일반명
                intent.putExtra("gnlNmCd", dataSet.get(position).getGnlNmCd());     // 일반명코드
                intent.putExtra("injcPthNm", dataSet.get(position).getInjcPthNm());   // 투여경로명
                intent.putExtra("iqtyTxt", dataSet.get(position).getIqtyTxt());     // 함량내용
                intent.putExtra("meftDivNo", dataSet.get(position).getMeftDivNo()); // 약효분류번호
                intent.putExtra("unit", dataSet.get(position).getUnit());     // 단위
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
            List<Ingredient> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataSetAll);
            } else {
                String filterPattern  = constraint.toString().toLowerCase().trim();

                for(Ingredient data : dataSetAll) {
                    if(data.getGnlNm().toLowerCase().contains(filterPattern)) {
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
