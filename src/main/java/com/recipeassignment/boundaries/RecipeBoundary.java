package com.recipeassignment.boundaries;


import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeBoundary {
    private Integer id;
    private String title;
    private String image;
    private String imageType;
    // private NutritionBoundary nutrition;

    private Map<String, Object> moreDetailes;


    public RecipeBoundary() {
    }

    public RecipeBoundary(Integer id, String title, String image, String imageType, Map<String, Object> moreDetailes) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.imageType = imageType;
        this.moreDetailes = moreDetailes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public Map<String, Object> getNutrition() {
        return moreDetailes;
    }

    public void setNutrition(Map<String, Object> moreDetailes) {
        this.moreDetailes = moreDetailes;
    }

    @Override
    public String toString() {
        return "RecipeBoundary [id=" + id + ", image=" + image + ", imageType=" + imageType + ", nutrition=" + moreDetailes
                + ", title=" + title + "]";
    }
    
}
