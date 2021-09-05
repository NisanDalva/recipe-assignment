package com.recipeassignment.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface RecipeDao extends PagingAndSortingRepository<RecipeEntity, String> {
    public RecipeEntity findByRecipeId(@Param("RecipeId") int recipeId);
    public List<RecipeEntity> findAllByFavorite(@Param("favorite") boolean favorite, Pageable pageable);
}
