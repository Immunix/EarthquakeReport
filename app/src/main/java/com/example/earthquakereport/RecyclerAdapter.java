package com.example.earthquakereport;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private final ArrayList<Earthquake> mArrayList;

    public RecyclerAdapter (ArrayList<Earthquake> arrayList) {
        mArrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.earthquake_list_cell, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.magnitude.setText(mArrayList.get(position).getMagnitude());
        holder.location.setText(mArrayList.get(position).getLocation());
        holder.date.setText(mArrayList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView magnitude;
        public TextView location;
        public TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.magnitude = itemView.findViewById(R.id.magnitude);
            this.location = itemView.findViewById(R.id.location);
            this.date = itemView.findViewById(R.id.date);
        }
    }
}
