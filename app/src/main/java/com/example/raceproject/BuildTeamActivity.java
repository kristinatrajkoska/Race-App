package com.example.raceproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BuildTeamActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private MaterialButton submitButton;
    private TextInputEditText teamNameEditText;
    private TextInputLayout teamNameInputLayout;
    private ImageView selectedDriver1ImageView;
    private ImageView selectedDriver2ImageView;
    private List<DriverGame> drivers;
    private DriverGame selectedDriver1, selectedDriver2;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_team);

        gridLayout = findViewById(R.id.gridLayout);
        submitButton = findViewById(R.id.submitButton);
        teamNameEditText = findViewById(R.id.teamNameEditText);
        teamNameInputLayout = findViewById(R.id.teamNameInputLayout);
        selectedDriver1ImageView = findViewById(R.id.selectedDriver1ImageView);
        selectedDriver2ImageView = findViewById(R.id.selectedDriver2ImageView);

        //za bazata vtora
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("favoriteDrivers");

        drivers = new ArrayList<>();
        initializeDrivers();
        prepareDrivers();

        //odma da ni pokazva so sme odbrale
        fetchLatestFavoriteDriver();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teamName = teamNameEditText.getText().toString().trim();

                if (selectedDriver1 != null && selectedDriver2 != null && !teamName.isEmpty()) {
                    Toast.makeText(BuildTeamActivity.this,
                            "Team '" + teamName + "' Selected: " + selectedDriver1.getName() + " and " + selectedDriver2.getName(),
                            Toast.LENGTH_SHORT).show();

                    //da gi pokazi na krajo novite kako new team
                    selectedDriver1ImageView.setImageResource(selectedDriver1.getImageResourceId());
                    selectedDriver2ImageView.setImageResource(selectedDriver2.getImageResourceId());
                } else {
                    Toast.makeText(BuildTeamActivity.this, "Please select two drivers and enter a team name.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchLatestFavoriteDriver() {

        //POSLEDNIOT SAMO PREDHODNITE IZBORI LIMITIRANI
        databaseReference.orderByChild("timestamp").limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String favoriteDriver = snapshot.child("name").getValue(String.class);
                        String favoriteTeam = snapshot.child("team").getValue(String.class);
                        showFavoriteDriverMessage(favoriteDriver, favoriteTeam);
                    }
                } else {
                    Log.d("Firebase", "No favorite drivers found.");
                    Toast.makeText(BuildTeamActivity.this, "No favorite drivers found.", Toast.LENGTH_SHORT).show();
                }
            }
            //ako ne fati da znam od stranata
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Error fetching data: " + databaseError.getMessage());
                Toast.makeText(BuildTeamActivity.this, "Error fetching data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showFavoriteDriverMessage(String favoriteDriver, String favoriteTeam) {
        String message = "You've told us your favorite driver is " + favoriteDriver + " from team " + favoriteTeam + ".";
        Toast.makeText(BuildTeamActivity.this, message, Toast.LENGTH_LONG).show();
    }

    private void prepareDrivers() {
        gridLayout.removeAllViews();

        //naredi gi slikickite da stojat ko so treba
        for (DriverGame driver : drivers) {
            ImageView driverView = new ImageView(this);
            driverView.setImageResource(driver.getImageResourceId());
            driverView.setPadding(8, 8, 8, 8); //probaj i so 10
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            driverView.setLayoutParams(params);
            driverView.setAdjustViewBounds(true);
            driverView.setScaleType(ImageView.ScaleType.CENTER_CROP);


            driverView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectDriver(driver);
                }
            });

            gridLayout.addView(driverView);
        }
    }

    private void selectDriver(DriverGame driver) {
        if (selectedDriver1 == null) {
            selectedDriver1 = driver; //prvio so go odbirame
            Toast.makeText(this, "Selected: " + driver.getName(), Toast.LENGTH_SHORT).show();
        } else if (selectedDriver2 == null && !driver.equals(selectedDriver1)) {
            selectedDriver2 = driver; //so driver e selektiran i vtoriot
            Toast.makeText(this, "Selected: " + driver.getName(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "You can only select two different drivers.", Toast.LENGTH_SHORT).show();
        }
    }

    private void initializeDrivers() {
        drivers.add(new DriverGame("Lewis Hamilton", R.drawable.lewis_hamilton));
        drivers.add(new DriverGame("Max Verstappen", R.drawable.max_verstappen));
        drivers.add(new DriverGame("Charles Leclerc", R.drawable.charles_leclerc));
        drivers.add(new DriverGame("Lando Norris", R.drawable.lando_norris));
        drivers.add(new DriverGame("Sergio Pérez", R.drawable.sergio_perez));
        drivers.add(new DriverGame("Fernando Alonso", R.drawable.fernando_alonso));
    }
}
