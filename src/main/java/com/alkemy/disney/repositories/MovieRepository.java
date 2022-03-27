package com.alkemy.disney.repositories;

import java.util.List;

import com.alkemy.disney.entities.MovieEntity;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<MovieEntity, Long> {

    MovieEntity findByMovieId(String movieId);
    List<MovieEntity> findByGenreId(String genreId);
    List<MovieEntity> findByNameIgnoreCaseContaining(String name);
    List<MovieEntity> findAllOrderByReleaseDateDesc();
    List<MovieEntity> findAllOrderByReleaseDateAsc();
}
    