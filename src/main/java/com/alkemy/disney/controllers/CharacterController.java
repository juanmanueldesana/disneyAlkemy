package com.alkemy.disney.controllers;

import java.util.List;

import com.alkemy.disney.services.CharacterServiceInterface;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    
    @Autowired
    private CharacterServiceInterface characterService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public CharacterRest createCharacter(@RequestBody CharacterRest character) {
        CharacterDto characterDto = mapper.map(character, CharacterDto.class);
        CharacterDto createdCharacter = characterService.createCharacter(characterDto);
        CharacterRest characterToReturn = mapper.map(createdCharacter, CharacterRest.class);
        return characterToReturn;
    }

    @GetMapping
    public List<CharacterRest> getCharacters() {

        List<CharacterDto> characterDtoList = characterService.getAll();
        List<CharacterRest> characterToReturn = new ArrayList<>();

        for (CharacterDto characterDto : characterDtoList) {
            CharacterRest characterRest = mapper.map(characterDto, CharacterRest.class);
            characterToReturn.add(characterRest);
        }

        return characterToReturn;
    }
    


}
