package com.example.raceproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class DriversChampionship extends AppCompatActivity {

    private RecyclerView driversRecyclerView;
    private DriverListAdapter driverListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers_championship);

        driversRecyclerView = findViewById(R.id.driversRecyclerView);
        driversRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<DriverItem> drivers = new ArrayList<>();
        drivers.add(new DriverItem(1, "Max Verstappen", "NED", "Red Bull Racing Honda RBPT", 331));
        drivers.add(new DriverItem(2, "Lando Norris", "GBR", "McLaren Mercedes", 279));
        drivers.add(new DriverItem(3, "Charles Leclerc", "MON", "Ferrari", 245));
        drivers.add(new DriverItem(4, "Oscar Piastri", "AUS", "McLaren Mercedes", 237));
        drivers.add(new DriverItem(5, "Carlos Sainz", "ESP", "Ferrari", 190));
        drivers.add(new DriverItem(6, "Lewis Hamilton", "GBR", "Mercedes", 174));
        drivers.add(new DriverItem(7, "George Russell", "GBR", "Mercedes", 155));
        drivers.add(new DriverItem(8, "Sergio Perez", "MEX", "Red Bull Racing Honda RBPT", 144));
        drivers.add(new DriverItem(9, "Fernando Alonso", "ESP", "Aston Martin Aramco Mercedes", 62));
        drivers.add(new DriverItem(10, "Nico Hulkenberg", "GER", "Haas Ferrari", 24));
        drivers.add(new DriverItem(11, "Lance Stroll", "CAN", "Aston Martin Aramco Mercedes", 24));
        drivers.add(new DriverItem(12, "Yuki Tsunoda", "JPN", "RB Honda RBPT", 22));
        drivers.add(new DriverItem(13, "Alexander Albon", "THA", "Williams Mercedes", 12));
        drivers.add(new DriverItem(14, "Daniel Ricciardo", "AUS", "RB Honda RBPT", 12));
        drivers.add(new DriverItem(15, "Pierre Gasly", "FRA", "Alpine Renault", 8));
        drivers.add(new DriverItem(16, "Oliver Bearman", "GBR", "Haas Ferrari", 7));
        drivers.add(new DriverItem(17, "Kevin Magnussen", "DEN", "Haas Ferrari", 6));
        drivers.add(new DriverItem(18, "Esteban Ocon", "FRA", "Alpine Renault", 5));
        drivers.add(new DriverItem(19, "Franco Colapinto", "ARG", "Williams Mercedes", 4));
        drivers.add(new DriverItem(20, "Zhou Guanyu", "CHN", "Kick Sauber Ferrari", 0));
        drivers.add(new DriverItem(21, "Logan Sargeant", "USA", "Williams Mercedes", 0));
        drivers.add(new DriverItem(22, "Valtteri Bottas", "FIN", "Kick Sauber Ferrari", 0));

        driverListAdapter = new DriverListAdapter(drivers);
        driversRecyclerView.setAdapter(driverListAdapter);
    }
}
