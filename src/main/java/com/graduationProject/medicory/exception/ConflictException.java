package com.graduationProject.medicory.exception;

public class ConflictException extends RuntimeException{
    public ConflictException() {
    }

    public ConflictException(String message) {
        super(message);
    }
}