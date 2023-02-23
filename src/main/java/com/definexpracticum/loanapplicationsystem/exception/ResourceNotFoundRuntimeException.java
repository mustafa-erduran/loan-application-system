package com.definexpracticum.loanapplicationsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundRuntimeException extends RuntimeException{

    public ResourceNotFoundRuntimeException(String message){
        super(message);
    }
}
