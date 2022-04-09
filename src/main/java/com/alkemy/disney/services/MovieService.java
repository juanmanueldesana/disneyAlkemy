package com.alkemy.disney.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
import org.springframework.data.jpa.domain.Specification;
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
    public MovieDto updateMovie(String idMovie, MovieDto movie, MultipartFile file) {
        
        MovieEntity movieEntity = movieRepository.findByMovieId(idMovie);
        movieEntity.setTitle(movie.getTitle());
        movieEntity.setReleaseDate(movie.getReleaseDate());
        movieEntity.setClassification(movie.getClassification());
        String oldPhotoId = movieEntity.getPhoto().getPhotoId();
        PhotoEntity photo = photoService.savePhoto(file);
        movieEntity.setPhoto(photo);
        movieRepository.save(movieEntity);
        photoService.deleteOldPhotoFromUpdate(oldPhotoId);
        
        MovieDto movieDto = mapper.map(movieEntity, MovieDto.class);
        movieDto.setGenre(movieEntity.getGenres().get(0));

        avoidRecursiveCharacterMovies(movieDto);
        return movieDto;
    }

    @Override
    public void deleteMovie(String idMovie) {
        
        MovieEntity movieEntity = movieRepository.findByMovieId(idMovie);
        String oldPhotoId = movieEntity.getPhoto().getFileName();
        movieRepository.delete(movieEntity);
        photoService.deleteOldPhotoFromFiles(oldPhotoId);
    }

    @Override
    public List<MovieDto> getAllMovies(String order, String name, String genreId) {
        
        List<MovieEntity> movieEntityList = (List<MovieEntity>) movieRepository.findAll(new Specification<MovieEntity>() {
            @Override
            public Predicate toPredicate(Root<MovieEntity> root, CriteriaQuery<?> cq,
                    CriteriaBuilder cb) {
                
                Predicate p = cb.conjunction();

                if(order != null && !order.isEmpty()){
                    if(order.equals("asc")){
                        cq.orderBy(cb.asc(root.get("releaseDate")));
                    }else{
                        cq.orderBy(cb.desc(root.get("releaseDate")));
                    }
                }

                if(name != null && !name.isEmpty()){
                    p = cb.and(p, cb.like(root.get("title"), "%" + name + "%"));
                }

                if(genreId != null && !genreId.isEmpty()){
                    p = cb.and(p, cb.equal(root.join("genres").get("genreId"), genreId));
                }

                return p;
            }
        });
        List<MovieDto> movieDtoList = new ArrayList<>();
        
        for(MovieEntity movieEntity : movieEntityList){
            MovieDto movieDto = mapper.map(movieEntity, MovieDto.class);
            movieDto.setGenre(movieEntity.getGenres().get(0));
            movieDtoList.add(movieDto);
        }

        return movieDtoList;
    }

    private void avoidRecursiveCharacterMovies(MovieDto movieDto){

        if(movieDto.getCharacters() != null){
            for(int i = 0; i < movieDto.getCharacters().size(); i++){
                movieDto.getCharacters().get(i).setMovies(null);
            }
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
