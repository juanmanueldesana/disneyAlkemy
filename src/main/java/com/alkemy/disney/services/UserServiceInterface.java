package com.alkemy.disney.services;

import com.alkemy.disney.shared.dto.UserDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceInterface extends UserDetailsService{
    
    public UserDto registerUser(UserDto user);
    public UserDto getUser(String username);

}
