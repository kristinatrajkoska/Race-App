package com.example.raceproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;

public class Welcome extends AppCompatActivity {

    private TextView greetingText, funText;
    private LinearLayout home, races, drivers, standings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        greetingText = findViewById(R.id.greetingText);

        //se premisliv za home sega logout ima znacenje
        home = findViewById(R.id.home);
        races = findViewById(R.id.races);
        drivers = findViewById(R.id.drivers);
        standings = findViewById(R.id.standings);
        funText = findViewById(R.id.funText);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        races.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this, Races.class);
                startActivity(intent);
            }
        });

        drivers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this, Drivers.class);
                startActivity(intent);
            }
        });

        //popup menu napraj
        standings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStandingsMenu(view);
            }
        });


        funText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this, Fun.class);
                startActivity(intent);
            }
        });
    }

    //odlogirvanje
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Welcome.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void showStandingsMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(Welcome.this, view);
        popupMenu.getMenu().add("Drivers Championship");
        popupMenu.getMenu().add("Constructors Championship");

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getTitle().equals("Drivers Championship")) {
                    Intent intent = new Intent(Welcome.this, DriversChampionship.class);
                    startActivity(intent);
                } else if (menuItem.getTitle().equals("Constructors Championship")) {
                    Intent intent = new Intent(Welcome.this, ConstructorsChampionship.class);
                    startActivity(intent);
                }
                return true;
            }
        });

        popupMenu.show();
    }
}
