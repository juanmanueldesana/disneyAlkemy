package com.alkemy.disney.shared.dto;

import java.time.LocalDate;
import java.util.List;

import com.alkemy.disney.entities.PhotoEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

public class MovieDto {
    
    private String movieId;
    private String title;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;

    private Integer classification;

    private List<CharacterDto> characters;
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

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getClassification() {
        return this.classification;
    }

    public void setClassification(Integer classification) {
        this.classification = classification;
    }

    public List<CharacterDto> getCharacters() {
        return this.characters;
    }

    public void setCharacters(List<CharacterDto> characters) {
        this.characters = characters;
    }

    public PhotoEntity getPhoto() {
        return this.photo;
    }

    public void setPhoto(PhotoEntity photo) {
        this.photo = photo;
    }


}
