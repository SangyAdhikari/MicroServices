package com.stackroute.authenticationservice.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserNotFoundException extends Exception{

    private String message;

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}