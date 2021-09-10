package com.recipeassignment.boundaries;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplexSearchBoundary {
    private Integer offset;
    private Integer number;
    private Integer totalResults;
    private List<RecipeBoundary> results;

    public ComplexSearchBoundary() {
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getTotalResults() {
        return this.totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<RecipeBoundary> getResults() {
        return this.results;
    }

    public void setResults(List<RecipeBoundary> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ComplexSearchBoundary [number=" + number + ", offset=" + offset + ", results=" + results
                + ", totalResults=" + totalResults + "]";
    }
}
