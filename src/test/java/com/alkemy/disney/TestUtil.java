package com.alkemy.disney;

import com.alkemy.disney.models.requests.UserDetailRequestModel;

public class TestUtil {
    
    public static UserDetailRequestModel createValidUser(){
        UserDetailRequestModel user = new UserDetailRequestModel();
        user.setUsername(generateRandomEmail(16)+"@test.com");
        user.setPassword(generateRandomEmail(8));

        return user;
    }


    public static String generateRandomEmail(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) ((int) (Math.random() * 26) + 97));
        }
        return sb.toString();
    }

}
