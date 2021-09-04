package com.recipeassignment.boundaries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LengthBoundary {
    private int number;
    private String unit;
    
    public LengthBoundary() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "LengthBoundary [number=" + number + ", unit=" + unit + "]";
    }
}
