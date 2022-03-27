package com.alkemy.disney.models.responses;

import java.time.LocalDate;

import com.alkemy.disney.entities.PhotoEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

public class MovieListRest {
    
    private String title;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;

    private PhotoEntity photo;

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

    public PhotoEntity getPhoto() {
        return this.photo;
    }

    public void setPhoto(PhotoEntity photo) {
        this.photo = photo;
    }


}
