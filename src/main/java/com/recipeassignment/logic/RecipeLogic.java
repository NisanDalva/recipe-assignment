package com.recipeassignment.logic;

import java.util.List;

import com.recipeassignment.boundaries.RecipeBoundary;

public interface RecipeLogic {
    public List<RecipeBoundary> getListOfRecipes(int offset, int number);

    public List<RecipeBoundary> searchByQuery(String query, int offset, int number);
}
