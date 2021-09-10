package com.recipeassignment.boundaries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EquipmentBoundary {
    private Integer id;
    private String name;
    private String localizedName;
    private String image;
    
    public EquipmentBoundary() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "EquipmentBoundary [id=" + id + ", image=" + image + ", localizedName=" + localizedName + ", name="
                + name + "]";
    }
}
