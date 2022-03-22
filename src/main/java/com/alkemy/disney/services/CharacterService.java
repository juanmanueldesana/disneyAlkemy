package com.alkemy.disney.services;

import java.util.UUID;

import com.alkemy.disney.entities.CharacterEntity;
import com.alkemy.disney.entities.PhotoEntity;
import com.alkemy.disney.repositories.CharacterRepository;
import com.alkemy.disney.shared.dto.CharacterDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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


    
}
