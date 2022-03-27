package com.alkemy.disney.services;

import java.util.ArrayList;
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
        
        MovieEntity movieEntity = movieRepository.findByMovieId(idMovie);
        MovieDto movieDto = mapper.map(movieEntity, MovieDto.class);
        return movieDto;

    }

    @Override
    public List<MovieDto> getMoviesByName(String name) {
        
        List<MovieEntity> movieEntityList = movieRepository.findByNameIgnoreCaseContaining(name);
        List<MovieDto> movieDtoList = new ArrayList<>();

        for(MovieEntity movieEntity : movieEntityList){
            MovieDto movieDto = mapper.map(movieEntity, MovieDto.class);
            movieDtoList.add(movieDto);
        }

        return movieDtoList;
    }


    @Override
    public List<MovieDto> getMoviesByGenre(String genreId) {
        
        List<MovieEntity> movieEntityList = movieRepository.findByGenreId(genreId);
        List<MovieDto> movieDtoList = new ArrayList<>();

        for(MovieEntity movieEntity : movieEntityList){
            MovieDto movieDto = mapper.map(movieEntity, MovieDto.class);
            movieDtoList.add(movieDto);
        }

        return movieDtoList;
    }

    @Override
    public MovieDto updateMovie(String idMovie, MovieDto movie, MultipartFile file) {
        
        MovieEntity movieEntity = movieRepository.findByMovieId(idMovie);
        movieEntity.setTitle(movie.getTitle());
        movieEntity.setReleaseDate(movie.getReleaseDate());
        movieEntity.setClassification(movie.getClassification());
        String oldPhotoId = movieEntity.getPhoto().getPhotoId();
        PhotoEntity photo = photoService.savePhoto(file);
        movieEntity.setPhoto(photo);
        movieRepository.save(movieEntity);
        photoService.deleteOldPhoto(oldPhotoId);
        
        MovieDto movieDto = mapper.map(movieEntity, MovieDto.class);

        return movieDto;
    }

    @Override
    public void deleteMovie(String idMovie) {
        
        MovieEntity movieEntity = movieRepository.findByMovieId(idMovie);
        String photoId = movieEntity.getPhoto().getPhotoId();
        movieRepository.delete(movieEntity);
        photoService.deleteOldPhoto(photoId);
        
    }

    @Override
    public List<MovieDto> getAllMovies(String order) {
        
        List<MovieEntity> movieEntityList = new ArrayList<>();
        if(order.equalsIgnoreCase("asc")){
            movieEntityList = movieRepository.findAllOrderByReleaseDateAsc();
        }else{
            movieEntityList = movieRepository.findAllOrderByReleaseDateDesc();
        }

        List<MovieDto> movieDtoList = new ArrayList<>();
        
        for(MovieEntity movieEntity : movieEntityList){
            MovieDto movieDto = mapper.map(movieEntity, MovieDto.class);
            movieDtoList.add(movieDto);
        }

        return movieDtoList;
    }
    
}
