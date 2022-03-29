package com.alkemy.disney.services;

import java.util.List;

import com.alkemy.disney.shared.dto.GenreDto;

import org.springframework.web.multipart.MultipartFile;

public interface GenreServiceInterface {
    
    public GenreDto createGenre(GenreDto genre, MultipartFile file);
    public GenreDto getGenre(String genreId);
    public List<GenreDto> getAllGenres();
    public GenreDto updateGenre(GenreDto genre, MultipartFile file, String genreId);
    public void deleteGenre(String genreIdid);

}
