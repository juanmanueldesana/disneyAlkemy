package com.alkemy.disney.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import com.alkemy.disney.entities.CharacterEntity;
import com.alkemy.disney.entities.PhotoEntity;
import com.alkemy.disney.repositories.CharacterRepository;
import com.alkemy.disney.repositories.GenreRepository;
import com.alkemy.disney.repositories.MovieRepository;
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

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieRepository movieRepository;

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

    public PhotoEntity updatePhoto(String id, MultipartFile multipartfile) {

        try {
            CharacterEntity characterEntity = characterRepository.findByCharacterId(id);
            PhotoEntity photoEntity = new PhotoEntity();

            photoEntity = savePhoto(multipartfile);
            deletePhoto(characterEntity);
            return photoEntity;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deletePhoto(CharacterEntity characterEntity) {

            PhotoEntity photoEntity = characterEntity.getPhoto();
            Path rootPath = Paths.get("web-files").resolve(photoEntity.getFileName()).toAbsolutePath();
            File file = rootPath.toFile();
            if (file.exists() && file.canRead()) {
                file.delete();
            }

            photoRepository.delete(photoEntity);
    }
    
}
