package com.alkemy.disney.services;

import java.util.List;

import com.alkemy.disney.shared.dto.MovieDto;

import org.springframework.web.multipart.MultipartFile;

public interface MovieServiceInterface {
    
    public MovieDto createMovie(MovieDto movie, MultipartFile file);
    public MovieDto getMovie(String idMovie);
    public List<MovieDto> getAllMovies(String order);
    public List<MovieDto> getMoviesByName(String name);
    public List<MovieDto> getMoviesByGenre(String genreId);
    public MovieDto updateMovie(String idMovie ,MovieDto movie, MultipartFile file);
    public void deleteMovie(String idMovie);
}
