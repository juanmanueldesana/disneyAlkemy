package com.alkemy.disney.services;

import com.alkemy.disney.entities.PhotoEntity;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoServiceInterface {

    public PhotoEntity savePhoto(MultipartFile file);
    public void deleteOldPhoto(String oldPhotoId);

    
}
