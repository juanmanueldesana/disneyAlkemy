package com.alkemy.disney.models.requests;

import javax.validation.constraints.NotEmpty;

public class GenreCreateRequestModel {
    
    @NotEmpty(message = "Genre name is required")
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
