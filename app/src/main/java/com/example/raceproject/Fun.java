package com.example.raceproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Fun extends AppCompatActivity {

    private TextView timerTextView, teamText;
    private Button pitStopButton;
    private CountDownTimer countDownTimer;
    private long timeRemaining = 2200; // 2.2 seconds in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun);

        timerTextView = findViewById(R.id.timerTextView);
        pitStopButton = findViewById(R.id.pitStopButton);
        teamText = findViewById(R.id.teamText);
        ImageView pitStopGif = findViewById(R.id.pitStopGif);

        // DA Nemozis da kliknis sledno mora da pocekas pitstopot
        teamText.setVisibility(View.GONE);

        pitStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPitStop();
            }
        });

        teamText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Fun.this, BuildTeamActivity.class);
                startActivity(intent);
            }
        });
    }

    //timer
    private void startPitStop() {
        timerTextView.setText("2.2s");

        //pusti go, broj vreme, meni tekst
        countDownTimer = new CountDownTimer(timeRemaining, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished;
                long seconds = (millisUntilFinished / 1000) % 60;
                long milliseconds = (millisUntilFinished % 1000) / 10;
                timerTextView.setText(String.format("%d.%02d", seconds, milliseconds));
            }

            @Override
            public void onFinish() {
                timerTextView.setText("Pit Stop Complete!");
                Toast.makeText(Fun.this, "Pit stop completed in 2.2 seconds!", Toast.LENGTH_SHORT).show();

                teamText.setVisibility(View.VISIBLE);
            }
        }.start();
    }
}
