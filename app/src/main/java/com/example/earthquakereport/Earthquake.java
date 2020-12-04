package com.example.earthquakereport;

public class Earthquake {
    private final double mMagnitude;
    private final String mLocation;
    private final long mTimeInMilliseconds;
    private final String mUrl;

    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public long getTimeInMilliseconds() { return mTimeInMilliseconds; }

    public String getLocation() {
        return mLocation;
    }

    public String getUrl() { return mUrl; };
}