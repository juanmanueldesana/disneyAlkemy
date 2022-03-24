package com.alkemy.disney.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.alkemy.disney.models.requests.CharacterCreateRequestModel;
import com.alkemy.disney.models.responses.CharacterListRest;
import com.alkemy.disney.models.responses.CharacterRest;
import com.alkemy.disney.models.responses.OperationStatusModel;
import com.alkemy.disney.services.CharacterServiceInterface;
import com.alkemy.disney.shared.dto.CharacterDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public CharacterRest createCharacter(@RequestPart @Valid CharacterCreateRequestModel characterCreateRequestModel,
            @ModelAttribute MultipartFile file) {
        CharacterDto characterDto = mapper.map(characterCreateRequestModel, CharacterDto.class);
        CharacterDto createdCharacter = characterService.createCharacter(characterDto, file);
        CharacterRest characterToReturn = mapper.map(createdCharacter, CharacterRest.class);
        return characterToReturn;
    }

    @GetMapping("/{id}")
    public CharacterRest getCharacter(@PathVariable String id) {

        CharacterDto characterDto = characterService.getCharacter(id);
        CharacterRest characterToReturn = mapper.map(characterDto, CharacterRest.class);

        return characterToReturn;
    }

    @GetMapping(params = "name")
    public List<CharacterRest> getCharacterByName(@RequestParam String name) {

        List<CharacterDto> characterDtoList = characterService.getCharactersByName(name);
        List<CharacterRest> characterToReturn = new ArrayList<>();
        for (CharacterDto characterDto : characterDtoList) {
            CharacterRest character = mapper.map(characterDto, CharacterRest.class);
            characterToReturn.add(character);
        }
        return characterToReturn;
    }

    @GetMapping(params = "age")
    public List<CharacterRest> getCharacterByAge(@RequestParam Integer age) {

        List<CharacterDto> characterDtoList = characterService.getCharactersByAge(age);
        List<CharacterRest> characterToReturn = new ArrayList<>();
        for (CharacterDto characterDto : characterDtoList) {
            CharacterRest character = mapper.map(characterDto, CharacterRest.class);
            characterToReturn.add(character);
        }
        return characterToReturn;
    }

    @GetMapping(params = "weight")
    public List<CharacterRest> getCharacterByWeight(@RequestParam Double weight) {

        List<CharacterDto> characterDtoList = characterService.getCharactersByWeight(weight);
        List<CharacterRest> characterToReturn = new ArrayList<>();
        for (CharacterDto characterDto : characterDtoList) {
            CharacterRest character = mapper.map(characterDto, CharacterRest.class);
            characterToReturn.add(character);
        }
        return characterToReturn;
    }

    @GetMapping
    public List<CharacterListRest> getAllCharacters() {
        List<CharacterDto> characters = characterService.getAllCharacters();
        List<CharacterListRest> charactersToReturn = new ArrayList<>();
        for (CharacterDto characterDto : characters) {
            CharacterListRest characterToReturn = mapper.map(characterDto, CharacterListRest.class);
            charactersToReturn.add(characterToReturn);
        }
        return charactersToReturn;
    }

    @PutMapping("/{id}")
    public CharacterRest updateCharacter(@PathVariable String id,
            @RequestPart @Valid CharacterCreateRequestModel characterUpdateRequestModel,
            @ModelAttribute MultipartFile file) {

        CharacterDto characterDto = mapper.map(characterUpdateRequestModel, CharacterDto.class);
        CharacterDto updatedCharacter = characterService.updateCharacter(id, characterDto, file);
        CharacterRest characterToReturn = mapper.map(updatedCharacter, CharacterRest.class);

        return characterToReturn;
    }

    @DeleteMapping("/{id}")
    public OperationStatusModel deleteCharacter(@PathVariable String id) {
        OperationStatusModel operationStatusModel = new OperationStatusModel();
        operationStatusModel.setName("DELETE");

        characterService.deleteCharacter(id);
        operationStatusModel.setResult("SUCCESS");

        return operationStatusModel;
    }

}
