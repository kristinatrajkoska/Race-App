package com.example.raceproject;
public class DriverGame {
    private String name;
    private int imageResourceId;
    //za build team

    public DriverGame(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
