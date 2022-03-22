package com.alkemy.disney.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.alkemy.disney.models.requests.CharacterCreateRequestModel;
import com.alkemy.disney.models.responses.CharacterRest;
import com.alkemy.disney.services.CharacterServiceInterface;
import com.alkemy.disney.shared.dto.CharacterDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    
    @Autowired
    CharacterServiceInterface characterService;

    @Autowired
    ModelMapper mapper;

    @PostMapping
    public CharacterRest createCharacter(@RequestPart @Valid CharacterCreateRequestModel characterCreateRequestModel, @ModelAttribute MultipartFile file) {
        CharacterDto characterDto = mapper.map(characterCreateRequestModel, CharacterDto.class);
        CharacterDto createdCharacter = characterService.createCharacter(characterDto, file);
        CharacterRest characterToReturn = mapper.map(createdCharacter, CharacterRest.class);
        return characterToReturn;
    }

    // @GetMapping
    // public List<CharacterRest> getCharacters() {

    //     List<CharacterDto> characterDtoList = characterService.getAll();
    //     List<CharacterRest> characterToReturn = new ArrayList<>();

    //     for (CharacterDto characterDto : characterDtoList) {
    //         CharacterRest characterRest = mapper.map(characterDto, CharacterRest.class);
    //         characterToReturn.add(characterRest);
    //     }

    //     return characterToReturn;
    // }
    


}
