package com.example.raceproject;

public class DriverItem {
    private int position;
    private String name;
    private String nationality;
    private String car;
    private int points;

    public DriverItem(int position, String name, String nationality, String car, int points) {
        this.position = position;
        this.name = name;
        this.nationality = nationality;
        this.car = car;
        this.points = points;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getCar() {
        return car;
    }

    public int getPoints() {
        return points;
    }
}
