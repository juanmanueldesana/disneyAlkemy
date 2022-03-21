package com.alkemy.disney.services;

import java.util.ArrayList;

import com.alkemy.disney.entities.UserEntity;
import com.alkemy.disney.exceptions.UserExistsException;
import com.alkemy.disney.repositories.UserRepository;
import com.alkemy.disney.shared.dto.UserDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ModelMapper mapper;

    @Override
    public UserDto registerUser(UserDto user) {
        
        if(userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserExistsException("Username is already in use");
        }

        UserEntity userEntity = mapper.map(user, UserEntity.class);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        UserEntity createdUser = userRepository.save(userEntity);
        UserDto userToReturn = mapper.map(createdUser, UserDto.class);

        return userToReturn;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(userEntity.getUsername(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }

    @Override
    public UserDto getUser(String username) {
        
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity == null) {
            throw new UsernameNotFoundException(username);
        }

        UserDto userToReturn = mapper.map(userEntity, UserDto.class);
        return userToReturn;
    }

}
