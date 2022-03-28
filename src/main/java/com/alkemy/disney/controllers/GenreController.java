package com.alkemy.disney.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.alkemy.disney.models.requests.GenreCreateRequestModel;
import com.alkemy.disney.models.responses.GenreRest;
import com.alkemy.disney.models.responses.OperationStatusModel;
import com.alkemy.disney.services.GenreServiceInterface;
import com.alkemy.disney.shared.dto.GenreDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genres")
public class GenreController {
    
    @Autowired
    private GenreServiceInterface genreService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public GenreRest createGenre(@RequestPart @Valid GenreCreateRequestModel genreCreateRequestModel, MultipartFile file) {
        GenreDto genreDto = mapper.map(genreCreateRequestModel, GenreDto.class);
        GenreDto createdGenre = genreService.createGenre(genreDto, file);
        GenreRest genreToReturn = mapper.map(createdGenre, GenreRest.class);
        return genreToReturn;
    }

    @GetMapping("/{id}")
    public GenreRest getGenre(@PathVariable String id) {
        GenreDto genreDto = genreService.getGenreById(id);
        GenreRest genreToReturn = mapper.map(genreDto, GenreRest.class);
        return genreToReturn;
    }

    @GetMapping
    public List<GenreListRest> getAllGenres() {
        List<GenreDto> genreDtoList = genreService.getAllGenres();
        List<GenreListRest> genreToReturn = new ArrayList<>();
        for (GenreDto genreDto : genreDtoList) {
            GenreListRest genreRest = mapper.map(genreDto, GenreListRest.class);
            genreToReturn.add(genreRest);
        }
        return genreToReturn;
    }

    @PutMapping("/{id}")
    public GenreRest updateGenre(@RequestPart @Valid GenreCreateRequestModel genreUpdateRequestModel, MultipartFile file, @PathVariable String id) {
        GenreDto genreDto = mapper.map(genreUpdateRequestModel, GenreDto.class);
        GenreDto updatedGenre = genreService.updateGenre(genreDto, file, id);
        GenreRest genreToReturn = mapper.map(updatedGenre, GenreRest.class);
        return genreToReturn;
    }

    @DeleteMapping("/{id}")
    public OperationStatusModel deleteGenre(@PathVariable String id) {
        OperationStatusModel operationStatusModel = new OperationStatusModel();

        operationStatusModel.setName("DELETE");
        genreService.deleteGenre(id);
        
        operationStatusModel.setResult("SUCCESS");

        return operationStatusModel;
    }

}
