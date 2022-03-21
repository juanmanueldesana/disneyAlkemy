package com.alkemy.disney.controllers;

import javax.validation.Valid;

import com.alkemy.disney.models.requests.UserDetailRequestModel;
import com.alkemy.disney.models.responses.UserRest;
import com.alkemy.disney.services.UserServiceInterface;
import com.alkemy.disney.shared.dto.UserDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    
    @Autowired
    UserServiceInterface userService;

    @Autowired
    ModelMapper mapper;

    @PostMapping(path = "/register")
    public UserRest registerUser(@RequestBody @Valid UserDetailRequestModel userDetails) {

        UserDto userDto = mapper.map(userDetails, UserDto.class);
        UserDto createdUser = userService.registerUser(userDto);
        UserRest userToReturn = mapper.map(createdUser, UserRest.class);
        
        return userToReturn;
    }

}
