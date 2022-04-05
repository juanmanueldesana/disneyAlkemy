package com.alkemy.disney.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alkemy.disney.entities.CharacterEntity;
import com.alkemy.disney.entities.PhotoEntity;
import com.alkemy.disney.repositories.CharacterRepository;
import com.alkemy.disney.repositories.MovieRepository;
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
    private MovieRepository movieRepository;

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
    public List<CharacterDto> getAllCharacters() {
       
        List<CharacterEntity> characterEntities = (List<CharacterEntity>) characterRepository.findAll();
        List<CharacterDto> characterDtos = new ArrayList<>();

        for (CharacterEntity characterEntity : characterEntities) {
            CharacterDto characterDto = mapper.map(characterEntity, CharacterDto.class);
            characterDtos.add(characterDto);
        }

        return characterDtos;
    }

    @Override
    public List<CharacterDto> getCharactersByName(String name) {
        
        List<CharacterEntity> characterEntities = characterRepository.findByNameIgnoreCaseContaining(name);
        List<CharacterDto> characterDtos = new ArrayList<>();

        for (CharacterEntity characterEntity : characterEntities) {
            CharacterDto characterDto = mapper.map(characterEntity, CharacterDto.class);
            characterDtos.add(characterDto);
        }

        return characterDtos;
    }

    @Override
    public List<CharacterDto> getCharactersByAge(Integer age) {
        
        List<CharacterEntity> characterEntities = characterRepository.findByAge(age);
        List<CharacterDto> characterDtos = new ArrayList<>();

        for (CharacterEntity characterEntity : characterEntities) {
            CharacterDto characterDto = mapper.map(characterEntity, CharacterDto.class);
            characterDtos.add(characterDto);
        }
        
        return characterDtos;
    }

    @Override
    public List<CharacterDto> getCharactersByWeight(Double weight) {
        
        List<CharacterEntity> characterEntities = characterRepository.findByWeight(weight);
        List<CharacterDto> characterDtos = new ArrayList<>();

        for (CharacterEntity characterEntity : characterEntities) {
            CharacterDto characterDto = mapper.map(characterEntity, CharacterDto.class);
            characterDtos.add(characterDto);
        }

        return characterDtos;
    }

    private boolean checkMoviesExistence(List<String> moviesId) {

        for(String movieId : moviesId) {
            if(movieRepository.findByMovieId(movieId) == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public CharacterDto addMoviesToCharacter(String idCharacter, List<String> moviesId) {

        CharacterEntity characterEntity = characterRepository.findByCharacterId(idCharacter);
        if (checkMoviesExistence(moviesId) == true) {
            for (String movieId : moviesId) {
                characterEntity.getMovies().add(movieRepository.findByMovieId(movieId));
            }
            characterRepository.save(characterEntity);
            CharacterDto characterDto = mapper.map(characterEntity, CharacterDto.class);
            
            
            //Devuelvo el Dto con el Genero y Personajes de las peliculas en null para evitar una recursión en la respuesta
            for(int i = 0; i < characterDto.getMovies().size(); i++) {
                characterDto.getMovies().get(i).setGenres(null);
                characterDto.getMovies().get(i).setCharacters(null);
            }

            return characterDto;
        } else {
            return null;
        }
    }

    @Override
    public CharacterDto removeMoviesFromCharacter(String idCharacter, List<String> moviesId) {
        
        CharacterEntity characterEntity = characterRepository.findByCharacterId(idCharacter);
        if (checkMoviesExistence(moviesId) == true) {
            for (String movieId : moviesId) {
                characterEntity.getMovies().remove(movieRepository.findByMovieId(movieId));
            }
            characterRepository.save(characterEntity);
            CharacterDto characterDto = mapper.map(characterEntity, CharacterDto.class);

            for(int i = 0; i < characterDto.getMovies().size(); i++) {
                characterDto.getMovies().get(i).setGenres(null);
            }

            return characterDto;
        } else {
            return null;
        }
    }
    
}
