package com.example.earthquakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeRecyclerAdapter extends RecyclerView.Adapter<EarthquakeRecyclerAdapter.ViewHolder>{

    private static final String LOCATION_SEPARATOR = " of ";

    private final ArrayList<Earthquake> mArrayList;
    private final Context mContext;
    private final OnEarthquakeClickListener mQuakeListener;

    public EarthquakeRecyclerAdapter(Context context, ArrayList<Earthquake> arrayList, OnEarthquakeClickListener quakeListener) {
        mArrayList = arrayList;
        mContext = context;
        mQuakeListener = quakeListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.earthquake_list_cell, parent, false);
        return new ViewHolder(listItem, mQuakeListener);
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

        GradientDrawable magnitudeCircle = (GradientDrawable) holder.magnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(mArrayList.get(position).getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        Date dateObject = new Date(mArrayList.get(position).getTimeInMilliseconds());

        String formattedMagnitude = formatMagnitude(mArrayList.get(position).getMagnitude());
        holder.magnitude.setText(formattedMagnitude);

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

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView magnitude;
        public TextView locationOffset;
        public TextView primaryLocation;
        public TextView date;
        public TextView time;
        OnEarthquakeClickListener quakeListener;

        public ViewHolder(@NonNull View itemView, OnEarthquakeClickListener onEarthquakeClickListener) {
            super(itemView);
            this.magnitude = itemView.findViewById(R.id.magnitude);
            this.locationOffset = itemView.findViewById(R.id.location_offset);
            this.primaryLocation = itemView.findViewById(R.id.primary_location);
            this.date = itemView.findViewById(R.id.date);
            this.time = itemView.findViewById(R.id.time);
            this.quakeListener = onEarthquakeClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            quakeListener.onEarthquakeClick(getAdapterPosition());
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

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormatter = new DecimalFormat("0.0");
        return magnitudeFormatter.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(mContext, magnitudeColorResourceId);
    }

    public interface OnEarthquakeClickListener {
        void onEarthquakeClick(int position);
    }
}
