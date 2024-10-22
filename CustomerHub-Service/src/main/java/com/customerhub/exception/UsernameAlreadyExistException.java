package com.customerhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UsernameAlreadyExistException extends RuntimeException {

    private String message;

    public UsernameAlreadyExistException(String message){
        super(message);
        this.message = message;
    }

}
