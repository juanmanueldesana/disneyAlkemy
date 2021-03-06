package com.alkemy.disney.repositories;


import com.alkemy.disney.entities.CharacterEntity;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends PagingAndSortingRepository<CharacterEntity, Long>, JpaSpecificationExecutor<CharacterEntity> {

    CharacterEntity findByCharacterId(String characterId);
}
    
