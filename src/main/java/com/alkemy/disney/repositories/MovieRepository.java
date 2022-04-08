package com.alkemy.disney.repositories;

import com.alkemy.disney.entities.MovieEntity;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends PagingAndSortingRepository<MovieEntity, Long>, JpaSpecificationExecutor<MovieEntity> {

    MovieEntity findByMovieId(String movieId);
}
    