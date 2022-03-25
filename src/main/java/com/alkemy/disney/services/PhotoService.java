package com.alkemy.disney.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import com.alkemy.disney.entities.PhotoEntity;
import com.alkemy.disney.repositories.PhotoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotoService implements PhotoServiceInterface{

    @Autowired
    private PhotoRepository photoRepository;

    public PhotoEntity savePhoto(MultipartFile file) {

        try {
            PhotoEntity photoEntity = new PhotoEntity();
            if (file != null) {
                    String uuidName = UUID.randomUUID().toString() + "." + file.getOriginalFilename();

                    Path rootPath = Paths.get("web-files").resolve(uuidName);
                    Path rootAbsolutePath = rootPath.toAbsolutePath();
                    Resource resource = new UrlResource(rootAbsolutePath.toUri());

                    Files.copy(file.getInputStream(), rootAbsolutePath);

                    photoEntity.setFileName(uuidName);
                    photoEntity.setUri(resource.getURI().toString());
                
                return photoRepository.save(photoEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteOldPhoto(String oldPhotoId) {

            PhotoEntity photoEntity = photoRepository.findById(oldPhotoId).get();
            Path rootPath = Paths.get("web-files").resolve(photoEntity.getFileName()).toAbsolutePath();
            File file = rootPath.toFile();
            if (file.exists() && file.canRead()) {
                file.delete();
            }

            photoRepository.delete(photoEntity);
    }

}
