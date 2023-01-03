package com.example.project.calendar.datelist;

import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.calendar.CalendarWithList;

import java.util.ArrayList;

public class mAdapter extends RecyclerView.Adapter<mAdapter.mViewHolder> {

    private ArrayList<DateDTO> arrayList;
    private int selectedPosition = -1;
    private LinearLayoutManager manager;

    public mAdapter(ArrayList<DateDTO> arrayList, LinearLayoutManager manager) {
        this.arrayList = arrayList;
        this.manager = manager;
    }

    @NonNull
    @Override
    public mAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item, parent, false);

        mViewHolder holder = new mViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull mAdapter.mViewHolder holder, int position) {
        String dayData = arrayList.get(position).day;
        String dateData = arrayList.get(position).date;
        int stateData = arrayList.get(position).complete;

        holder.day.setText(dayData);
        holder.date.setText(dateData);

        switch (stateData) {
            case 1: holder.state.setImageResource(R.drawable.no_data_state_circle); break;
            case 2: holder.state.setImageResource(R.drawable.exist_data_state_circle); break;
        }

        if (selectedPosition == position) {
            holder.itemView.setBackgroundColor(Color.GRAY);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

        holder.itemView.setOnClickListener(v -> {
            if (selectedPosition >= 0)
                notifyItemChanged(selectedPosition);
            selectedPosition = holder.getAdapterPosition();
            CalendarWithList.fragmentRefreshWithData(dateData);
            manager.scrollToPositionWithOffset(selectedPosition+1, 600);
            notifyItemChanged(selectedPosition);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setState(int idx, int state) {arrayList.get(idx).setComplete(state);}

    public static class mViewHolder extends RecyclerView.ViewHolder {

        public TextView date;
        public TextView day;
        public ImageView state;

        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            this.date = itemView.findViewById(R.id.date_cell);
            this.day = itemView.findViewById(R.id.day_cell);
            this.state = itemView.findViewById(R.id.state);
        }
    }
}
