package com.alkemy.disney.models.responses;

import com.alkemy.disney.entities.PhotoEntity;

public class GenreRest {
    
    private String genreId;
    private String name;
    private PhotoEntity photoEntity;

    public String getGenreId() {
        return this.genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PhotoEntity getPhotoEntity() {
        return this.photoEntity;
    }

    public void setPhotoEntity(PhotoEntity photoEntity) {
        this.photoEntity = photoEntity;
    }


}
