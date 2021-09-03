package com.recipeassignment.boundaries;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NutritionBoundary {
    private List<Object> nutrients;

    public NutritionBoundary() {
    }

    public NutritionBoundary(List<Object> nutrients) {
        this.nutrients = nutrients;
    }


    public List<Object> getNutrients() {
        return this.nutrients;
    }

    public void setNutrients(List<Object> nutrients) {
        this.nutrients = nutrients;
    }

    @Override
    public String toString() {
        return "NutritionBoundary [nutrients=" + nutrients + "]";
    }
    
}
