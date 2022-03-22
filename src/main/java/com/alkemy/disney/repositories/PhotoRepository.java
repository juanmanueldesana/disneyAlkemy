package com.alkemy.disney.repositories;

import com.alkemy.disney.entities.PhotoEntity;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends PagingAndSortingRepository<PhotoEntity, String> {

    
}
    
