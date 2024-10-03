package com.example.raceproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ConstructorsChampionship extends AppCompatActivity {

    private RecyclerView constructorsRecyclerView;
    private ConstructorAdapter constructorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constructors_championship);

        constructorsRecyclerView = findViewById(R.id.constructorsRecyclerView);
        constructorsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Constructor> constructors = new ArrayList<>();
        constructors.add(new Constructor(1, "McLaren Mercedes", 516));
        constructors.add(new Constructor(2, "Red Bull Racing Honda RBPT", 475));
        constructors.add(new Constructor(3, "Ferrari", 441));
        constructors.add(new Constructor(4, "Mercedes", 329));
        constructors.add(new Constructor(5, "Aston Martin Aramco Mercedes", 86));
        constructors.add(new Constructor(6, "RB Honda RBPT", 34));
        constructors.add(new Constructor(7, "Haas Ferrari", 31));
        constructors.add(new Constructor(8, "Williams Mercedes", 16));
        constructors.add(new Constructor(9, "Alpine Renault", 13));
        constructors.add(new Constructor(10, "Kick Sauber Ferrari", 0));

        constructorAdapter = new ConstructorAdapter(constructors);
        constructorsRecyclerView.setAdapter(constructorAdapter);
    }
}
