package com.alkemy.disney.repositories;

import com.alkemy.disney.entities.UserEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long>{
    
    UserEntity findByUsername(String username);
    

}
