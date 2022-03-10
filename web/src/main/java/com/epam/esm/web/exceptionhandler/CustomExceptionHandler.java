package com.epam.esm.web.exceptionhandler;

import com.epam.esm.util.exception.CustomServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleCustomServiceException(CustomServiceException e) {
        IncorrectData incorrectData = new IncorrectData(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(incorrectData);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(Exception e) {
        IncorrectData incorrectData = new IncorrectData(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(incorrectData);
    }


}
