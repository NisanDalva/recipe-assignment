package com.recipeassignment.logic;
import java.util.List;

import com.recipeassignment.boundaries.ComplexSearchBoundary;
import com.recipeassignment.boundaries.RecipeBoundary;
import com.recipeassignment.boundaries.RecipeDetailsBoundary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecipeLogicImplementation implements RecipeLogic {
    private RestTemplate restTemplate;
    
    @Value("${api.key}")
    private String apiKey;

    private String baseUrl = "https://api.spoonacular.com/recipes/";
    
    public RecipeLogicImplementation() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<RecipeBoundary> getListOfRecipes(int offset, int number) {
        String updatedUrl = this.baseUrl + "complexSearch?apiKey=" + this.apiKey + "&offset=" + offset +"&number=" + number;
        
        ComplexSearchBoundary res = restTemplate.getForObject(updatedUrl, ComplexSearchBoundary.class);

        return res.getResults();
    }

    @Override
    public List<RecipeBoundary> searchByQuery(String query, int offset, int number) {
        String updatedUrl = this.baseUrl + "complexSearch?apiKey=" + this.apiKey + "&query=" + query + "&offset=" + offset +"&number=" + number;

        ComplexSearchBoundary res = restTemplate.getForObject(updatedUrl, ComplexSearchBoundary.class);

        return res.getResults();
    }

    public RecipeDetailsBoundary getRecipeDetailsById(int id) {
        String updatedUrl = this.baseUrl + "informationBulk?apiKey=" + this.apiKey + "&ids=" + id;

        return restTemplate.getForObject(updatedUrl, RecipeDetailsBoundary[].class)[0];
    }

    
}
