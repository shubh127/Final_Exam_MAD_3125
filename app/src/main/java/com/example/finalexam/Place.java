package com.example.finalexam;

public class Place {
    private String name;
    private int priceOfVisit;
    private int imageId;
    private boolean isSelected;

    public Place(String name, int priceOfVisit, int imageId) {
        this.name = name;
        this.priceOfVisit = priceOfVisit;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriceOfVisit() {
        return priceOfVisit;
    }

    public void setPriceOfVisit(int priceOfVisit) {
        this.priceOfVisit = priceOfVisit;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
