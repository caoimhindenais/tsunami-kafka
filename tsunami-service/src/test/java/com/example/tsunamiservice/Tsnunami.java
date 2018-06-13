package com.example.tsunamiservice;

import java.time.LocalDate;

public class Tsnunami {


    String localDate;
    String country;
    Double height;

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Tsnunami( String country, Double height,String localDate) {
        this.localDate = localDate;
        this.country = country;
        this.height = height;
    }
}
