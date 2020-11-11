package com.example.cyprusreservations;

import java.io.Serializable;

public class StoreInfo implements Serializable {

    private String title,description;
    private float rating;
    private int logo;
    private int phone;
    private String address;

    public StoreInfo(String title, String description, int logo, float rating, int phone, String address) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.logo = logo;
        this.phone = phone;
        this.address = address;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
