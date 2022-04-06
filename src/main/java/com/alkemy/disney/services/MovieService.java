package com.alkemy.disney.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alkemy.disney.entities.CharacterEntity;
import com.alkemy.disney.entities.GenreEntity;
import com.alkemy.disney.entities.MovieEntity;
import com.alkemy.disney.entities.PhotoEntity;
import com.alkemy.disney.repositories.CharacterRepository;
import com.alkemy.disney.repositories.GenreRepository;
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
    private CharacterRepository characterRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ModelMapper mapper;

    
    @Override
    public MovieDto createMovie(MovieDto movie, MultipartFile file) {
        
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(movie.getTitle());
        movieEntity.setReleaseDate(movie.getReleaseDate());
        movieEntity.setClassification(movie.getClassification());
        movieEntity.setMovieId(UUID.randomUUID().toString());
        List<GenreEntity> genreId = new ArrayList<>();
        GenreEntity genre = genreRepository.findByGenreId(movie.getGenres().getGenreId());
        genreId.add(genre);
        movieEntity.setGenres(genreId);

        PhotoEntity photo = photoService.savePhoto(file);
        movieEntity.setPhoto(photo);
        movieRepository.save(movieEntity);

        MovieDto movieDto = mapper.map(movieEntity, MovieDto.class);
        movieDto.setGenre(movieEntity.getGenres().get(0));
        avoidRecursiveCharacterMovies(movieDto);
        return movieDto;
    }

    @Override
    public MovieDto getMovie(String idMovie) {
        
        MovieEntity movieEntity = movieRepository.findByMovieId(idMovie);
        MovieDto movieDto = mapper.map(movieEntity, MovieDto.class);
        movieDto.setGenre(movieEntity.getGenres().get(0));

        for(int i = 0; i < movieDto.getCharacters().size(); i++){
            movieDto.getCharacters().get(i).setMovies(null);
        }
        avoidRecursiveCharacterMovies(movieDto);
        return movieDto;

    }

    @Override
    public List<MovieDto> getMoviesByName(String title) {
        
        List<MovieEntity> movieEntityList = movieRepository.findByTitleIgnoreCaseContaining(title);
        List<MovieDto> movieDtoList = new ArrayList<>();

        for(MovieEntity movieEntity : movieEntityList){
            MovieDto movieDto = mapper.map(movieEntity, MovieDto.class);
            movieDto.setGenre(movieEntity.getGenres().get(0));
            movieDtoList.add(movieDto);
        }

        return movieDtoList;
    }


    @Override
    public List<MovieDto> getMoviesByGenre(String genreId) {
        
        List<MovieEntity> movieEntityList = movieRepository.findByGenres(genreId);
        List<MovieDto> movieDtoList = new ArrayList<>();

        for(MovieEntity movieEntity : movieEntityList){
            MovieDto movieDto = mapper.map(movieEntity, MovieDto.class);
            movieDto.setGenre(movieEntity.getGenres().get(0));
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
        movieDto.setGenre(movieEntity.getGenres().get(0));

        avoidRecursiveCharacterMovies(movieDto);
        return movieDto;
    }

    @Override
    public void deleteMovie(String idMovie) {
        
        MovieEntity movieEntity = movieRepository.findByMovieId(idMovie);
        movieRepository.delete(movieEntity);
        
    }

    @Override
    public List<MovieDto> getAllMovies(String order) {
        
        List<MovieEntity> movieEntityList = new ArrayList<>();
        if(order.equalsIgnoreCase("asc")){
            movieEntityList = movieRepository.findAllByOrderByReleaseDateAsc();
        }else{
            movieEntityList = movieRepository.findAllByOrderByReleaseDateDesc();
        }

        List<MovieDto> movieDtoList = new ArrayList<>();
        
        for(MovieEntity movieEntity : movieEntityList){
            MovieDto movieDto = mapper.map(movieEntity, MovieDto.class);
            movieDto.setGenre(movieEntity.getGenres().get(0));
            movieDtoList.add(movieDto);
        }

        return movieDtoList;
    }

    private void avoidRecursiveCharacterMovies(MovieDto movieDto){
        for(int i = 0; i < movieDto.getCharacters().size(); i++){
            movieDto.getCharacters().get(i).setMovies(null);
        }
    }

    @Override
    public MovieDto addCharacterToMovie(String idMovie, String idCharacter) {
        
        MovieEntity movieEntity = movieRepository.findByMovieId(idMovie);
        CharacterEntity characterEntity = characterRepository.findByCharacterId(idCharacter);
        if(characterEntity != null){
            movieEntity.getCharacters().add(characterEntity);
            movieRepository.save(movieEntity);
            MovieDto movieDto = mapper.map(movieEntity, MovieDto.class);
            movieDto.setGenre(movieEntity.getGenres().get(0));
            avoidRecursiveCharacterMovies(movieDto);
            return movieDto;
        }

        return null;
    }

    @Override
    public MovieDto deleteCharacterFromMovie(String idMovie, String idCharacter) {
        
        MovieEntity movieEntity = movieRepository.findByMovieId(idMovie);
        CharacterEntity characterEntity = characterRepository.findByCharacterId(idCharacter);
        if(characterEntity != null){
            movieEntity.getCharacters().remove(characterEntity);
            movieRepository.save(movieEntity);
            MovieDto movieDto = mapper.map(movieEntity, MovieDto.class);
            movieDto.setGenre(movieEntity.getGenres().get(0));
            avoidRecursiveCharacterMovies(movieDto);
            return movieDto;
        }
        return null;
    }
    
}
