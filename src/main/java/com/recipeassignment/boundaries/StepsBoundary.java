package com.recipeassignment.boundaries;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StepsBoundary {
    private Integer number;
    private String step;
    private List<IngredientsBoundary> ingredients;
    private List<EquipmentBoundary> equipment;
    
    public StepsBoundary() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public List<IngredientsBoundary> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientsBoundary> ingredients) {
        this.ingredients = ingredients;
    }

    public List<EquipmentBoundary> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<EquipmentBoundary> equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return "StepsBoundary [equipment=" + equipment + ", ingredients=" + ingredients +
                ", number=" + number + ", step=" + step + "]";
    }
}
