package com.example.tsunamiui;

public class Wave {

    String year;
    Double height;

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

    public Wave(String year, Double height) {
        this.year = year;
        this.height = height;
    }
}


