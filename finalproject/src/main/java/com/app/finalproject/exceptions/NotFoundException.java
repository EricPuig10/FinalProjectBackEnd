package com.app.finalproject.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper=false)
public class NotFoundException extends RuntimeException{
    private String code;
    private HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    public NotFoundException(String message , String code) {
        super(message);
        this.code = code;
    }
}