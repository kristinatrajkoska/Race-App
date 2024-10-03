package com.example.raceproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DriverListAdapter extends RecyclerView.Adapter<DriverListAdapter.DriverViewHolder> {

    private List<DriverItem> driverList;

    public DriverListAdapter(List<DriverItem> driverList) {
        this.driverList = driverList;
    }

    @NonNull
    @Override
    public DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_list_item, parent, false);
        return new DriverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverViewHolder holder, int position) {
        DriverItem driver = driverList.get(position);
        holder.position.setText(String.valueOf(driver.getPosition()));
        holder.driverName.setText(driver.getName());
        holder.nationality.setText(driver.getNationality());
        holder.car.setText(driver.getCar());
        holder.points.setText(String.valueOf(driver.getPoints()));
    }

    @Override
    public int getItemCount() {
        return driverList.size();
    }

    static class DriverViewHolder extends RecyclerView.ViewHolder {
        TextView position;
        TextView driverName;
        TextView nationality;
        TextView car;
        TextView points;

        public DriverViewHolder(@NonNull View itemView) {
            super(itemView);
            position = itemView.findViewById(R.id.position);
            driverName = itemView.findViewById(R.id.driverName);
            nationality = itemView.findViewById(R.id.nationality);
            car = itemView.findViewById(R.id.car);
            points = itemView.findViewById(R.id.points);
        }
    }
}
