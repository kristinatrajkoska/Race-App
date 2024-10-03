package com.example.raceproject;

public class Constructor {
    private int position;
    private String teamName;
    private int points;

    public Constructor(int position, String teamName, int points) {
        this.position = position;
        this.teamName = teamName;
        this.points = points;
    }

    public int getPosition() {
        return position;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPoints() {
        return points;
    }
}
