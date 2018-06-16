package com.example.tsunamiui;

public class Wave {

    String year;
    Double height;
    String country;



    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Wave(String country,String year, Double height) {
        this.year = year;
        this.height = height;
        this.country = country;
    }
}


