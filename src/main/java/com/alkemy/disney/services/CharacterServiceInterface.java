package com.alkemy.disney.services;

import java.util.List;

import com.alkemy.disney.shared.dto.CharacterDto;

import org.springframework.web.multipart.MultipartFile;

public interface CharacterServiceInterface {
    
    public CharacterDto createCharacter(CharacterDto character, MultipartFile file);
    public CharacterDto getCharacter(String idCharacter);
    public List<CharacterDto> getCharactersByName(String name);
    public List<CharacterDto> getCharactersByAge(Integer age);
    public List<CharacterDto> getCharactersByWeight(Double weight);
    public CharacterDto updateCharacter(String idCharacter ,CharacterDto character, MultipartFile file);
    public void deleteCharacter(String idCharacter);
    public List<CharacterDto> getAllCharacters();
}
