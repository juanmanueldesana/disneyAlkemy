package com.alkemy.disney.services;

import java.util.List;

import com.alkemy.disney.shared.dto.MovieDto;

import org.springframework.web.multipart.MultipartFile;

public interface MovieServiceInterface {
    
    public MovieDto createMovie(MovieDto movie, MultipartFile file);
    public MovieDto getMovie(String idMovie);
    public List<MovieDto> getAllMovies(String order, String name, String genreId);
    public MovieDto updateMovie(String idMovie ,MovieDto movie, MultipartFile file);
    public MovieDto addCharacterToMovie(String idMovie, String idCharacter);
    public void deleteMovie(String idMovie);
    public MovieDto deleteCharacterFromMovie(String idMovie, String idCharacter);
}
