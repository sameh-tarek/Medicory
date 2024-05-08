package com.graduationProject.medicory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {RecordNotFoundException.class})
    public ResponseEntity<ErrorDetails> handleRecordNotFoundException(RecordNotFoundException e){
        ErrorDetails errorDetails = new ErrorDetails(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                new Date()
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorDetails> handleConflictException(ConflictException e){
        ErrorDetails errorDetails = new ErrorDetails(
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                new Date()
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<ErrorDetails>handleUserDisabledException(UserDisabledException  exception){
        ErrorDetails details=new ErrorDetails(
                exception.getMessage(),
                HttpStatus.CONFLICT.value(),
                new Date()
        );
        return new ResponseEntity<>(details,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {IllegalAccessException.class})
    public ResponseEntity<ErrorDetails> handleIllegalAccessException(IllegalAccessException e){
        ErrorDetails errorDetails = new ErrorDetails(
                e.getMessage(),
                HttpStatus.FORBIDDEN.value(),
                new Date()
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }
}
