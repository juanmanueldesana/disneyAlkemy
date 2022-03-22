package com.alkemy.disney.repositories;

import com.alkemy.disney.entities.CharacterEntity;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends PagingAndSortingRepository<CharacterEntity, Long> {

    CharacterEntity findByCharacterId(String characterId);

}
    
