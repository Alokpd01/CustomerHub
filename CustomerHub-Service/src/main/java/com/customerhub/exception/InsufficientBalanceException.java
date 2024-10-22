package com.customerhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InsufficientBalanceException extends RuntimeException {

    private String message;

    public InsufficientBalanceException(String message){
        super(message);
        this.message = message;
    }

}
