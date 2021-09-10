package com.recipeassignment.controller;

import java.util.List;

import com.recipeassignment.boundaries.RecipeBoundary;
import com.recipeassignment.logic.RecipeLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    
    private RecipeLogic recipeLogic;

    @Autowired
    public void setRecipeLogic(RecipeLogic recipeLogic) {
        this.recipeLogic = recipeLogic;
    }

    @RequestMapping(
			path = "/search", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecipeBoundary> showRecipes(
        @RequestParam(name = "q", required = false, defaultValue = "") String query, //empty query means showing all recipes
        @RequestParam(name = "cuisine", required = false, defaultValue = "") String cuisine,
        @RequestParam(name = "diet", required = false, defaultValue = "") String diet,
        @RequestParam(name = "type", required = false, defaultValue = "") String type,
        @RequestParam(name = "offset", required = false, defaultValue = "0") int offset,
        @RequestParam(name = "number", required = false, defaultValue = "5") int number
    ) {
        return this.recipeLogic.getListOfRecipes(query, cuisine, diet, type, offset, number);
    }

    @RequestMapping(
        path="/{id}",
        method=RequestMethod.GET,
        produces=MediaType.APPLICATION_JSON_VALUE
    )
    public RecipeBoundary showRecipeDetailsById(@PathVariable("id") int id) {
        return this.recipeLogic.getRecipeDetailsById(id);
    }

    @RequestMapping(
        path="/addToFavorites",
        method=RequestMethod.POST,
        consumes=MediaType.APPLICATION_JSON_VALUE
    )
    public void setRecipeAsFavorites(@RequestBody RecipeBoundary recipe) {
        this.recipeLogic.markRecipeAsFavorite(recipe);
    }

    @RequestMapping(
        path="/removeFromFavorites",
        method=RequestMethod.PUT,
        consumes=MediaType.APPLICATION_JSON_VALUE
    )
    public void removeRecipeFromFavorite(@RequestBody RecipeBoundary recipe) {
        this.recipeLogic.unmarkRecipeAsFavorite(recipe);
    }

    @RequestMapping(
        path="/allFavorites",
        method=RequestMethod.GET,
        produces=MediaType.APPLICATION_JSON_VALUE
    )
    public List<RecipeBoundary> getAllFavorites(
		@RequestParam(name = "page", required = false, defaultValue = "0")  int page,
        @RequestParam(name = "size", required = false, defaultValue = "5") int size
    ) {
        return this.recipeLogic.getAllFavoriteRecipes(page, size);
    }

    @RequestMapping(
        path="/allFavorites",
        method=RequestMethod.DELETE
    )
    public void deleteAllFavorites() {
        this.recipeLogic.deleteAllFavorites();
    }
}
