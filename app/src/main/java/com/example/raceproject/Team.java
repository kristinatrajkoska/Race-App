package com.example.raceproject;

import java.util.List;
//za Drivers
public class Team {
    private String name;
    private List<String> drivers;

    public Team(String name, List<String> drivers) {
        this.name = name;
        this.drivers = drivers;
    }

    public String getName() {
        return name;
    }

    public List<String> getDrivers() {
        return drivers;
    }
}
