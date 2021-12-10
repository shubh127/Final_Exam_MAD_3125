package com.example.finalexam;

import java.util.List;

public class CountryInfo {
    private String name;
    private String capitalName;
    private List<Place> places;
    private int flagImageId;

    public CountryInfo(String name, String capitalName, List<Place> places, int flagImageId) {
        this.name = name;
        this.capitalName = capitalName;
        this.places = places;
        this.flagImageId = flagImageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapitalName() {
        return capitalName;
    }

    public void setCapitalName(String capitalName) {
        this.capitalName = capitalName;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public int getFlagImageId() {
        return flagImageId;
    }

    public void setFlagImageId(int flagImageId) {
        this.flagImageId = flagImageId;
    }
}
