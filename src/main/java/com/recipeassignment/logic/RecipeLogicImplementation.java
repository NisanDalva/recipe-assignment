package com.recipeassignment.logic;
import java.util.List;

import com.recipeassignment.boundaries.ComplexSearchBoundary;
import com.recipeassignment.boundaries.RecipeBoundary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RecipeLogicImplementation implements RecipeLogic {
    private RestTemplate restTemplate;
    
    @Value("${api.key}")
    private String apiKey;

    private String url = "https://api.spoonacular.com/recipes/complexSearch?apiKey=";
    
    public RecipeLogicImplementation() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<RecipeBoundary> getListOfRecipes(int offset, int number) {
        
        String updatedUrl = this.url + this.apiKey + "&offset=" + offset +"&number=" + number;
        
        ComplexSearchBoundary res = restTemplate.getForObject(updatedUrl, ComplexSearchBoundary.class);

        return res.getResults();
    }

    

    
}
