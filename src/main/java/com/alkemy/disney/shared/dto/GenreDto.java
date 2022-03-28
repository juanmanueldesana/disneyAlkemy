package com.alkemy.disney.shared.dto;

import java.util.List;

import com.alkemy.disney.entities.MovieEntity;
import com.alkemy.disney.entities.PhotoEntity;

public class GenreDto {
    
    private String genreId;
    private String name;
    private PhotoEntity photoEntity;
    private List<MovieEntity> movies;

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

    public List<MovieEntity> getMovies() {
        return this.movies;
    }

    public void setMovies(List<MovieEntity> movies) {
        this.movies = movies;
    }


}
