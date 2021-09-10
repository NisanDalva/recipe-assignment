package com.recipeassignment.boundaries;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalyzedInstructionsBoundary {
    private String name;
    private List<StepsBoundary> steps;
    
    public AnalyzedInstructionsBoundary() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StepsBoundary> getSteps() {
        return steps;
    }

    public void setSteps(List<StepsBoundary> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "AnalyzedInstructionsBoundary [name=" + name + ", steps=" + steps + "]";
    }
}
