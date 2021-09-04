package com.recipeassignment.controller;

import java.util.List;

import com.recipeassignment.boundaries.RecipeBoundary;
import com.recipeassignment.logic.RecipeLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    
    private RecipeLogic recipeLogic;

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    public void setRecipeLogic(RecipeLogic recipeLogic) {
        this.recipeLogic = recipeLogic;
    }

    @RequestMapping(
			path = "/all", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecipeBoundary> showRecipes(
        @RequestParam(name="offset", required = false, defaultValue = "0") int offset,
        @RequestParam(name="number", required = false, defaultValue = "10") int number
    ) {
        return this.recipeLogic.getListOfRecipes(offset, number);
    }

    @RequestMapping(
        path="/search/{query}",
        method=RequestMethod.GET,
        produces=MediaType.APPLICATION_JSON_VALUE
    )
    public List<RecipeBoundary> SearchRecipeByText(
        @PathVariable("query") String  query,
        @RequestParam(name="offset", required = false, defaultValue = "0") int offset,
        @RequestParam(name="number", required = false, defaultValue = "10") int number
    ) {
        return this.recipeLogic.searchByQuery(query, offset, number);
    }

    

}
