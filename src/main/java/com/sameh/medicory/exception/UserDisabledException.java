package com.sameh.medicory.exception;

public class UserDisabledException extends RuntimeException{
    public UserDisabledException() {
    }

    public UserDisabledException(String message) {
        super(message);
    }
}
