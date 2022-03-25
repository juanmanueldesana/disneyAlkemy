package com.alkemy.disney.repositories;

import com.alkemy.disney.entities.MovieEntity;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<MovieEntity, Long> {

    MovieEntity findByMovieId(String movieId);

}
    