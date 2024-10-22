package com.graduationProject.medicory.exception.handller;

import com.graduationProject.medicory.exception.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(StorageException.class)
    public ResponseEntity<ErrorDetails> handleStorageException(StorageException ex){
        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                new Date());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }
    @ExceptionHandler(ResutExistsException.class)
    public ResponseEntity<ErrorDetails> handleResultExistsException(ResutExistsException ex){
        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                HttpStatus.CONFLICT.value(),
                new Date());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorDetails);
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorDetails> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex){
        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                HttpStatus.PAYLOAD_TOO_LARGE.value(),
                new Date());

        return ResponseEntity
                .status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body(errorDetails);
    }
    @ExceptionHandler(VoiceRecordNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleVoiceRecordNotFoundException(VoiceRecordNotFoundException ex){
        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                new Date());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorDetails);
    }
}