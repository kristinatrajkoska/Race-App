package com.example.raceproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
// RaceAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RaceAdapter extends RecyclerView.Adapter<RaceAdapter.RaceViewHolder> {

    private ArrayList<Race> raceList;
    private OnRaceClickListener onRaceClickListener;

    // Constructor
    public RaceAdapter(ArrayList<Race> raceList, OnRaceClickListener listener) {
        this.raceList = raceList;
        this.onRaceClickListener = listener;
    }

    @NonNull
    @Override
    public RaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.race_item, parent, false);
        return new RaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RaceViewHolder holder, int position) {
        Race race = raceList.get(position);
        holder.raceName.setText(race.getName());
        holder.raceLocation.setText(race.getLocation());

        // Handle item click
        holder.itemView.setOnClickListener(v -> {
            if (onRaceClickListener != null) {
                onRaceClickListener.onRaceClick(race);
            }
        });
    }

    @Override
    public int getItemCount() {
        return raceList.size();
    }

    public static class RaceViewHolder extends RecyclerView.ViewHolder {
        TextView raceName, raceLocation;

        public RaceViewHolder(@NonNull View itemView) {
            super(itemView);
            raceName = itemView.findViewById(R.id.raceName);
            raceLocation = itemView.findViewById(R.id.raceLocation);
        }
    }

    // Interface for click handling
    public interface OnRaceClickListener {
        void onRaceClick(Race race);
    }
}
