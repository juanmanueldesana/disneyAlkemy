package com.alkemy.disney.repositories;

import java.util.List;

import com.alkemy.disney.entities.CharacterEntity;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends PagingAndSortingRepository<CharacterEntity, Long>, JpaSpecificationExecutor<CharacterEntity> {

    CharacterEntity findByCharacterId(String characterId);
    List<CharacterEntity> findAll(String name, Integer age, Double weight);

}
    
