package com.recipeassignment.logic;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.recipeassignment.boundaries.ComplexSearchBoundary;
import com.recipeassignment.boundaries.RecipeBoundary;
import com.recipeassignment.boundaries.RecipeDetailsBoundary;
import com.recipeassignment.data.RecipeDao;
import com.recipeassignment.data.RecipeEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class RecipeLogicImplementation implements RecipeLogic {
    private RestTemplate restTemplate;
    private RecipeDao recipeDao;
    
    @Value("${api.key}")
    private String apiKey;  //TODO: READ API KEY FROM SYS ENVIRONMENT!!

    private String baseUrl = "https://api.spoonacular.com/recipes/";
    
    @Autowired
    public void setRecipeDao(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

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

    @Transactional(readOnly=true)
    public void markRecipeAsFavorite(int recipeId) {
        String id = UUID.randomUUID().toString();

        RecipeEntity entity = new RecipeEntity();

        entity.setId(id);
        entity.setRecipeId(recipeId);
        entity.setFavorite(true);

        this.recipeDao.save(entity);
    }

    @Transactional(readOnly=true)
    public void unmarkRecipeAsFavorite(int recipeId) {
        RecipeEntity entity = this.recipeDao.findByRecipeId(recipeId);

        entity.setFavorite(false);
        this.recipeDao.save(entity);
    }

    @Transactional
    public List<RecipeDetailsBoundary> getAllFavoriteRecipes(int page, int size) {
        List<RecipeEntity> entities = this.recipeDao.findAllByFavorite(true, PageRequest.of(page, size));
        List<RecipeDetailsBoundary> rv = new ArrayList<>();
        
        for (RecipeEntity entity : entities)
            rv.add(this.getRecipeDetailsById(entity.getRecipeId()));

        return rv;
    }

    public void deleteAllFavorites() {
        this.recipeDao.deleteAll();
    }

}
