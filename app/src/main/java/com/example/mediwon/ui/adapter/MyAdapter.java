package com.example.mediwon.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mediwon.R;
import com.example.mediwon.view_model.Medicine;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Medicine> dataSet;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public ImageView imageView;
        public TextView nameTextView;
        public TextView pharmaceuticalCompanyTextView;

        public ViewHolder(View view) {

            super(view);
            imageView = view.findViewById(R.id.medicineImage);
            nameTextView = view.findViewById(R.id.medicineName);
            pharmaceuticalCompanyTextView = view.findViewById(R.id.pharmaceuticalCompany);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Medicine> dataSet) {
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
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.imageView.setImageResource(dataSet.get(position).getImage());
        holder.nameTextView.setText(dataSet.get(position).getName());
        holder.pharmaceuticalCompanyTextView.setText(dataSet.get(position).getPharmaceuticalCompany());
    }

    // Return the size of your dataset (invoked by the layout manager)
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
