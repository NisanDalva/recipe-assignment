package com.recipeassignment.logic;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.recipeassignment.boundaries.ComplexSearchBoundary;
import com.recipeassignment.boundaries.RecipeBoundary;
import com.recipeassignment.data.RecipeDao;
import com.recipeassignment.data.RecipeEntity;
import com.recipeassignment.exceptions.RecipeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class RecipeLogicImplementation implements RecipeLogic {
    private RestTemplate restTemplate;
    private RecipeDao recipeDao;
    
    /*
        disable this comment to read the api key from 'application.propreties' file
        and comment the 'apiKey' in the constructor below
    */
    // @Value("${api.key}") //TODO: READ API KEY FROM SYSTEM ENVIRONMENT!!
    private String apiKey;
    private final String BASE_URL = "https://api.spoonacular.com/recipes/";
    
    @Autowired
    public void setRecipeDao(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    public RecipeLogicImplementation() {
        this.restTemplate = new RestTemplate();
        // since the api key is a private key, cannot be on the repository. read it from the system environment variables
        apiKey = System.getenv("API_KEY_SPOONACULAR");
    }

    @Override
    public List<RecipeBoundary> getListOfRecipes(String query, String cuisine, String diet, String type, int offset, int number) {
        String updatedUrl = this.BASE_URL + "complexSearch?apiKey=" + this.apiKey + 
        "&addRecipeInformation=true" + "&query=" + query + "&cuisine=" + cuisine + 
        "&diet=" + diet + "&type=" + type + "&offset=" + offset +"&number=" + number;
        
        ComplexSearchBoundary response = restTemplate.getForObject(updatedUrl, ComplexSearchBoundary.class);

        return response.getResults();
    }

    public RecipeBoundary getRecipeDetailsById(int id) {
        String updatedUrl = this.BASE_URL + "informationBulk?apiKey=" + this.apiKey + "&ids=" + id;

        return restTemplate.getForObject(updatedUrl, RecipeBoundary[].class)[0];
    }

    @Transactional
    public void markRecipeAsFavorite(RecipeBoundary recipe) {
        Optional<RecipeEntity> op = this.recipeDao.findByRecipeId(recipe.getId());

        if(op.isPresent()) { // if recipe marked before, just update the entity
            RecipeEntity entity = op.get();
            entity.setFavorite(true);
            this.recipeDao.save(entity);
        
        } else { // if is not, adds new entity to DB
            String id = UUID.randomUUID().toString();
            RecipeEntity entity = new RecipeEntity();
    
            entity.setId(id);
            entity.setRecipeId(recipe.getId());
            entity.setFavorite(true);
    
            this.recipeDao.save(entity);
        }
    }

    @Transactional
    public void unmarkRecipeAsFavorite(RecipeBoundary recipe) {
        Optional<RecipeEntity> op = this.recipeDao.findByRecipeId(recipe.getId());

        if (op.isPresent()) {
            RecipeEntity entity = op.get();
            entity.setFavorite(false);
            this.recipeDao.save(entity);
        } else
            // throws 404 NOT_FOUND instead of 500
            throw new RecipeNotFoundException("Could not find recipe to unmark as favorite by id: " + recipe.getId());
    }

    @Transactional(readOnly=true)
    public List<RecipeBoundary> getAllFavoriteRecipes(int page, int size) {
        List<RecipeEntity> entities = this.recipeDao.findAllByFavorite(true, PageRequest.of(page, size));
        List<RecipeBoundary> rv = new ArrayList<>();
        
        for (RecipeEntity entity : entities)
            rv.add(this.getRecipeDetailsById(entity.getRecipeId()));

        return rv;
    }

    @Transactional
    public void deleteAllFavorites() {
        this.recipeDao.deleteAll();
    }
}
