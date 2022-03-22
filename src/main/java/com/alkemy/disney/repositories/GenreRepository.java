package com.alkemy.disney.repositories;

import com.alkemy.disney.entities.GenreEntity;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends PagingAndSortingRepository<GenreEntity, Long> {

}
