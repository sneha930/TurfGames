package com.lcwd.game.turf.GameTurf.exceptions;

import com.lcwd.game.turf.GameTurf.dtos.ApiResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    handle resource not found exception
    @ExceptionHandler(ResouceNotFoundException.class)
    public ResponseEntity<ApiResponseMessage> resouceNotFoundExceptionHandler(ResouceNotFoundException ex){
        ApiResponseMessage response = new ApiResponseMessage.Builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(false).build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
