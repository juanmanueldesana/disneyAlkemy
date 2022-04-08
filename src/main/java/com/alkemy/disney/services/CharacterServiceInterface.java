package com.alkemy.disney.services;

import java.util.List;

import com.alkemy.disney.shared.dto.CharacterDto;

import org.springframework.web.multipart.MultipartFile;

public interface CharacterServiceInterface {
    
    public CharacterDto createCharacter(CharacterDto character, MultipartFile file);
    public CharacterDto getCharacter(String idCharacter);
    public CharacterDto updateCharacter(String idCharacter ,CharacterDto character, MultipartFile file);
    public void deleteCharacter(String idCharacter);
    public List<CharacterDto> getAllCharacters(String name, Integer age, Double weight);
}
