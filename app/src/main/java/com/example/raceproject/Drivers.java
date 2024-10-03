package com.example.raceproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Drivers extends AppCompatActivity {

    private RecyclerView driversRecyclerView;
    private TeamAdapter teamAdapter;
    private EditText favoriteDriverInput, favoriteTeamInput;
    private Button submitBtn;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);

        driversRecyclerView = findViewById(R.id.driversRecyclerView);
        driversRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<Team> teams = new ArrayList<>();
        teams.add(new Team("Mercedes", Arrays.asList("Lewis Hamilton", "George Russell")));
        teams.add(new Team("Red Bull Racing", Arrays.asList("Max Verstappen", "Sergio Perez")));
        teams.add(new Team("Ferrari", Arrays.asList("Charles Leclerc", "Carlos Sainz")));
        teams.add(new Team("McLaren", Arrays.asList("Lando Norris", "Oscar Piastri")));
        teams.add(new Team("Alpine", Arrays.asList("Esteban Ocon", "Pierre Gasly")));
        teams.add(new Team("VisaCashapp RB", Arrays.asList("Yuki Tsunoda", "Daniel Ricciardo")));
        teams.add(new Team("Aston Martin", Arrays.asList("Lance Stroll", "Fernando Alonso")));
        teams.add(new Team("Haas", Arrays.asList("Kevin Magnussen", "Nico Hulkenberg")));
        teams.add(new Team("Kick Sauber", Arrays.asList("Valtteri Bottas", "Zhou Guanyu")));
        teams.add(new Team("Williams", Arrays.asList("Alexander Albon", "Franco Colapinto")));


        teamAdapter = new TeamAdapter(teams);
        driversRecyclerView.setAdapter(teamAdapter);

        //FORM ZA VNESVANJE FAVS
        favoriteDriverInput = findViewById(R.id.favoriteDriver);
        favoriteTeamInput = findViewById(R.id.favoriteTeam);
        submitBtn = findViewById(R.id.submitBtn);

        //povrzi so firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("favoriteDrivers");

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitQuestionnaire();
            }
        });
    }

    //ovde napraj go formot
    private void submitQuestionnaire() {
        String favoriteDriver = favoriteDriverInput.getText().toString().trim();
        String favoriteTeam = favoriteTeamInput.getText().toString().trim();

        if (TextUtils.isEmpty(favoriteDriver)) {
            favoriteDriverInput.setError("Favorite driver is required");
            return;
        }

        if (TextUtils.isEmpty(favoriteTeam)) {
            favoriteTeamInput.setError("Favorite team is required");
            return;
        }

        // mora unique keys za da ne gi vadi predhodnite odgovori
        String id = databaseReference.push().getKey();
        Map<String, Object> driverData = new HashMap<>();
        driverData.put("name", favoriteDriver);
        driverData.put("team", favoriteTeam);
        driverData.put("timestamp", ServerValue.TIMESTAMP);

        //save to database
        databaseReference.child(id).setValue(driverData)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(Drivers.this, "Submitted! Favorite Driver: " + favoriteDriver + ", Favorite Team: " + favoriteTeam, Toast.LENGTH_LONG).show();
                    // ISCISTI !!!
                    favoriteDriverInput.setText("");
                    favoriteTeamInput.setText("");
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(Drivers.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }
}
