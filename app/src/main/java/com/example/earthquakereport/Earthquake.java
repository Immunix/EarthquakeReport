package com.example.earthquakereport;

public class Earthquake {
    private final String mMagnitude;
    private final String mLocation;
    private final long mTimeInMilliseconds;

    public Earthquake(String magnitude, String location, long timeInMilliseconds) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public long getTimeInMilliseconds() { return mTimeInMilliseconds; }

    public String getLocation() {
        return mLocation;
    }
}