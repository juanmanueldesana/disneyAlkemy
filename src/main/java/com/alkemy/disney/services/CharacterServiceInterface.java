package com.alkemy.disney.services;

import com.alkemy.disney.shared.dto.CharacterDto;

import org.springframework.web.multipart.MultipartFile;

public interface CharacterServiceInterface {
    
    public CharacterDto createCharacter(CharacterDto character, MultipartFile file);

}
