package com.alkemy.disney;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alkemy.disney.controllers.CharacterController;
import com.alkemy.disney.entities.CharacterEntity;
import com.alkemy.disney.entities.MovieEntity;
import com.alkemy.disney.entities.PhotoEntity;
import com.alkemy.disney.services.CharacterServiceInterface;
import com.alkemy.disney.shared.dto.CharacterDto;

@AutoConfigureMockMvc(addFilters = false)
@Import(CharacterController.class)
@WebMvcTest(controllers =  CharacterController.class)
public class CharacterControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ModelMapper mapper;

    @MockBean
    private CharacterServiceInterface characterService;

    PhotoEntity photo = new PhotoEntity();
    CharacterEntity character = new CharacterEntity("1", "Tom Holland", 23, 65.5, "Joven actor", new ArrayList<>(), photo);
    CharacterEntity character2 = new CharacterEntity("2", "Leonardo Di Caprio", 50, 74.5, "Actuo en Titanic", new ArrayList<>(), photo);
    CharacterEntity characterNotValid = new CharacterEntity("1", null, 23, 65.5, null, new ArrayList<>(), photo);
    List<CharacterEntity> listCharacters = Arrays.asList(character, character2);
    MovieEntity movie = new MovieEntity("1", "Titanic", LocalDate.now(), 5, new ArrayList<>(), photo, new ArrayList<>());

    @Test
    void getCharacterFound() throws Exception{
        when(characterService.getCharacter("1")).thenReturn(mapper.map(character, CharacterDto.class));

            mockMvc.perform(get("/characters/1")
                    .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER"))
                    .with(SecurityMockMvcRequestPostProcessors.csrf()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.characterId").value("1"))
                    .andExpect(jsonPath("$.name").value("Tom Holland"))
                    .andExpect(jsonPath("$.age").value(23))
                    .andExpect(jsonPath("$.weight").value(65.5))
                    .andExpect(jsonPath("$.history").value("Joven actor"))
                    .andExpect(jsonPath("$.movies").isEmpty());
    }

    @Test
    void getCharacterNotFound() throws Exception{
        when(characterService.getCharacter("1")).thenReturn(null);

            mockMvc.perform(get("/characters/1"))
                    .andExpect(status().isNotFound());
    }

    
        
}
