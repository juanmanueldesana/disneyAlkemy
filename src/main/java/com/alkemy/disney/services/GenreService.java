package com.alkemy.disney.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alkemy.disney.entities.GenreEntity;
import com.alkemy.disney.entities.PhotoEntity;
import com.alkemy.disney.repositories.GenreRepository;
import com.alkemy.disney.shared.dto.GenreDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GenreService implements GenreServiceInterface {

    @Autowired
    private PhotoServiceInterface photoService;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public GenreDto createGenre(GenreDto genre, MultipartFile file) {
        
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setGenreId(UUID.randomUUID().toString());
        genreEntity.setName(genre.getName());
        
        PhotoEntity photo = photoService.savePhoto(file);
        genreEntity.setPhoto(photo);
        genreRepository.save(genreEntity);

        GenreDto genreDto = mapper.map(genreEntity, GenreDto.class);

        return genreDto;
    }

    @Override
    public GenreDto getGenre(String genreId) {
        
        GenreEntity genreEntity = genreRepository.findByGenreId(genreId);
        GenreDto genreDto = mapper.map(genreEntity, GenreDto.class);

        return genreDto;
    }

    @Override
    public List<GenreDto> getAllGenres() {
    
        List<GenreEntity> genreEntityList = (List<GenreEntity>) genreRepository.findAll();

        List<GenreDto> genreDtoList = new ArrayList<>();

        for (GenreEntity genreEntity : genreEntityList) {
            GenreDto genreDto = mapper.map(genreEntity, GenreDto.class);
            genreDtoList.add(genreDto);
        }

        return genreDtoList;
    }

    @Override
    public GenreDto updateGenre(GenreDto genre, MultipartFile file, String genreId) {
        
        GenreEntity genreEntity = genreRepository.findByGenreId(genreId);
        genreEntity.setName(genre.getName());
        PhotoEntity photo = photoService.savePhoto(file);


        if(genreEntity.getPhoto() != null){
            String oldPhotoId = genreEntity.getPhoto().getPhotoId();
            genreEntity.setPhoto(photo);
            genreRepository.save(genreEntity);

            photoService.deleteOldPhotoFromUpdate(oldPhotoId);
        }

        genreEntity.setPhoto(photo);
        genreRepository.save(genreEntity);

        GenreDto genreDto = mapper.map(genreEntity, GenreDto.class);
        return genreDto;
    }

    @Override
    public void deleteGenre(String id) {
        
        GenreEntity genreEntity = genreRepository.findByGenreId(id);
        String oldPhotoId = genreEntity.getPhoto().getFileName();
        genreRepository.delete(genreEntity);
        photoService.deleteOldPhotoFromFiles(oldPhotoId);
    }

}
