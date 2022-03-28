package com.alkemy.disney.services;

import java.util.List;

import com.alkemy.disney.shared.dto.GenreDto;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class GenreService implements GenreServiceInterface {

    @Override
    public GenreDto createGenre(GenreDto genre, MultipartFile file) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GenreDto getGenreById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GenreDto> getAllGenres() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GenreDto updateGenre(GenreDto genre, MultipartFile file, String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteGenre(String id) {
        // TODO Auto-generated method stub
        
    }


    
}
