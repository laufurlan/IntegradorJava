package com.dh.clinicaSmile.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptions {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> tratarErrorNotFound(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()+ " - GLOBAL");
    }
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> badRequestHandler(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
