package com.alkemy.disney.repositories;

import java.util.List;

import com.alkemy.disney.entities.CharacterEntity;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends PagingAndSortingRepository<CharacterEntity, Long> {

    CharacterEntity findByCharacterId(String characterId);
    List<CharacterEntity> findByNameIgnoreCaseContaining(String name);
    List<CharacterEntity> findByAge(Integer age);
    List<CharacterEntity> findByWeight(Double weight);

}
    
