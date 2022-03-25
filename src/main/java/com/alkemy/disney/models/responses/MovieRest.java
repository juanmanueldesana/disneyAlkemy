package com.alkemy.disney.models.responses;

import java.util.List;

import com.alkemy.disney.entities.PhotoEntity;

public class MovieRest {
    
    private String movieId;
    private String title;
    
    private String releaseDate;
    
    private Integer classification;
    
    private List<CharacterRest> characters;
    private PhotoEntity photo;
    
    
    public String getMovieId() {
        return this.movieId;
    }
    
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getReleaseDate() {
        return this.releaseDate;
    }
    
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    public Integer getClassification() {
        return this.classification;
    }
    
    public void setClassification(Integer classification) {
        this.classification = classification;
    }
    
    public List<CharacterRest> getCharacters() {
        return this.characters;
    }
    
    public void setCharacters(List<CharacterRest> characters) {
        this.characters = characters;
    }
    
    public PhotoEntity getPhoto() {
        return this.photo;
    }
    
    public void setPhoto(PhotoEntity photo) {
        this.photo = photo;
    }

}
