package com.recipeassignment.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/*
    FAVORIES
------------------------
    ID  |   RECIPE_ID    |   FAVORIE    |
   <PK> |
*/


@Entity
@Table(name="FAVORITES")
public class RecipeEntity {
    private String id;
    private int recipeId;
    private Boolean favorite;
    
    public RecipeEntity() {
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }
}
