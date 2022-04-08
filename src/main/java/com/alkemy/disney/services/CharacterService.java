package com.alkemy.disney.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.alkemy.disney.entities.CharacterEntity;
import com.alkemy.disney.entities.PhotoEntity;
import com.alkemy.disney.repositories.CharacterRepository;
import com.alkemy.disney.shared.dto.CharacterDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CharacterService implements CharacterServiceInterface {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    PhotoServiceInterface photoService;

    @Autowired
    ModelMapper mapper;

    @Override
    public CharacterDto createCharacter(CharacterDto character, MultipartFile file) {
        
        CharacterEntity characterEntity = new CharacterEntity();

        characterEntity.setName(character.getName());
        characterEntity.setAge(character.getAge());
        characterEntity.setWeight(character.getWeight());
        characterEntity.setHistory(character.getHistory());
        characterEntity.setCharacterId(UUID.randomUUID().toString());
        PhotoEntity photo = photoService.savePhoto(file);
        characterEntity.setPhoto(photo);
        characterRepository.save(characterEntity);

        CharacterDto characterDto = mapper.map(characterEntity, CharacterDto.class);
        return characterDto;
    }

    @Override
    public CharacterDto getCharacter(String idCharacter) {
        
        CharacterEntity characterEntity = characterRepository.findByCharacterId(idCharacter);
        CharacterDto characterDto = mapper.map(characterEntity, CharacterDto.class);
        return characterDto;
    }

    @Override
    public CharacterDto updateCharacter(String idCharacter, CharacterDto character, MultipartFile file) {

        CharacterEntity characterEntity = characterRepository.findByCharacterId(idCharacter);
        characterEntity.setName(character.getName());
        characterEntity.setAge(character.getAge());
        characterEntity.setWeight(character.getWeight());
        characterEntity.setHistory(character.getHistory());
        String oldPhotoId = characterEntity.getPhoto().getPhotoId();
        PhotoEntity photo = photoService.savePhoto(file);
        characterEntity.setPhoto(photo);
        characterRepository.save(characterEntity);
        photoService.deleteOldPhoto(oldPhotoId);

        CharacterDto characterDto = mapper.map(characterEntity, CharacterDto.class);

        return characterDto;
    }

    @Override
    public void deleteCharacter(String idCharacter) {
        
        CharacterEntity characterEntity = characterRepository.findByCharacterId(idCharacter);
        characterRepository.delete(characterEntity);
        
    }

    @Override
    public List<CharacterDto> getAllCharacters(String name, Integer age, Double weight) {
       
        List<CharacterEntity> characterEntities = (List<CharacterEntity>) characterRepository.findAll(new Specification<CharacterEntity>() {
            @Override
            public Predicate toPredicate(Root<CharacterEntity> root, CriteriaQuery<?> cq,
                    CriteriaBuilder cb) {
                
                Predicate p = cb.conjunction();

                if (name != null) {
                    p = cb.and(p, cb.like(root.get("name"), "%" + name + "%"));
                }
                if (age != null) {
                    p = cb.and(p, cb.equal(root.get("age"), age));
                }
                if (weight != null) {
                    p = cb.and(p, cb.equal(root.get("weight"), weight));
                }
                return p;
            }
        });

        List<CharacterDto> characterDtos = new ArrayList<>();

        for (CharacterEntity characterEntity : characterEntities) {
            CharacterDto characterDto = mapper.map(characterEntity, CharacterDto.class);
            characterDtos.add(characterDto);
        }

        return characterDtos;
    }


}
