package com.example.earthquakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private static final String LOCATION_SEPARATOR = " of ";

    private final ArrayList<Earthquake> mArrayList;
    private final Context mContext;

    public RecyclerAdapter (Context context, ArrayList<Earthquake> arrayList) {
        mArrayList = arrayList;
        mContext = context;
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
        String originalLocation = mArrayList.get(position).getLocation();
        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = mContext.getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        Date dateObject = new Date(mArrayList.get(position).getTimeInMilliseconds());

        holder.magnitude.setText(mArrayList.get(position).getMagnitude());

        holder.locationOffset.setText(locationOffset);
        holder.primaryLocation.setText(primaryLocation);

        String formattedDate = formatDate(dateObject);
        holder.date.setText(formattedDate);

        String formattedTime = formatTime(dateObject);
        holder.time.setText(formattedTime);
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView magnitude;
        public TextView locationOffset;
        public TextView primaryLocation;
        public TextView date;
        public TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.magnitude = itemView.findViewById(R.id.magnitude);
            this.locationOffset = itemView.findViewById(R.id.location_offset);
            this.primaryLocation = itemView.findViewById(R.id.primary_location);
            this.date = itemView.findViewById(R.id.date);
            this.time = itemView.findViewById(R.id.time);
        }
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

}
