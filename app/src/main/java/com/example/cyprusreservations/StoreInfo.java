package com.example.cyprusreservations;

import java.io.Serializable;

public class StoreInfo implements Serializable {

    private String title,description;
    private float rating;
    private int logo;

    public StoreInfo(String title, String description, int logo, float rating) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }
}
