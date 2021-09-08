package com.recipeassignment.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface RecipeDao extends PagingAndSortingRepository<RecipeEntity, String> {
    public Optional<RecipeEntity> findByRecipeId(@Param("RecipeId") int recipeId);
    public List<RecipeEntity> findAllByFavorite(@Param("favorite") boolean favorite, Pageable pageable);
}
