package com.example.mediwon.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mediwon.R;
import com.example.mediwon.view_model.Medicine;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Medicine> dataSet;

    // Provide a reference to the views for each data item
    // Provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

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

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context context, ArrayList<Medicine> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
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
                .load(dataSet.get(position).getImageUrl())
                .into(holder.imageView);
        holder.nameTextView.setText(dataSet.get(position).getName());
        holder.enterpriseTextView.setText(dataSet.get(position).getEnterprise());
    }

    // Return the size of dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void clear() {
        dataSet.clear();
    }

    public void remove(int position) {
        dataSet.remove(dataSet.get(position));
    }

    public void add(Medicine medicine) {
        dataSet.add(medicine);
    }

}
