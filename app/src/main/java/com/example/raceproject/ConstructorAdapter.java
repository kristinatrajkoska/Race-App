package com.example.raceproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ConstructorAdapter extends RecyclerView.Adapter<ConstructorAdapter.ConstructorViewHolder> {

    private List<Constructor> constructorList;

    public ConstructorAdapter(List<Constructor> constructorList) {
        this.constructorList = constructorList;
    }

    @NonNull
    @Override
    public ConstructorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.constructor_item, parent, false);
        return new ConstructorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConstructorViewHolder holder, int position) {
        Constructor constructor = constructorList.get(position);
        holder.position.setText(String.valueOf(constructor.getPosition()));
        holder.teamName.setText(constructor.getTeamName());
        holder.points.setText(String.valueOf(constructor.getPoints()));
    }

    @Override
    public int getItemCount() {
        return constructorList.size();
    }

    static class ConstructorViewHolder extends RecyclerView.ViewHolder {
        TextView position;
        TextView teamName;
        TextView points;

        public ConstructorViewHolder(@NonNull View itemView) {
            super(itemView);
            position = itemView.findViewById(R.id.position);
            teamName = itemView.findViewById(R.id.teamName);
            points = itemView.findViewById(R.id.points);
        }
    }
}
