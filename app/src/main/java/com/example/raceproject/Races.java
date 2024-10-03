package com.example.raceproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class Races extends AppCompatActivity implements RaceAdapter.OnRaceClickListener {

    private RecyclerView raceRecyclerView;
    private ArrayList<Race> raceList;
    private WebView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_races);

        raceRecyclerView = findViewById(R.id.raceRecyclerView);
        raceList = new ArrayList<>();

        // Sample race data with specific dates
        raceList.add(new Race("Monza", "Italy", new LatLng(45.6155, 9.2811), "2024-09-01"));
        raceList.add(new Race("Silverstone", "UK", new LatLng(52.0733, -1.0151), "2024-07-14"));
        raceList.add(new Race("Spa-Francorchamps", "Belgium", new LatLng(50.4372, 5.9714), "2024-08-25"));
        raceList.add(new Race("Suzuka", "Japan", new LatLng(34.8431, 136.5417), "2024-10-06"));
        raceList.add(new Race("Interlagos", "Brazil", new LatLng(-23.7036, -46.6997), "2024-11-10"));
        raceList.add(new Race("Circuit of the Americas", "USA", new LatLng(30.1346, -97.6359), "2024-10-27"));
        raceList.add(new Race("Monaco", "Monaco", new LatLng(43.7340, 7.4206), "2024-05-26"));
        raceList.add(new Race("Abu Dhabi", "UAE", new LatLng(24.4672, 54.6031), "2024-11-24"));
        raceList.add(new Race("Melbourne", "Australia", new LatLng(-37.8497, 144.968), "2024-03-31"));
        raceList.add(new Race("Bahrain", "Bahrain", new LatLng(26.0325, 50.5105), "2024-03-17"));

        // Create the RaceAdapter with the click listener
        RaceAdapter raceAdapter = new RaceAdapter(raceList, this);
        raceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        raceRecyclerView.setAdapter(raceAdapter);

        // Initialize the WebView for the map
        mapView = findViewById(R.id.mapView);
        mapView.getSettings().setJavaScriptEnabled(true);
        mapView.setWebViewClient(new WebViewClient());
        mapView.loadUrl("https://www.openstreetmap.org");

        // Handle notification logic (example)
        scheduleRaceNotification();
    }

    @Override
    public void onRaceClick(Race race) {
        if (mapView != null) {
            String url = "https://www.openstreetmap.org/#map=15/" + race.getLatLng().latitude + "/" + race.getLatLng().longitude;
            mapView.loadUrl(url);
            Toast.makeText(this, "Date: " + race.getDate(), Toast.LENGTH_SHORT).show(); // Show race date
        }
    }

    private void scheduleRaceNotification() {
        Toast.makeText(this, "Notifications set for upcoming races", Toast.LENGTH_SHORT).show();
    }
}
