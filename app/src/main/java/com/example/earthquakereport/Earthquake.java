package com.example.earthquakereport;

public class Earthquake {
    private final String mMagnitude;
    private final String mLocation;
    private final String mDate;

    public Earthquake(String magnitude, String location, String date) {
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public String getDate() {
        return mDate;
    }

    public String getLocation() {
        return mLocation;
    }
}