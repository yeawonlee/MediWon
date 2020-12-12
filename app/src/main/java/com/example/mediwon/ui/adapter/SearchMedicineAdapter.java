package com.example.mediwon.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mediwon.MedicineDetailPageActivity;
import com.example.mediwon.R;
import com.example.mediwon.view_model.MedicineIdentification;

import java.util.ArrayList;
import java.util.List;

public class SearchMedicineAdapter extends RecyclerView.Adapter<SearchMedicineAdapter.ViewHolder> implements Filterable {

    private List<MedicineIdentification> dataSet;
    private List<MedicineIdentification> dataSetAll;

    // 생성자에서 데이터 리스트를 전달받음
    public SearchMedicineAdapter(List<MedicineIdentification> dataSet) {
        this.dataSet = dataSet;
        dataSetAll = new ArrayList<>(dataSet);
    }

    // ViewHolder 클래스 : 화면에 표시될 아이템 View를 저장
    // 자식 View를 포함한 레이아웃 단위의 View를 하나의 ViewHolder로 생성
    public class ViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public ImageView imageView;
        public TextView nameTextView;
        public TextView enterpriseTextView;

        public ViewHolder(View view) {

            super(view);
            imageView = view.findViewById(R.id.medicineImage);
            nameTextView = view.findViewById(R.id.medicineName);
            enterpriseTextView = view.findViewById(R.id.enterprise);
        }

    }

    // onCreateViewHolder() : 새로운 아이템 View를 위한 ViewHolder 객체를 생성하여 리턴
    @Override
    public SearchMedicineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Create a new view. ViewHolder에 넣어줄 View를 찾음
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item, parent, false);

        // ViewHolder를 선언하고 View를 넣어줌
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    // onBindViewHolder() : position에 해당하는 데이터를 각 ViewHolder의 아이템 View에 표시
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // Bind data
        Glide.with(holder.imageView)
                .load(dataSet.get(position).getImageUrl())
                .into(holder.imageView);
        holder.nameTextView.setText(dataSet.get(position).getName());
        holder.enterpriseTextView.setText(dataSet.get(position).getEnterprise());

        // 아이템 클릭 시 이벤트 처리
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Log.v("adapter", "position : " + position);
                Intent intent = new Intent(context, MedicineDetailPageActivity.class);
                intent.putExtra("name", dataSet.get(position).getName());
                intent.putExtra("engName", dataSet.get(position).getEngName());
                intent.putExtra("image", dataSet.get(position).getImageUrl());
                intent.putExtra("enterprise", dataSet.get(position).getEnterprise());
                intent.putExtra("classNo", dataSet.get(position).getClassNo());
                intent.putExtra("className", dataSet.get(position).getClassName());
                intent.putExtra("etcOtcName", dataSet.get(position).getEtcOtcName());
                intent.putExtra("ediCode", dataSet.get(position).getEdiCode());
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
            List<MedicineIdentification> filteredList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0) {
                filteredList.addAll(dataSetAll);
            } else {
                String filterPattern  = constraint.toString().toLowerCase().trim();

                for(MedicineIdentification data : dataSetAll) {
                    if(data.getName().toLowerCase().contains(filterPattern)) {
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

    /*
    public void clear() {
        dataSet.clear();
    }

    public void remove(int position) {
        dataSet.remove(dataSet.get(position));
    }

    public void add(MedicineIdentification medicine) {
        dataSet.add(medicine);
    }
    */
}
