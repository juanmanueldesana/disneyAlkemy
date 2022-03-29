package com.alkemy.disney.repositories;

import java.util.List;

import com.alkemy.disney.entities.MovieEntity;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<MovieEntity, Long> {

    MovieEntity findByMovieId(String movieId);
    List<MovieEntity> findByGenres(String genreId);
    List<MovieEntity> findByTitleIgnoreCaseContaining(String title);
    List<MovieEntity> findAllByOrderByReleaseDateDesc();
    List<MovieEntity> findAllByOrderByReleaseDateAsc();
}
    