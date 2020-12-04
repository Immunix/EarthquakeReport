package com.example.earthquakereport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EarthquakeRecyclerAdapter.OnEarthquakeClickListener {

    ArrayList<Earthquake> earthquakes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        earthquakes = QueryUtils.extractEarthquakes();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        EarthquakeRecyclerAdapter adapter = new EarthquakeRecyclerAdapter(this, earthquakes, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onEarthquakeClick(int position) {
        Uri earthquakeUri = Uri.parse(earthquakes.get(position).getUrl());
        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
        startActivity(websiteIntent);
    }
}