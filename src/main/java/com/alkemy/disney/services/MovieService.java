package com.alkemy.disney.services;

import java.util.List;
import java.util.UUID;

import com.alkemy.disney.entities.MovieEntity;
import com.alkemy.disney.entities.PhotoEntity;
import com.alkemy.disney.repositories.MovieRepository;
import com.alkemy.disney.shared.dto.MovieDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MovieService implements MovieServiceInterface{

    @Autowired
    private PhotoServiceInterface photoService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper mapper;

    
    @Override
    public MovieDto createMovie(MovieDto movie, MultipartFile file) {
        
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(movie.getTitle());
        movieEntity.setReleaseDate(movie.getReleaseDate());
        movieEntity.setClassification(movie.getClassification());
        movieEntity.setMovieId(UUID.randomUUID().toString());

        PhotoEntity photo = photoService.savePhoto(file);
        movieEntity.setPhoto(photo);
        movieRepository.save(movieEntity);

        MovieDto movieDto = mapper.map(movieEntity, MovieDto.class);
        return movieDto;
    }

    @Override
    public MovieDto getMovie(String idMovie) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MovieDto> getMoviesByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MovieDto> getMoviesByYear(Integer year) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MovieDto> getMoviesByGenre(String genre) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MovieDto updateMovie(String idMovie, MovieDto movie, MultipartFile file) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteMovie(String idMovie) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<MovieDto> getAllMovies() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
