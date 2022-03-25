package com.alkemy.disney.models.requests;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;


public class MovieCreateRequestModel {
    
    @NotEmpty(message = "Title is required")
    private String title;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Release date is required")
    private LocalDate releaseDate;

    @NotNull(message = "Classification is required")
    private Integer classification;

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

}
