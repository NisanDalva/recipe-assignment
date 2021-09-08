package com.recipeassignment.logic;

import java.util.List;

import com.recipeassignment.boundaries.RecipeBoundary;
// import com.recipeassignment.boundaries.RecipeDetailsBoundary;

public interface RecipeLogic {
    public List<RecipeBoundary> getListOfRecipes(String query, int offset, int number);

    // public List<RecipeBoundary> searchByQuery(String query, int offset, int number);

    public RecipeBoundary getRecipeDetailsById(int id);

    public void markRecipeAsFavorite(RecipeBoundary recipe);

    public void unmarkRecipeAsFavorite(RecipeBoundary recipe);

    public List<RecipeBoundary> getAllFavoriteRecipes(int page, int size);

    public void deleteAllFavorites();
}
