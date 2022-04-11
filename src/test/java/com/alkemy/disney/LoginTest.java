package com.alkemy.disney;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.alkemy.disney.models.requests.UserDetailRequestModel;
import com.alkemy.disney.models.requests.UserLoginRequestModel;
import com.alkemy.disney.repositories.UserRepository;
import com.alkemy.disney.services.UserServiceInterface;
import com.alkemy.disney.shared.dto.UserDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class LoginTest {

    private static final String API_LOGIN_URL = "/auth/login";

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    UserServiceInterface userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    @BeforeEach
    public void cleanUp() {
        userRepository.deleteAll();
    }
    
    @Test
    public void postLogin_withoutCredentials_returns403() {
        
        ResponseEntity<Object> response = login(null, Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);      
    }

    @Test
    public void postLogin_withIncorrectCredentials_returns403() {
        UserDetailRequestModel user = TestUtil.createValidUser();
        UserDto userToRegister = mapper.map(user, UserDto.class);
        userService.registerUser(userToRegister);

        UserLoginRequestModel userLogin = new UserLoginRequestModel();
        userLogin.setUsername("wrongUsername@test.com");
        userLogin.setPassword("wrongPassword");


        ResponseEntity<Object> response = login(userLogin, Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);      
    }

    @Test
    public void postLogin_withCorrectCredentials_returns200() {
        UserDetailRequestModel user = TestUtil.createValidUser();
        UserDto userToRegister = mapper.map(user, UserDto.class);
        userService.registerUser(userToRegister);
        System.out.println(userService.getUser(user.getUsername().toString()));

        UserLoginRequestModel userLogin = new UserLoginRequestModel();
        userLogin.setUsername(userToRegister.getUsername());
        userLogin.setPassword(userToRegister.getPassword());


        ResponseEntity<Object> response = login(userLogin, Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);     
    }

    public <T> ResponseEntity<T> login(UserLoginRequestModel data, Class<T> responseType) {
        return testRestTemplate.postForEntity(API_LOGIN_URL, data, responseType);
    }


}
