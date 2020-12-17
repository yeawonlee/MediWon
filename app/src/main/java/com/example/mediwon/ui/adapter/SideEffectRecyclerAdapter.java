package com.example.mediwon.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mediwon.R;
import com.example.mediwon.view_model.SideEffect;

import java.util.ArrayList;

public class SideEffectRecyclerAdapter extends RecyclerView.Adapter<SideEffectRecyclerAdapter.ViewHolder> {

    private ArrayList<SideEffect> dataSet;

    // 생성자에서 데이터 리스트를 전달받음
    public SideEffectRecyclerAdapter(ArrayList<SideEffect> dataSet) {
        this.dataSet = dataSet;
    }

    // ViewHolder 클래스 : 화면에 표시될 아이템 View를 저장
    // 자식 View를 포함한 레이아웃 단위의 View를 하나의 ViewHolder로 생성
    public class ViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public TextView nameTextView;                           // 품명
        public TextView nameEngTextView;                        // 품명 (영문)
        public TextView componentsTextView;                     // 구성제제
        public TextView periodTextView;                         // 기간
        public TextView signalKorTextView;                      // 실마리정보 (국문)
        public TextView signalEngTextView;                      // 실마리정보 (영문)
        public TextView elucidatoryNotesTextView;               // 비고
        public TextView signalGuideTextView;                    // 실마리정보안내
        public TextView monitoringTextView;                     // 지속적모니터링안내
        public TextView permissionChangeGuideTextView;          // 허가사항변경안내

        public ViewHolder(View view) {

            super(view);
            nameTextView = view.findViewById(R.id.name);
            nameEngTextView = view.findViewById(R.id.name_eng);
            componentsTextView = view.findViewById(R.id.components);
            periodTextView = view.findViewById(R.id.period);
            signalKorTextView = view.findViewById(R.id.signal_kor);
            signalEngTextView = view.findViewById(R.id.signal_eng);
            elucidatoryNotesTextView = view.findViewById(R.id.elucidatoryNotes);
            signalGuideTextView = view.findViewById(R.id.signalGuide);
            monitoringTextView = view.findViewById(R.id.monitoring);
            permissionChangeGuideTextView = view.findViewById(R.id.permissionChangeGuide);
        }

    }

    // onCreateViewHolder() : 새로운 아이템 View를 위한 ViewHolder 객체를 생성하여 리턴
    @Override
    public SideEffectRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Create a new view. ViewHolder에 넣어줄 View를 찾음
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.side_effect_recyclerview_item, parent, false);

        // ViewHolder를 선언하고 View를 넣어줌
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    // onBindViewHolder() : position에 해당하는 데이터를 각 ViewHolder의 아이템 View에 표시
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // Bind data
        holder.nameTextView.setText(dataSet.get(position).getName());
        holder.nameEngTextView.setText(dataSet.get(position).getName_eng());
        holder.componentsTextView.setText(dataSet.get(position).getComponents());
        holder.periodTextView.setText(dataSet.get(position).getPeriod());
        holder.signalKorTextView.setText(dataSet.get(position).getSignal_kor());
        holder.signalEngTextView.setText(dataSet.get(position).getSignal_eng());
        holder.elucidatoryNotesTextView.setText(dataSet.get(position).getElucidatoryNotes());
        holder.signalGuideTextView.setText(dataSet.get(position).getSignalGuide());
        holder.monitoringTextView.setText(dataSet.get(position).getMonitoring());
        holder.permissionChangeGuideTextView.setText(dataSet.get(position).getPermissionChangeGuide());
    }

    // Return the size of dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
