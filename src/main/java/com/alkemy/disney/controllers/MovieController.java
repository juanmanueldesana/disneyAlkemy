package com.alkemy.disney.controllers;

import javax.validation.Valid;

import com.alkemy.disney.models.requests.MovieCreateRequestModel;
import com.alkemy.disney.models.responses.MovieRest;
import com.alkemy.disney.models.responses.OperationStatusModel;
import com.alkemy.disney.services.MovieServiceInterface;
import com.alkemy.disney.shared.dto.MovieDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/movies")
public class MovieController {
    
    @Autowired
    private MovieServiceInterface movieService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public MovieRest createMovie(@RequestPart @Valid MovieCreateRequestModel movieCreateRequestModel,
            @ModelAttribute MultipartFile file) {
        MovieDto movieDto = mapper.map(movieCreateRequestModel, MovieDto.class);
        MovieDto createdMovie = movieService.createMovie(movieDto, file);
        MovieRest movieToReturn = mapper.map(createdMovie, MovieRest.class);
        return movieToReturn;
    }

    @GetMapping("/{id}")
    public MovieRest getMovie(@PathVariable String id) {

        MovieDto movieDto = movieService.getMovie(id);
        MovieRest movieToReturn = mapper.map(movieDto, MovieRest.class);

        return movieToReturn;
    }

    @PutMapping("/{id}")
    public MovieRest updateMovie(@PathVariable String id, @RequestPart @Valid MovieCreateRequestModel movieCreateRequestModel,
            @ModelAttribute MultipartFile file) {
        MovieDto movieDto = mapper.map(movieCreateRequestModel, MovieDto.class);
        MovieDto updatedMovie = movieService.updateMovie(id, movieDto, file);
        MovieRest movieToReturn = mapper.map(updatedMovie, MovieRest.class);
        return movieToReturn;
    }

    @DeleteMapping
    public OperationStatusModel deleteMovie(@PathVariable String id) {
        OperationStatusModel operationStatusModel = new OperationStatusModel();

        operationStatusModel.setName("DELETE");
        movieService.deleteMovie(id);
        operationStatusModel.setResult("SUCCESS");
        
        return operationStatusModel;
    }


}
