package com.example.raceproject;
import com.google.android.gms.maps.model.LatLng;
// Race.java
import com.google.android.gms.maps.model.LatLng;

public class Race {

    private String name;
    private String location;
    private LatLng latLng;
    private String date;

    public Race(String name, String location, LatLng latLng, String date) {
        this.name = name;
        this.location = location;
        this.latLng = latLng;
        this.date=date;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getDate() {
        return date;
    }
}
