package com.example.mediwon.ui.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.mediwon.view_model.Medicine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements Filterable {

    //private Context context;

    private List<Medicine> dataSet;
    private List<Medicine> dataSetAll;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Medicine> dataSet) {
        //this.context = context;
        this.dataSet = dataSet;
        this.dataSetAll = dataSet;
    }

    // Provide a reference to the views for each data item
    // Provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // each data item is just a string in this case
        public ImageView imageView;
        public TextView nameTextView;
        public TextView enterpriseTextView;

        public ViewHolder(View view) {

            super(view);
            imageView = view.findViewById(R.id.medicineImage);
            nameTextView = view.findViewById(R.id.medicineName);
            enterpriseTextView = view.findViewById(R.id.enterprise);
            view.setOnClickListener(this);
        }

        // 아이템 클릭 시 이벤트 처리
        @Override
        public void onClick(View v) {

            Context context = v.getContext();
            int position = getAdapterPosition();
            Intent intent = new Intent(context, MedicineDetailPageActivity.class);
            intent.putExtra("image", dataSetAll.get(position).getImageUrl());
            intent.putExtra("name", dataSetAll.get(position).getName());
            intent.putExtra("enterprise", dataSetAll.get(position).getEnterprise());
            context.startActivity(intent);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view. ViewHolder에 넣어줄 view를 찾음
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item, parent, false);

        // ViewHolder를 선언하고 view를 넣어줌
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Glide.with(context)  // Activity 자리. 안 되면 context 넣어보기
        Glide.with(holder.imageView)
                .load(dataSetAll.get(position).getImageUrl())
                .into(holder.imageView);
        holder.nameTextView.setText(dataSetAll.get(position).getName());
        holder.enterpriseTextView.setText(dataSetAll.get(position).getEnterprise());
    }

    // Return the size of dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataSetAll.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String character = constraint.toString();
                if(character.isEmpty()) {
                    dataSetAll = dataSet;
                }
                else {
                    List<Medicine> filterList = new ArrayList<>();
                    for(Medicine data: dataSetAll) {
                        if(data.getName().toLowerCase().contains(character.toLowerCase())) {
                            filterList.add(data);
                        }
                    }

                    dataSetAll = filterList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dataSetAll;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dataSetAll = (ArrayList<Medicine>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    /*
    Filter filter = new Filter() {

        // run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<String> filteredList = new ArrayList<>();

            if(constraint.toString().isEmpty()) {
                filteredList.addAll(Collections.singleton(dataSetAll.toString()));
            }
            else {
                for (String data: dataSetAll) {
                    if(data.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(data);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        // run on a UI thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataSet.clear();
            dataSet.addAll((Collection<? extends Medicine>) results.values);
            notifyDataSetChanged();
        }
    };
    */

    /*
    public void clear() {
        dataSet.clear();
    }

    public void remove(int position) {
        dataSet.remove(dataSet.get(position));
    }

    public void add(Medicine medicine) {
        dataSet.add(medicine);
    }
    */
}
