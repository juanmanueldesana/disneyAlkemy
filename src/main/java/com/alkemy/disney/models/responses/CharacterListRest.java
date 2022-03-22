package com.alkemy.disney.models.responses;

import com.alkemy.disney.entities.PhotoEntity;

public class CharacterListRest {
    
    private String name;
    private PhotoEntity photo;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PhotoEntity getPhoto() {
        return this.photo;
    }

    public void setPhoto(PhotoEntity photo) {
        this.photo = photo;
    }
    
}
